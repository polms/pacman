package View;

import java.awt.event.*;

import javax.swing.ImageIcon;

import Data.DataLoader;
import Data.InvalidDataException;
import Logic.Logic;
import Logic.Jeux;
import java.awt.*;  

public class Fenetre  {
	
	private Frame f;
	private Logic l;
	
	
	public Fenetre(Logic gamelogic) {
	    l = gamelogic;
	    f= new Frame("Canvas Example");

	    int zoom = 2;

	    //f.add(new Drawer());  
	    f.setLayout(null);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    Dimension fensize = new Dimension(l.getPasDeResolution()*31*zoom, l.getPasDeResolution()*31*zoom);
	    f.setSize(fensize);
	    f.setLocation(screenSize.width/2-300, screenSize.height/2-300);
	    f.setResizable(false);
	    //f.setUndecorated(true);
	    f.setTitle("PacMan Game");
	    f.setIconImage(new ImageIcon("images/pac-man.png").getImage());
	    f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});


		Drawer drawer = new Drawer(l, zoom, fensize);
	    drawer.setBackground(Color.black);
	    
        f.addKeyListener(new CustomKeyListener());

		f.setLayout(new BorderLayout());
		f.add(drawer, BorderLayout.CENTER);
	    drawer.validate();
	    f.setVisible(true);
	    
		while(true) {
			drawer.paint(drawer.getGraphics());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	class CustomKeyListener implements KeyListener {
		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
		    switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    l.movePlayerUp();
                    break;
                case KeyEvent.VK_DOWN:
                    l.movePlayerDown();
                    break;
                case KeyEvent.VK_LEFT:
                    l.movePlayerLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    l.movePlayerRight();
                    break;

            }
		}

		public void keyReleased(KeyEvent e) {
		}
	}

	public static void main(String[] args) throws InvalidDataException {
        DataLoader dl = new DataLoader("map1.xml");
        Jeux j = new Jeux(dl);
		new Fenetre(j);
	}
}

