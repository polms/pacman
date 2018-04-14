package Logic;

import Data.GhostType;

public class Ghost implements Ighost {
	private int x;
	private int y;
	private final GhostType type;
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
	
	public void move(int dx) {
		switch (direction) {
			case up:
				this.y -= dx;
				break;
			case left:
				this.x -= dx;
				break;
			case right:
				this.x += dx;
				break;
			case down:
				this.y += dx;
		}
	}
	
	public void setDirection(Direction dir) {
		this.direction = dir;
	}

}
