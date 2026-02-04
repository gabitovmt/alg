package workshop.ch05.swing.shape;

import java.awt.*;

public record Note(Point p, String label) implements Shape {

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString(label, p.x, p.y + g.getFontMetrics().getAscent());
    }
}
