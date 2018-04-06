package Data;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;

public class DataLoader implements Data {
    private static String VALID_FILE = "niveau.xsd";
    private static int BOARD_SIZE = 30;
    private Document doc;

    public DataLoader(String fileName) throws InvalidDataException {
        File xmlFile = new File(fileName);
        validate(xmlFile);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            this.doc = dBuilder.parse(xmlFile);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if a the level file is correct according to xml schema
     *
     */
    private void validate(File schema) throws InvalidDataException {
        StreamSource xmlFileSource = new StreamSource(schema);
        File schemaFile = new File("niveau.xsd");
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schemaF = schemaFactory.newSchema(schemaFile);
            Validator validator = schemaF.newValidator();
            validator.validate(xmlFileSource);
            System.out.println(schema.getAbsolutePath() + " is a valid level");
        } catch (SAXException e) {
            String s;
            if (e instanceof SAXParseException) {
                SAXParseException ex = (SAXParseException) e;
                s = ex.getLineNumber() + ":" + ex.getColumnNumber() + ": "+e.getLocalizedMessage() ;
            } else {
                s = e.toString();
            }
            System.out.println(schema.getAbsolutePath() + " is NOT a valid level because:" + s);
            throw new InvalidDataException(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getIntFromUniqueTag(String tag)
    {
        NodeList nl = doc.getElementsByTagName(tag);
        assert nl.getLength() == 1;
        Node bs = nl.item(0);
        return Integer.parseInt(bs.getTextContent().trim());
    }

    @Override
    public int getLevelNumber() {
        int level = getIntFromUniqueTag("level");
        assert level > 0;
        return level;
    }

    @Override
    public EnumMap<GommeType, Integer> getGommesValues() {
        EnumMap<GommeType, Integer> p = new EnumMap<>(GommeType.class);
        Node n = this.doc.getElementsByTagName("gomes-values").item(0);
        NodeList nl = n.getChildNodes();
        for (int i = 1; i < nl.getLength(); i++)
        {
            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                int value = Integer.parseInt(nl.item(i).getTextContent().trim());
                switch (nl.item(i).getNodeName()) {
                    case "super-gome":
                        p.put(GommeType.SUPER, value);
                        break;
                    case "gome":
                        p.put(GommeType.SIMPLE, value);
                        break;
                    case "bonus":
                        p.put(GommeType.BONUS, value);
                        break;
                }
            }
        }
        return p;
    }

    @Override
    public int getInitialPlayerLives() {
        int lives = getIntFromUniqueTag("player-lives");
        assert lives > 0;
        return lives;
    }

    @Override
    public Entity[][] getPlateau() {
        int size = this.getPlateauSize();
        Entity[][] tab = new Entity[size][size];
        Entity mur = new Entity2(EntityType.WALL, Color.BLUE);

        Node n = this.doc.getElementsByTagName("plateau").item(0);
        String tabS = n.getTextContent().replace(" ", "").replace("\n", "");
        if (tabS.length() != this.getPlateauSize() * this.getPlateauSize()) {
            System.err.println("Err: The number of tile in the map file is wrong");
            return null;
        }
        int currentTile = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char tile = tabS.charAt(currentTile);
                switch (tile) {
                    default:
                    case '\u25A0':
                        tab[i][j] = mur;
                        break;
                    case 'N':
                        tab[i][j] = new Entity3(EntityType.GOMME, Color.white, GommeType.SIMPLE);
                        break;
                    case 'B':
                        tab[i][j] = new Entity3(EntityType.GOMME, Color.white, GommeType.BONUS);
                        break;
                    case 'S':
                        tab[i][j] = new Entity3(EntityType.GOMME, Color.white, GommeType.SUPER);
                        break;
                }
                currentTile++;
            }
        }
        assert ! Arrays.asList(tab).contains(null);
        return tab;
    }

