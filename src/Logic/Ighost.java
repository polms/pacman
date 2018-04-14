package Logic;

import Data.GhostType;

public interface Ighost {

	/**
	 * get the position in X
	 * @return position
	 */
    int getPositionX();
	

	/**
	 * get the position in Y
	 * @return position
	 */
    int getPositionY();
	

	/**
	 * get the direction of Pacman
	 * @return direction
	 */
    Direction getDirection();

	/**
	 * get the type of the ghost
	 * @return ghost type
	 */
    GhostType getGhostType();
}
