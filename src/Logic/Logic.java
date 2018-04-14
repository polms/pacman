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
     * Get the size of the board (it is a square)
     * @return The size
     */
    public int getSize();
    
    /**
     * Get the resolution in pixel
     * @return resolution
     */
    public int getPasDeResolution();

    /**
     * Get the entity at the given position on the board
     * @param X the x coordinate from the left of the screen
     * @param Y the y coordinate from the top of the screen
     * @return the entity, or null if outside boundary
     */
    public Entity getEntity(int X, int Y);
    
    /**
     * get the pacman of the game
     * @return the pacman
     */
    public Pacman getPacman();
    
    
    /**
     * get all the ghosts of the game
     * @return list of ghost
     */
    public Ghost[] getGhosts();

    /**
     * get the all time best score for a map
     * @return best scrore
     */
    int getBestScore();
}