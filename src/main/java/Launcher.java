import Data.DataAccess;
import Data.InvalidDataException;
import Logic.Jeux;
import View.Fenetre;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Launcher {
    public static void main(String[] arg)  {
        String level = "map1.xml";
        if (arg.length == 2)
            level = arg[1];
        try {
            DataAccess dl = new DataAccess(level);
            Jeux j = new Jeux(dl);
            new Fenetre(j);
        } catch (InvalidDataException | IOException e) {
            OkDialog di = new OkDialog(null, "Erreur de chargement du fichier \""+level+"\"",e.getMessage());
            di.setVisible(true);
            System.exit(1);
        }
    }
}

class OkDialog extends Dialog implements ActionListener {

    public OkDialog(Frame parent, String title, String message) {

        super(parent, true);
        this.setTitle(title);
        this.add(BorderLayout.CENTER, new Label(message));
        Panel p = new Panel();
        p.setLayout(new FlowLayout());
        Button ok = new Button("Ok");
        ok.addActionListener(this);
        p.add(ok);
        this.add(BorderLayout.SOUTH, p);
        this.setSize(300,100);
        this.setLocation(100, 200);
        this.pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}

