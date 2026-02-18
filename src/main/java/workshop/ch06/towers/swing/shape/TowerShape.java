package workshop.ch06.towers.swing.shape;

import workshop.ch06.towers.gg.Disk;
import workshop.ch06.towers.gg.Tower;

import java.awt.*;

public record TowerShape(Tower tower, int centerX, int y, int height) implements Shape {
    private static final int WIDTH = 15;
    private static final int LABEL_OFFSET = 15;

    @Override
    public void draw(Graphics g) {
        drawTower(g);
        drawString(g);
    }

    public Rectangle getBounds() {
        return new Rectangle(centerX - WIDTH / 2, y, WIDTH, height);
    }

    private void drawTower(Graphics g) {
        g.setColor(tower.color());
        var r = getBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
        drawDisks(g);
    }

    private void drawString(Graphics g) {
        g.setColor(Color.BLACK);
        var fm = g.getFontMetrics();

        int x = centerX - fm.stringWidth(tower.name()) / 2;
        int y = this.y + LABEL_OFFSET;

        g.drawString(tower.name(), x, y);
    }

    private void drawDisks(Graphics g) {
        int bottomY = y + height;

        Disk[] disks = tower.toArray();
        for (int i = 0; i < disks.length; i++) {
            drawDisk(g, disks[i], i, bottomY);
        }
    }

    private void drawDisk(Graphics g, Disk disk, int index, int bottomY) {
        new DiskShape(disk, tower.maxDisks(), index, centerX, bottomY).draw(g);
    }
}
