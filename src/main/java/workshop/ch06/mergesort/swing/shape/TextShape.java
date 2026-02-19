package workshop.ch06.mergesort.swing.shape;

import java.awt.*;

public record TextShape(Color color, String text, Point start) implements Shape {

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        int y = start.y + g.getFontMetrics().getAscent();
        g.drawString(text, start.x, y);
    }
}
