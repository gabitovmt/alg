package workshop.ch05.swing.shape;

import java.awt.*;
import java.util.List;

public record Arrow(Point p, Color c, List<Line> lines, Dimension tipSize, boolean bold) implements Shape {

    @Override
    public void draw(Graphics g) {
        g.setColor(c);
        if (bold) {
            ((Graphics2D) g).setStroke(new BasicStroke(2f));
        }

        int x = p.x;
        int y = p.y;
        for (var line : lines) {
            switch (line.direction) {
                case NORTH -> {
                    g.drawLine(x, y, x, y - line.size);
                    y -= line.size;
                }
                case EAST -> {
                    g.drawLine(x, y, x + line.size, y);
                    x += line.size;
                }
                case SOUTH -> {
                    g.drawLine(x, y, x, y + line.size);
                    y += line.size;
                }
                case WEST -> {
                    g.drawLine(x, y, x - line.size, y);
                    x -= line.size;
                }
            }
        }

        new Tip(new Point(x, y), tipSize, c, lines.get(lines.size() - 1).direction).draw(g);

        if (bold) {
            ((Graphics2D) g).setStroke(new BasicStroke(1f));
        }
    }

    public record Line(int size, Direction direction) {
    }
}
