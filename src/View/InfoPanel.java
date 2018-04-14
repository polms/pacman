package View;

import Logic.Logic;

import javax.swing.*;
import java.awt.*;

/**
 * This class describe the info section of the game
 */
public class InfoPanel extends Panel {
    private Logic l;
    private Label score;
    private Label lives;

    /**
    * Create a new drawer
    * @param l access to the game logic
    */
    public InfoPanel(Logic l) {
        super();
        this.l = l;

        Font f = new Font("Monospaced", Font.PLAIN, 20);

        this.score = new Label("Score:    ");
        this.score.setFont(f);
        this.lives = new Label("Lives:    ");
        this.lives.setFont(f);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(score);
        this.add(lives);
    }

    /**
     * Update game information
     */
    public void updateInfo() {
        StringBuilder pv = new StringBuilder();
        int npv = l.getPacman().getPV();
        if (npv <= 0) {
            pv.append("mort");
        } else {
            for (int i = 0; i < l.getPacman().getPV(); i++)
                pv.append("#");
        }
        this.score.setText("Score: "+l.getPacman().getPoints());
        this.lives.setText("Vies: "+pv.toString());
    }
}
