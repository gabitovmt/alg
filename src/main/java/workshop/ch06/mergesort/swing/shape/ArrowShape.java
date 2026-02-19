package workshop.ch06.mergesort.swing.shape;

import java.awt.*;

public record ArrowShape(Color color, Rectangle size) implements Shape {

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        int x = size.x + size.width / 2;
        int y = size.y;

        g.drawLine(x, y, x, y + size.height);
        g.drawLine(x, y, x - 3, y + 5);
        g.drawLine(x, y, x + 3, y + 5);
    }
}