    @Override
    public HashMap<Entity, Point> getEntitiesStartingPosition() {
        HashMap<Entity, Point> ret = new HashMap<>();
        Node n = this.doc.getElementsByTagName("start-positions").item(0);
        NodeList nl = n.getChildNodes();
        for (int i = 1; i < nl.getLength(); i++) {
            Node c = nl.item(i);
            if (c.getNodeType() == Node.ELEMENT_NODE) {
                String vc = c.getTextContent().trim();
                String cn = c.getNodeName();
                int x = Integer.parseInt(c.getAttributes().getNamedItem("x").getTextContent().trim());
                int y = Integer.parseInt(c.getAttributes().getNamedItem("y").getTextContent().trim());
                Point p = new Point(x,y);
                if (cn.equals("pacman")) {
                    ret.put(new Entity2(EntityType.PACMAN, Color.yellow), p);
                } else {
                    switch (vc) {
                        case "inky":
                            ret.put(new Entity4(EntityType.GHOST, Color.BLUE, GhostType.INKY), p);
                            break;
                        case "pinky":
                            ret.put(new Entity4(EntityType.GHOST, Color.PINK, GhostType.PINKY), p);
                            break;
                        case "clide":
                            ret.put(new Entity4(EntityType.GHOST, Color.ORANGE, GhostType.CLIDE), p);
                            break;
                        case "blinky":
                            ret.put(new Entity4(EntityType.GHOST, Color.RED, GhostType.BLINKY), p);
                            break;
                    }
                }
            }

        }
        return ret;
    }

    @Override
    public EnumMap<GommeType, Integer> getSuperPouvoirTime() {
        EnumMap<GommeType, Integer> p = new EnumMap<>(GommeType.class);
        Node n = this.doc.getElementsByTagName("gomes-values").item(0);
        NodeList nl = n.getChildNodes();
        for (int i = 1; i < nl.getLength(); i++)
        {
            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Node c = nl.item(i);
                String vc = c.getAttributes().getNamedItem("time").getTextContent().trim();
                int value = Integer.parseInt(vc);
                switch (nl.item(i).getNodeName()) {
                    case "super-gome":
                        p.put(GommeType.SUPER, value);
                        break;
                    default:
                        System.err.println("Err: getSuperPouvoirTime() invalid node found.");
                    case "gome":
                        p.put(GommeType.SIMPLE, value);
                        break;
                    case "bonus":
                        p.put(GommeType.BONUS, value);
                        break;
                }
            }
        }
        return p;
    }

    @Override
    public int getGameSpeed() {
        int speed = getIntFromUniqueTag("speed");
        assert speed > 0;
        return speed;
    }

    @Override
    public int getPlateauSize() {
        return BOARD_SIZE;
    }

    @Override
    public int getBestScore() {
        int score = getIntFromUniqueTag("best-score");
        assert score >= 0;
        return score;
    }

    @Override
    public void setBestScore(int score) {
        System.err.println("Err: void setBestScore(int score) unimplemented");
    }

    public void printBoard() {
        Entity[][] tab = this.getPlateau();
        if (tab == null)
            return;
        int size = this.getPlateauSize();
        for (int i = 0; i < size; i++) {
            System.out.print((i%10)+" ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                EntityType type = tab[i][j].type();
                switch (type) {
                    case WALL:
                        System.out.print("\u25A0");
                        break;
                    case GOMME:
                        System.out.print(((EntityGomme)tab[i][j]).getGommeType().name().charAt(0));
                        break;
                    case PACMAN:
                        System.out.print("@");
                        break;
                    case GHOST:
                        System.out.print(((EntityGhost)tab[i][j]).getGhostType().name().charAt(0));
                        break;
                }
                System.out.print(" ");
            }
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InvalidDataException {
        DataLoader d = new DataLoader("map1.xml");
        System.out.println("Score: "+ d.getBestScore());
        System.out.println(d.getEntitiesStartingPosition().values());
        Entity[][] test = d.getPlateau();
        d.printBoard();
    }
}

class Entity2 implements Entity {
    private EntityType t;
    private Color c;

    public Entity2 (EntityType t, Color c){
        this.t = t;
        this.c = c;
    }

    @Override
    public Color getColor() {
        return this.c;
    }

    @Override
    public EntityType type() {
        return this.t;
    }
}

class Entity3 implements EntityGomme {
    private GommeType gt;
    private EntityType t;
    private Color c;

    public Entity3 (EntityType t, Color c, GommeType gt){
        this.t = t;
        this.c = c;
        this.gt = gt;
    }

    @Override
    public Color getColor() {
        return this.c;
    }

    @Override
    public EntityType type() {
        return this.t;
    }
    @Override
    public GommeType getGommeType() {
        return this.gt;
    }
}

class Entity4 implements EntityGhost {
    private GhostType gt;
    private EntityType t;
    private Color c;

    public Entity4 (EntityType t, Color c, GhostType gt){
        this.t = t;
        this.c = c;
        this.gt = gt;
    }
    @Override
    public GhostType getGhostType() {
        return gt;
    }

    @Override
    public Color getColor() {
        return c;
    }

    @Override
    public EntityType type() {
        return t;
    }
}