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

	public Drawer(Logic l, int zoom, Dimension dim) {
		super();
		this.l = l;
		this.pas = l.getPasDeResolution() * zoom;
		this.fen = dim;
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g){
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
                           //bufferGraphics.drawImage(new ImageIcon("images/wall.gif").getImage(), i*this.pas, j*this.pas,this.pas,this.pas, this);
                           bufferGraphics.fillRect(i * this.pas, j * this.pas, this.pas, this.pas);
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
        bufferGraphics.drawImage(new ImageIcon("images/pac-man.png").getImage(), pacman.getPositionX()*this.pas, pacman.getPositionY()*this.pas,this.pas,this.pas, this);
	   for (Ighost go : l.getGhosts()) {
	       if (go != null) {
	           System.out.println("Ghost "+go.getPositionX()+" "+go.getPositionY()+" "+go.getDirection().toString() );
               bufferGraphics.setColor(Color.red);
               bufferGraphics.fillRect(go.getPositionX()*this.pas, go.getPositionY()*this.pas, this.pas, this.pas);
               //bufferGraphics.drawImage(new ImageIcon("images/ghost-rose.png").getImage(), go.getPositionX() * this.pas, go.getPositionY() * this.pas, this.pas, this.pas, this);
           }
	   }

        g.drawImage(bufferImage,0,0,this);
	}
}
