package Data;

import java.awt.*;

/**
 * A class that implement the Data.Entity interface is a component of the board.
 * It has a color.
 */
public interface Entity {
    /**
     * The general color of the component. Black is used to add character to component.
     * @return The color of the component
     */
    public Color getColor();

    public EntityType type();
}