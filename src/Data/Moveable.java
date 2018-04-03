package Data;

/**
 * A class that implement the Data.Moveable interface is a piece on the board,
 * able to move in a given direction ({Up, Down, Left, Right} axis).
 * The speed at of the element can be set.
 */
public interface Moveable extends Entity {

    /**
     * Get the moving speed of the entity
     * @return The speed
     */
    public MoveSpeed getSpeed();

    /**
     * Set the moving speed.
     */
    public void setSpeed(MoveSpeed speed);

    /**
     * Move the entity by a grid unit up
     */
    public void moveUp();

    /**
     * Move the entity by a grid unit down
     */
    public void moveDown();

    /**
     * Move the entity by a grid unit left
     */
    public void moveLeft();

    /**
     * Move the entity by a grid unit right
     */
    public void moveRight();

    /**
     * Move the entity by a grid unit in the same direction as the last time it was used.
     * If the entity was not moved before, the default is up.
     */
    public void moveAgain();

}