package View;

import Data.EntityGhost;
import Data.EntityGomme;
import Logic.Logic;
import Logic.Ighost;
import Logic.Ipacman;

import java.awt.*;

import javax.swing.ImageIcon;

public class Drawer extends Canvas {
	private Logic l;
	private int pas;
	private Dimension fen;
	private int zoom;
	private Image pacman_i;
	private Image clide_i;
	private Image wall_i;
    private Image inky_i;
    private Image blinky_i;
    private Image pinky_i;

	public Drawer(Logic l, int zoom, Dimension dim) {
		super();
		this.l = l;
		this.pas = l.getPasDeResolution() * zoom;
		this.fen = dim;
		this.pacman_i = new ImageIcon("images/pac-man.png").getImage();
		this.clide_i = new ImageIcon("images/clide.png").getImage();
		this.wall_i = new ImageIcon("images/wall20.png").getImage();
		this.inky_i = new ImageIcon("images/inky.png").getImage();
        this.blinky_i = new ImageIcon("images/blinky.png").getImage();
        this.pinky_i = new ImageIcon("images/pinky.png").getImage();
    }

	public void paint(Graphics g) {
	}

	public void update(Graphics g){
        Image bufferImage;
        Graphics bufferGraphics;
        bufferImage=createImage(fen.width,fen.height);
        bufferGraphics=bufferImage.getGraphics();

       this.setBackground(Color.PINK);
	   for (int i = 0; i < l.getSize(); i++) {
		   for (int j = 0; j < l.getSize(); j++) {
		   	   if (l.getEntity(i,j) == null) {
                   bufferGraphics.setColor(Color.black);//TODO: Draw the walls only once
                   bufferGraphics.fillRect(i*this.pas, j*this.pas, this.pas, this.pas);
			   } else {
				   switch (l.getEntity(i, j).type()) {
					   case WALL:
                           bufferGraphics.setColor(l.getEntity(i, j).getColor());

                           bufferGraphics.drawImage(wall_i, i*this.pas, j*this.pas,this.pas,this.pas, this);
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
        bufferGraphics.drawImage(pacman_i, pacman.getPositionX()*this.pas, pacman.getPositionY()*this.pas,this.pas,this.pas, this);
	    for (Ighost go : l.getGhosts()) {
	       if (go != null) {
               bufferGraphics.setColor(Color.black);
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
