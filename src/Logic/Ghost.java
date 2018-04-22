package Logic;

import Data.GhostType;

public class Ghost implements Ighost {
	private int x;
	private int y;
	private final int ix, iy;
	private final GhostType type;
	private int speed;
	private boolean fleeing;
	private long fleeing_t;
	private static long FLEE_TIME = 5000;
	
	private Direction direction;
	
	public Ghost(int posX, int posY, GhostType t) {
		x=posX;
		ix = x;
		y=posY;
		iy = y;
		type = t;
		direction = Direction.up;
		fleeing = false;
	}

	public void reset() {
		x = ix;
		y = iy;
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

	@Override
	public boolean isFleeing() {
		return fleeing && this.fleeing_t + FLEE_TIME > System.currentTimeMillis();
	}

	public void setFleeing(boolean fleeing) {
		if (isFleeing() && fleeing) {
			this.fleeing_t = System.currentTimeMillis();
		} else {
			this.fleeing = fleeing;
			if (fleeing) {
				this.fleeing_t = System.currentTimeMillis();
				direction = direction.getOppositeDirection();
			}
		}
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
