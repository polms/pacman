package Logic;

public enum Direction {
	up,
	down,
	left,
	right;

	private Direction opposite;

	static {
		up.opposite = down;
		down.opposite = up;
		left.opposite = right;
		right.opposite = left;
	}

	public Direction getOppositeDirection() {
		return opposite;
	}
}
