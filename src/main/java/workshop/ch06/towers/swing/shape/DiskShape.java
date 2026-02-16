package workshop.ch06.towers.swing.shape;

import java.awt.*;

public record DiskShape(
        Color color, int diskNum, int diskSize, int diskIndex, int centerX, int bottomY
) implements Shape {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 20;
    private static final int HEIGHT_HALF = HEIGHT / 2;

    @Override
    public void draw(Graphics g) {
        int width = WIDTH * (diskNum + 1) / diskSize;
        int x = centerX - width / 2;
        int y = bottomY - (diskIndex + 1) * HEIGHT;

        fill(g, x, y, width);
        draw(g, x, y, width);
        drawString(g, x, y);
    }

    private void draw(Graphics g, int x, int y, int width) {
        g.setColor(Color.BLACK);
        g.drawLine(x, y, x + width, y);
        g.drawLine(x, y + HEIGHT, x + width, y + HEIGHT);
        g.drawArc(x - HEIGHT_HALF, y, HEIGHT, HEIGHT, 90, 180);
        g.drawArc(x + width - HEIGHT_HALF, y, HEIGHT, HEIGHT, -90, 180);
    }

    private void fill(Graphics g, int x, int y, int width) {
        g.setColor(color);
        g.fillRect(x, y, width, HEIGHT);
        g.fillArc(x - HEIGHT_HALF, y, HEIGHT, HEIGHT, 90, 180);
        g.fillArc(x + width - HEIGHT_HALF, y, HEIGHT, HEIGHT, -90, 180);
    }

    private void drawString(Graphics g, int x, int y) {
        String label = Integer.toString(diskNum + 1);
        g.setColor(Color.BLACK);
        var fm = g.getFontMetrics();
        g.drawString(label, x, y + fm.getAscent() + (HEIGHT - fm.getHeight()) / 2);
    }
}
