package Data;

import java.awt.*;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * This interface enable access the initials data of the game
 */
public interface Data {

    /**
     * Get the number of the loaded stage
     * @return The stage number
     */
    int getLevelNumber();

    /**
     * Get the values in points of each gomme type.
     * @post each Integer is positive
     * @return an hashmap associating each GommeType with it's value
     */
    EnumMap<GommeType, Integer> getGommesValues();

    /**
     * Get the time a superpouvoir is applied to a player for each gomme type
     * @post each Integer is positive
     * @return an hashmap associating each GommeType with it's value
     */
    EnumMap<GommeType, Integer> getSuperPouvoirTime();

    /**
     * Get the number of lives available for the level
     * @pre lives superior to zero
     * @return the number of lives
     */
    int getInitialPlayerLives();

    /**
     * Get the game board, each tiles has an entity. It can be a wall or a gomme
     * @post no tiles are null
     * @return The game board, can be null if the game file is not correct
     */
    Entity[][] getPlateau();


    /**
     * Get the starting positions of Pacman and the ghosts
     * Pacman can be alone or with up to five of the unique ghosts
     *
     * @post only one pacman entity
     * @post at most one entity of each ghost types
     * @return A mac betwin entities and their starting point.
     */
    HashMap<Entity, Point> getEntitiesStartingPosition();

    /**
     * Get the speed of a game frame in milliseconds
     * @pre the int is positive
     * @return the value in ms
     */
    int getGameSpeed();

    /**
     * Get the size of the board (it is a square)
     * @pre the length is equal to the height of the board
     * @return the size in tiles
     */
    int getPlateauSize();

    /**
     * Get the best score of all saved games; 0 if it is the first games
     * @post return positive
     * @return the size in tiles
     */
    int getBestScore();

    /**
     * Save the new best score
     * @param score The new best score
     * @pre score superior to prev.score
     * @post getBestScore() = score
     */
    void setBestScore(int score);
}