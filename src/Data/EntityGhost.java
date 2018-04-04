package Data;

/**
 * describe a ghost, it can be any of the 5 ghost in the game.
 */
public interface EntityGhost extends Entity {
    /**
     * Get the type of the ghost
     * @return The type of the ghost
     */
    public GhostType getGhostType();
}
