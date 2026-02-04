package workshop.ch05.swing.shape;

import java.awt.*;

public record Background() implements Shape {

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        var cb = g.getClipBounds();
        g.fillRect((int) cb.getX(), (int) cb.getY(), (int) cb.getWidth(), (int) cb.getHeight());
    }
}
