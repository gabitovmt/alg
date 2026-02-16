package workshop.ch06.towers.gg;

import workshop.ch06.towers.swing.shape.DiskShape;

import java.awt.Color;
import java.awt.Graphics;

public class Tower {
    private final int groundLevel = 300;
    private final int diskHeight = 20;
    private final int maxDiskWidth = 120;
    private final int towerTop = 80;

    private final int TOWER_TOP = 80;
    private final int TOWER_WIDTH = 15;
    private final int TOWER_HEIGHT = 220;

    private final int LABEL_TOP = 15;

    public Disk[] a;
    public int xCenter;
    public char label;
    public int arrayTop;

    public Tower(int var1, char var2, int maxDisks) {
        xCenter = var1;
        label = var2;
        a = new Disk[maxDisks];
        arrayTop = -1;
    }

    public void insertDisk(Disk var1) {
        a[++arrayTop] = var1;
    }

    public Disk removeDisk() {
        return a[arrayTop--];
    }

    public Disk peekDisk() {
        return a[arrayTop];
    }

    public boolean isEmpty() {
        return arrayTop == -1;
    }

    public boolean isFull() {
        return arrayTop == a.length - 1;
    }

    public void drawTower(Graphics g, int var2, int var3) {
        drawTower(g);
        drawText(g);

        for(int var5 = 0; var5 <= arrayTop && a[var5] != null; ++var5) {
            var disk = a[var5];
            var num = Integer.parseInt(disk.label()) - 1;
            new DiskShape(disk.color(), disk.num(), 4, var5, xCenter, 300).draw(g);
        }
    }

    private void drawTower(Graphics g) {
        g.setColor(new Color(153, 0, 0));
        g.fillRect(xCenter - TOWER_WIDTH / 2, TOWER_TOP, TOWER_WIDTH, TOWER_HEIGHT);
    }

    private void drawText(Graphics g) {
        g.setColor(Color.WHITE);
        var fm = g.getFontMetrics();
        int x = xCenter - (TOWER_WIDTH - fm.stringWidth(String.valueOf(label))) / 2;
        int y = TOWER_TOP + LABEL_TOP;

        g.drawString(String.valueOf(label), x, y);
    }
}
