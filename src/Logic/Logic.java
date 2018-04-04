package Logic;

import Data.Entity;

public interface Logic {
    /**
     * Move the player by a grid unit down
     */
    public void movePlayerUp();

    /**
     * Move the player by a grid unit down
     */
    public void movePlayerDown();

    /**
     * Move the player by a grid unit left
     */
    public void movePlayerLeft();

    /**
     * Move the player by a grid unit right
     */
    public void movePlayerRight();

    /**
     * Move the player by a grid unit in the same direction as the last time it was used.
     * If the player was not moved before, the default is up.
     */
    public void movePlayerAgain();


    /**
     * Get the size of the board (it is a square)
     * @return The size
     */
    public int getSize();

    /**
     * Get the entity at the given position on the board
     * @param X the x coordinate from the left of the screen
     * @param Y the y coordinate from the top of the screen
     * @return the entity, or null if outside boundary
     */
    public Entity getEntity(int X, int Y);
}