package workshop.ch06.towers.swing;

import workshop.ch06.towers.gg.GameGroup;
import workshop.ch06.towers.swing.shape.TowersShape;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final transient GameGroup group;

    public GamePanel(GameGroup group) {
        this.group = group;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        new TowersShape(group).draw(g);
    }
}
