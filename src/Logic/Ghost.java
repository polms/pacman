package Logic;

import Data.GhostType;

public class Ghost implements Ighost {
	private int x;
	private int y;
	private GhostType type;
	private int speed;
	
	private Direction direction;
	
	public Ghost(int posX, int posY, GhostType t) {
		x=posX;
		y=posY;
		type = t;
		direction = Direction.up;
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

	@Override
	public GhostType getGhostType() {
		return type;
	}
	
	public void move(int delta) {
		if(direction==Direction.down)
			y += delta;
		else if(direction==Direction.up)
			y -= delta;
		else if(direction==Direction.left)
			x -= delta;
		else
			x += delta;
	}
	
	public void setDirection(Direction dir) {
		this.direction = dir;
	}

	public void centreX(int pas) {
		x = ((int) (x/pas-1/2))*pas;
	}
	
	public void centreY(int pas) {
		y = ((int) (y/pas-1/2))*pas;
	}

}
