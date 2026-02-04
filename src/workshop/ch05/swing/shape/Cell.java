package workshop.ch05.swing.shape;

import java.awt.*;

public record Cell(Rectangle r, Color c, String label) implements Shape {
    private static final int H_PADDING = 4;
    private static final int V_PADDING = 1;

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(r.x, r.y, r.width, r.height);

        g.setColor(c);
        g.fill3DRect(r.x + 1, r.y + 1, r.width - 1, r.height - 1, true);

        g.setColor(Color.BLACK);

        var fm = g.getFontMetrics();
        g.drawString(label, r.x + r.width - H_PADDING - fm.stringWidth(label), r.y + V_PADDING + fm.getAscent());
    }
}
