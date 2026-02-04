package workshop.ch05.swing.shape;

import java.awt.*;

public record Line(Rectangle r, Color c) implements Shape {

    @Override
    public void draw(Graphics g) {
        g.setColor(c);
        g.drawLine(r.x, r.y, r.x + r.width, r.y + r.height);
    }
}
