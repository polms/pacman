package Logic;

public interface Ipacman {

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
	 * get the PV of pacman
	 * @return PV
	 */
    int getPV();
	
	/**
	 * get the points of pacman
	 * @return points
	 */
    int getPoints();
    
    /**
     * 
     * @return
     */
    boolean isEaten();
}
