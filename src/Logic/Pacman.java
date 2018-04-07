package Logic;

import Data.GommeType;

public class Pacman implements Ipacman {
	
	private int x;
	private int y;
	private Direction direction;
	private int pv;
	private int point;
	public long timeLastKill;
	
	public Pacman(int posX,int posY, int points) {
		x = posX;
		y = posY;
		direction = Direction.up;
		point = points;
	}

	@Override
	public int getPositionX() {
		return x;
	}

	@Override
	public int getPositionY() {
		return y;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}
	

	public void move(int dx) {
		
	}
	
	public void changeDirection(Direction dir) {
		direction = dir;
	}

	@Override
	public int getPV() {
		return pv;
	}
	
	public void kill() {
		pv --;
	}
	
	public void eatGomme(int points) {
		point += points;
	}

	@Override
	public int getPoints() {
		return point;
	}
}
