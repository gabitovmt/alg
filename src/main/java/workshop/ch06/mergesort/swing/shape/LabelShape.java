package workshop.ch06.mergesort.swing.shape;

import java.awt.*;

public record LabelShape(String label, Point start) implements Shape {

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        int y = start.y + g.getFontMetrics().getAscent();
        g.drawString(label, start.x, y);
    }
}
