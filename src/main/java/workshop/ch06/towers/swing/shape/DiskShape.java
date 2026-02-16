package workshop.ch06.towers.swing.shape;

import workshop.ch06.towers.gg.Disk;

import java.awt.*;

public record DiskShape(
        Disk disk, int diskSize, int diskIndex, int centerX, int bottomY
) implements Shape {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 20;
    private static final int HEIGHT_HALF = HEIGHT / 2;

    @Override
    public void draw(Graphics g) {
        int width = WIDTH * (disk.num() + 1) / diskSize;
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
        g.setColor(disk.color());
        g.fillRect(x, y, width, HEIGHT);
        g.fillArc(x - HEIGHT_HALF, y, HEIGHT, HEIGHT, 90, 180);
        g.fillArc(x + width - HEIGHT_HALF, y, HEIGHT, HEIGHT, -90, 180);
    }

    private void drawString(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        var fm = g.getFontMetrics();
        g.drawString(disk.label(), x, y + fm.getAscent() + (HEIGHT - fm.getHeight()) / 2);
    }
}
