package workshop.ch05.swing.shape;

import java.awt.*;

public record Tip(Point p, Dimension d, Color c, Direction direction) implements Shape {

    @Override
    public void draw(Graphics g) {
        g.setColor(c);
        switch (direction) {
            case NORTH -> {
                g.drawLine(p.x, p.y, p.x - d.width, p.y + d.height);
                g.drawLine(p.x, p.y, p.x + d.width, p.y + d.height);
            }
            case EAST -> {
                g.drawLine(p.x, p.y, p.x - d.height, p.y - d.width);
                g.drawLine(p.x, p.y, p.x - d.height, p.y + d.width);
            }
            case SOUTH -> {
                g.drawLine(p.x, p.y, p.x - d.width, p.y - d.height);
                g.drawLine(p.x, p.y, p.x + d.width, p.y - d.height);
            }
            case WEST -> {
                g.drawLine(p.x, p.y, p.x + d.height, p.y - d.width);
                g.drawLine(p.x, p.y, p.x + d.height, p.y + d.width);
            }
        }
    }
}
