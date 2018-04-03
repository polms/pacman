package Data;

import java.awt.*;

/**
 * A class that implement the Data.Entity interface is a component of the board.
 * It has a position and a color.
 */
public interface Entity {

    /**
     * The position of the component on the x axis from the left of the board.
     * @return position
     */
    public int getX();

    /**
     * The position of the component on the y axis from the top of the board.
     * @return position
     */
    public int getY();

    /**
     * The general color of the component. Black is used to add character to component.
     * @return The color of the component
     */
    public Color getColor();
}