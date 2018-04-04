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
     * Get the game board
     * @return
     */
    public Entity[][] getPlateau();

    public HashMap<GommeType, Integer> getSuperPouvoirTime();

    public int getGameSpeed();

    public int getPlateauSize();

    public int getBestScore();

    public void setBestScore();
}