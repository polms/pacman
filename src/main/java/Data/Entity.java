package Data;

import java.awt.*;

/**
 * A class that implement the Data.Entity interface is a component of the board.
 * It has a color and a type.
 */
public interface Entity {
    /**
     * The general color of the component. Black is used to add character to component.
     * @return The color of the component
     */
    Color getColor();

    /**
     * The entity type, it can be any of EntityType Enum.
     * @return the enum
     */
    EntityType type();
}