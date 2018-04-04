package Data;

/**
 * This interface describe the game, including the board
 */
public interface jeu {
    /**
     * Initialise the board
     * @param niveau level to load, must exist somewhere
     * @param nom name of the level, to be displayed at the start
     */
    public void init(int niveau, String nom);

    /**
     * Get the size of the board (it is a square)
     * @return The size
     */
    public int getSize();

    /**
     * Remove the entity at the given position
     * @param X the x coordinate from the left of the screen
     * @param Y the y coordinate from the top of the screen
     */
    public void removeEntity(int X, int Y);

    /**
     * Add an entity to the board
     * @param entity the entity to be added
     * @param x the x coordinate from the left of the screen
     * @param y the y coordinate from the top of the screen
     */
    public void addEntity(Entity entity, int x, int y);

    /**
     * Get the entity at the given position on the board
     * @param X the x coordinate from the left of the screen
     * @param Y the y coordinate from the top of the screen
     * @return the entity, or null if outside boundary
     */
    public Entity getEntity(int X, int Y);
}