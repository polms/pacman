package View;

import Data.EntityGomme;
import Logic.Logic;
import Logic.Ighost;
import Logic.Ipacman;

import java.awt.*;

import javax.swing.ImageIcon;

/**
 * This class describes the main screen of the game
 * It is reading the game state at every frame and show it on the screen
 */
public class Drawer extends Canvas {
	private Logic l;
	private int pas;
    private Image pacman_u_i;
    private Image pacman_d_i;
    private Image pacman_l_i;
    private Image pacman_r_i;
    private Image hurt_i;
	private Image clide_i;
	private Image wall_i;
    private Image inky_i;
    private Image blinky_i;
    private Image pinky_i;
    private float px, py, tx, ty, p;

    /**
     * Create a new drawer
     * @param l access to the game logic
     * @param zoom multiplier to scale the game screen
     */
	public Drawer(Logic l, int zoom) {
		super();
		this.l = l;
		this.pas = l.getPasDeResolution() * zoom; // this is the size of a tile
        Dimension fensize = new Dimension(this.pas*30, this.pas*30);
        this.setSize(fensize);

        this.pacman_u_i = new ImageIcon("images/pacman_u.gif").getImage();
        this.pacman_d_i = new ImageIcon("images/pacman_d.gif").getImage();
        this.pacman_l_i = new ImageIcon("images/pacman_l.gif").getImage();
        this.pacman_r_i = new ImageIcon("images/pacman_r.gif").getImage();
        this.hurt_i = new ImageIcon("images/hurt.gif").getImage();
		this.clide_i = new ImageIcon("images/clide.png").getImage();
		this.wall_i = new ImageIcon("images/background.png").getImage();
		this.inky_i = new ImageIcon("images/inky.png").getImage();
        this.blinky_i = new ImageIcon("images/blinky.png").getImage();
        this.pinky_i = new ImageIcon("images/pinky.png").getImage();

        px = l.getPacman().getPositionX() * this.pas;
        tx = px;
        py = l.getPacman().getPositionY() * this.pas;
        ty = py;
        p = 0;
    }

    /**
     * Display a welcome screen
     * @param g the graphics to draw to (usually the drawer's graphics)
     */
    @Override
	public void paint(Graphics g) {
	    Dimension fen = this.getSize();
	    Font title_f = new Font("Algerian", Font.BOLD, 50);
        Font noms_f = new Font("Algerian", Font.BOLD, 20);
        g.drawImage(this.pacman_d_i, (fen.width/3) - 100, fen.width/4 - (fen.width/13) + 5, (fen.width/13), (fen.width/13), this);
	    g.setFont(title_f);
	    g.setColor(Color.yellow);
	    g.drawString("Pac - Man", (fen.width/3) - 9,fen.width/4);
	    g.setFont(noms_f);
	    g.setColor(Color.white);
	    g.drawString("Un jeu realise par :",(fen.width/4), fen.width/2);
        g.drawString("- Baudouin de la Fleche",(fen.width/4), fen.width/2 + 30);
        g.drawString("- Robin Moalic",(fen.width/4), fen.width/2 + 60);
        g.drawString("- Fakher Hamzaoui",(fen.width/4), fen.width/2 + 90);
	}

    /**
     * Update the game panel
     * @param g the graphics to draw to (usually the drawer's graphics)
     */
    @Override
	public void update(Graphics g){
	    Dimension fen = this.getSize();
        Image bufferImage;
        Graphics bufferGraphics;
        bufferImage=createImage(fen.width,fen.height);
        bufferGraphics=bufferImage.getGraphics();  // initialise double buffering

       this.setBackground(Color.PINK); // clear the screen
        bufferGraphics.drawImage(this.wall_i, 0, 0, fen.width, fen.height, this);
	   for (int i = 0; i < l.getSize(); i++) {
		   for (int j = 0; j < l.getSize(); j++) {  // for each tile
		   	   if (l.getEntity(i,j) == null) {
                   bufferGraphics.setColor(Color.black);//TODO: Draw the walls only once
                   bufferGraphics.fillRect(i*this.pas, j*this.pas, this.pas, this.pas);
			   } else {
				   switch (l.getEntity(i, j).type()) {
					   case WALL:
                           //bufferGraphics.setColor(l.getEntity(i, j).getColor());

                           //bufferGraphics.drawImage(wall_i, i*this.pas, j*this.pas,this.pas,this.pas, this);
                           //bufferGraphics.fillRect(i * this.pas, j * this.pas, this.pas, this.pas);
						   break;
					   case GOMME:
                           bufferGraphics.setColor(Color.black);
                           bufferGraphics.fillRect(i*this.pas, j*this.pas, this.pas, this.pas);
                           bufferGraphics.setColor(l.getEntity(i, j).getColor());

                           switch (((EntityGomme)l.getEntity(i,j)).getGommeType()) {
                               case SIMPLE:
                                   bufferGraphics.setColor(l.getEntity(i, j).getColor());
                                   break;
                               case SUPER:
                                   bufferGraphics.setColor(Color.green);
                                   break;
                               case BONUS:
                                   bufferGraphics.setColor(Color.RED);
                                   break;
                           }
                           bufferGraphics.fillOval(i * this.pas + (this.pas / 4), j * this.pas + (this.pas / 4), this.pas / 2, this.pas / 2);
						   break;
				   }
			   }
		   }
	   }
        Ipacman pacman = l.getPacman();
	   Image pm = pacman_u_i;
	    switch (pacman.getDirection()) {
            case up:
                pm = pacman_u_i;
                break;
            case down:
                pm = pacman_d_i;
                break;
            case left:
                pm = pacman_l_i;
                break;
            case right:
                pm = pacman_r_i;
                break;
        }

        tx = l.getPacman().getPositionX() * this.pas;
        ty = l.getPacman().getPositionY() * this.pas;
        if (px != tx || py != ty) {
            px = px + (tx - px) / (this.pas / p);
            py = py + (ty - py) / (this.pas / p);
            p = ((p + 1) % 5) + 1;
        }
        if(pacman.isEaten())
            bufferGraphics.drawImage(hurt_i, (int)px, (int)py,this.pas,this.pas, this);
        else
            bufferGraphics.drawImage(pm, (int)px, (int)py,this.pas,this.pas, this);
        //bufferGraphics.drawImage(hurt_i, l.getPacman().getPositionX()*this.pas, l.getPacman().getPositionY()*this.pas,this.pas,this.pas, this);
	    for (Ighost go : l.getGhosts()) {
	       if (go != null) {
               bufferGraphics.setColor(Color.black); // account for png transparency
               bufferGraphics.fillRect(go.getPositionX()*this.pas, go.getPositionY()*this.pas, this.pas, this.pas);
               bufferGraphics.setColor(Color.red);
               //bufferGraphics.fillRect(go.getPositionX()*this.pas, go.getPositionY()*this.pas, this.pas, this.pas);
               Image curr_i;
               switch (go.getGhostType()) {
                   case CLIDE:
                       curr_i = clide_i;
                       break;
                   case INKY:
                       curr_i = inky_i;
                       break;
                   case BLINKY:
                       curr_i = blinky_i;
                       break;
                   default:
                   case PINKY:
                       curr_i = pinky_i;
               }
               bufferGraphics.drawImage(curr_i, go.getPositionX() * this.pas, go.getPositionY() * this.pas, this.pas, this.pas, this);
           }
	   }
	   g.drawImage(bufferImage,0,0,this);
	    bufferGraphics.dispose();
        Thread.yield();
	}
}
