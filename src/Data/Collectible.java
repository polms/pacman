package Data;

/**
 * A class that implement the Data.Entity interface is a compoment of the board.
 * It can be gathered by the main character and will then be removed from the board.
 */
public interface Collectible extends Entity {

    /**
     * The value of a collectible indicate the number of point the main character will obtain by gethering it.
     * @return The value
     */
    public int getValue();

    /**
     * The type indicate which ghost it is.
     * @return The GostType
     */
    public GhostType getType();
}