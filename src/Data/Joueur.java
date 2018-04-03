package Data;

/**
 * This interface describe a player. He as points, levels and a health bar.
 */
public interface Joueur extends Moveable {


    /**
     * Remove a pv from the player
     */
    public void hurt();

    /**
     * Fully restore the player's pv
     */
    public void heal();

    /**
     * Remove all the player's pv, resulting in his death
     */
    public void kill();

    /**
     * Get the player's score
     * @return The score
     */
    public int getPoint();

    /**
     * Add a point to the player's score
     */
    public void pointUp();

    /**
     * Remove all the player's pv, resulting in his death
     */
    public int getLevel();

    /**
     * Bring the player to the next level
     */
    public void levelUp();

}