package Data;

import java.util.HashMap;

/**
 * This interface enable access the initials data of the game
 */
public interface Data {
    /**
     * Get the values in points of each gomme type.
     * @post each Integer is positive
     * @return an hashmap associating each GommeType with it's value
     */
    public HashMap<GommeType, Integer> getGommesValues();

    /**
     * Get the number of lives available for the level
     * @pre lives > 0
     * @return the number of lives
     */
    public int getInitialPlayerLives();

    /**
     * Get the game board, each tiles as an entity. It can be a wall, a gomme, a ghost or the player
     * @post no tiles are null
     * @post one and only one pacman
     * @post at most one of each GhostType
     * @return The game board
     */
    public Entity[][] getPlateau();

    /**
     * Get the time a superpouvoir is applied to a player for each gomme type
     * @post each Integer is positive
     * @return an hashmap associating each GommeType with it's value
     */
    public HashMap<GommeType, Integer> getSuperPouvoirTime();

    /**
     * Get the speed of a game frame in milliseconds
     * @pre the int is positive
     * @return the value in ms
     */
    public int getGameSpeed();

    /**
     * Get the size of the board (it is a square)
     * @pre the length is equal to the height of the board
     * @return the size in tiles
     */
    public int getPlateauSize();

    /**
     * Get the best score of all saved games; 0 if it is the first games
     * @post return >= 0
     * @return the size in tiles
     */
    public int getBestScore();

    /**
     * Save the new best score
     * @param score The new best score
     * @pre score > prev.score
     * @post getBestScore() = score
     */
    public void setBestScore(int score);
}