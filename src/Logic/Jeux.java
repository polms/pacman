package Logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Data.Data;
import Data.Entity;
import Data.EntityGhost;
import Data.EntityGomme;
import Data.EntityType;
import Data.GhostType;
import Data.GommeType;

public class Jeux implements Logic{
	
	private int pasDeResolution = 10;
	
	private Data data;
	private Entity[][] plateau;
	private Pacman pacman;
	private Ghost[] ghosts;
	
	public Jeux(Data dataclass) {
		data = dataclass;
		plateau = data.getPlateau();
		pacman = initPacman();
		ghosts = initGhosts();
	}

	@Override
	public void movePlayerUp() {
		
		//changement de la prochaine direction
		pacman.changeNextDirection(Direction.up);
        //movePacman();
	}

	@Override
	public void movePlayerDown() {
		pacman.changeNextDirection(Direction.down);
		//movePacman();
	}

	@Override
	public void movePlayerLeft() {
	    pacman.changeNextDirection(Direction.left);
        //movePacman();
	}

	@Override
	public void movePlayerRight() {
        pacman.changeNextDirection(Direction.right);
        //movePacman();
	}
	
	
	private void movePacman() {
		
		//si la prochaine direction n'est pas �gale � la direction actuelle on v�rifi s'il peu tourner
		if(pacman.getNextDirection()!=pacman.getDirection() && !thereIsWall(pacman.getPositionX(), pacman.getPositionY(), pacman.getNextDirection()))
			pacman.changeDirection(pacman.getNextDirection());
		
		//s'il y a un mur, pacman s'arrete
		if(!thereIsWall(pacman.getPositionX(), pacman.getPositionY(), pacman.getDirection()))
			pacman.move(1);

		//pacman mange les gommes
		if(plateau[pacman.getPositionX()][pacman.getPositionY()] != null && plateau[pacman.getPositionX()][pacman.getPositionY()].type() == EntityType.GOMME) {
			EntityGomme gomme = (EntityGomme) (plateau[pacman.getPositionX()][pacman.getPositionY()]);
			pacman.eatGomme(data.getGommesValues().get(gomme.getGommeType()));
			
			//remove gomme
			plateau[pacman.getPositionX()][pacman.getPositionY()] = null;
		}
	}

	@Override
	public int getSize() {
		return this.plateau.length;
	}

	@Override
	public Entity getEntity(int X, int Y) {
		return plateau[X][Y];
	}

	@Override
	public Pacman getPacman() {
		return pacman;
	}

	@Override
	public Ghost[] getGhosts() {
		//déplacement des GHOSTS
		for(int i=0;i<ghosts.length;i++) {

			if (ghosts[i] == null) {
				continue;
			}

			//pos du ghost dans la matrice
			int xTableau = ghosts[i].getPositionX();
			int yTableau = ghosts[i].getPositionY();

			//directions possibles
			ArrayList<Direction> arDir = new ArrayList<>();
			if(plateau[xTableau][yTableau-1] == null || this.plateau[xTableau][yTableau-1].type()!=EntityType.WALL) //TODO: Check array bound
				arDir.add(Direction.up);
			if(plateau[xTableau][yTableau+1] == null || this.plateau[xTableau][yTableau+1].type()!=EntityType.WALL)
				arDir.add(Direction.down);
			if(plateau[xTableau-1][yTableau] == null || this.plateau[xTableau-1][yTableau].type()!=EntityType.WALL)
				arDir.add(Direction.left);
			if(plateau[xTableau+1][yTableau] == null || this.plateau[xTableau+1][yTableau].type()!=EntityType.WALL)
				arDir.add(Direction.right);

			//trouve la marche arrière
			Direction marcheArriere;
			if(ghosts[i].getDirection()==Direction.right) //TODO: premier mouvement ? risque de bloquage
				marcheArriere = Direction.left;
			else if(ghosts[i].getDirection()==Direction.left)
				marcheArriere = Direction.right;
			else if(ghosts[i].getDirection()==Direction.up)
				marcheArriere = Direction.down;
			else
				marcheArriere = Direction.up;

			//enlève la direction de marche arrière si il y à une autre direction possible
			if(arDir.size()>1) {
				Iterator it = arDir.iterator();
				while(it.hasNext()) {
					Direction e = (Direction) it.next();
					if (e == marcheArriere)
						it.remove();
				}
			}

			//choisi une direction aléatoirement
			if (arDir.size() != 0) {
				Direction newDirection = arDir.get((int) ((arDir.size()) * Math.random()));
				if (newDirection != ghosts[i].getDirection()) {
					ghosts[i].setDirection(newDirection);
				}

				//déplacement
				ghosts[i].move(1);
			}


			//pacman dead?
			if(Math.abs(pacman.getPositionX() - ghosts[i].getPositionX())<2 && Math.abs(pacman.getPositionY() - ghosts[i].getPositionY())<2) {
				if(System.currentTimeMillis()-pacman.timeLastKill>2000) {
					pacman.kill();
					pacman.timeLastKill = System.currentTimeMillis();
				}
			}
		}
		movePacman();
		return ghosts;
	}
	
	
	
	
	//private 
	
	private Pacman initPacman() {
        HashMap<Entity, Point> sp = data.getEntitiesStartingPosition();
        for (Entity e : sp.keySet()) {
            if (e.type() == EntityType.PACMAN) {
                Point p = sp.get(e);
                System.out.println(String.valueOf(p.x));
                return new Pacman(p.x,p.y,data.getInitialPlayerLives());
            }
        }
        return null;
	}
	
	
	
	private Ghost[] initGhosts() {
        HashMap<Entity, Point> sp = data.getEntitiesStartingPosition();
		Ghost[] list = new Ghost[4];
		int cpt=0;

		for (Entity e : sp.keySet()) {
		    if (e.type() == EntityType.GHOST) {
                EntityGhost g = (EntityGhost) e;
                Point p = sp.get(e);
                list[cpt] = new Ghost(p.x,p.y,g.getGhostType());
                cpt++;
            }
        }
		return list;
	}

	@Override
	public int getPasDeResolution() {
		return this.pasDeResolution;
	}

	
	
	private boolean thereIsWall(int posX,int posY,Direction direction) {

        switch (direction) {
            case up:
            	return this.plateau[posX][posY-1]!=null && this.plateau[posX][posY-1].type() == EntityType.WALL;
            case down:
            	return this.plateau[posX][posY+1]!=null && this.plateau[posX][posY+1].type() == EntityType.WALL;
            case left:
            	return this.plateau[posX-1][posY]!=null && this.plateau[posX-1][posY].type() == EntityType.WALL;
            case right:
            	return this.plateau[posX+1][posY]!=null && this.plateau[posX+1][posY].type() == EntityType.WALL;
            default:
            	return true;
        }
	}
}
