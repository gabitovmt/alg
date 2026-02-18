package workshop.ch06.towers.swing;

import workshop.ch06.towers.gg.Game;
import workshop.ch06.towers.swing.shape.TowersShape;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final transient Game game;

    public GamePanel(Game game) {
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        new TowersShape(game).draw(g);
    }
}
