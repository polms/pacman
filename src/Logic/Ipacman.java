package Logic;

public interface Ipacman {

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
	 * get the PV of pacman
	 * @return PV
	 */
	public int getPV();	
	
	/**
	 * get the points of pacman
	 * @return points
	 */
	public int getPoints();
}
