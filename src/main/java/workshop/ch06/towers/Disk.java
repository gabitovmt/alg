package workshop.ch06.towers;

import java.awt.Color;
import java.awt.Graphics;

public record Disk(int width, Color color, String label) {
    private static final int SIZE = 20;
    private static final int SIZE_HALF = SIZE / 2;
    private static final int BOTTOM_Y = 300;

    public void drawDisk(Graphics g, int centerX, int diskNum) {
        int x = centerX - width / 2;
        int y = BOTTOM_Y - (diskNum + 1) * SIZE;

        g.setColor(Color.BLACK);
        g.drawRect(x, y, width - 1, SIZE - 1);
        g.drawOval(x - SIZE_HALF, y, SIZE - 1, SIZE - 1);
        g.drawOval(x + width - SIZE_HALF - 1, y, SIZE - 1, SIZE - 1);

        g.setColor(color);
        g.fillRect(x + 1, y + 1, width - 2, SIZE - 2);
        g.fillOval(x - SIZE_HALF + 1, y + 1, SIZE - 2, SIZE - 2);
        g.fillOval(x + width - SIZE_HALF, y + 1, SIZE - 2, SIZE - 2);

        g.setColor(Color.BLACK);
        g.drawString(label, x, y + SIZE_HALF + 4);
    }
}
