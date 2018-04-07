package Logic;

import Data.GhostType;

public interface Ighost {

	/**
	 * get the position in X
	 * @return position
	 */
	public int getPositionX();
	

	/**
	 * get the position in Y
	 * @return position
	 */
	public int getPositionY();
	

	/**
	 * get the direction of Pacman
	 * @return direction
	 */
	public Direction getDirection();

	/**
	 * get the type of the ghost
	 * @return ghost type
	 */
	public GhostType getGhostType();
}
