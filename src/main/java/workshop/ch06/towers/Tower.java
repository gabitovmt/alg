package workshop.ch06.towers;

import java.awt.Color;
import java.awt.Graphics;

public class Tower {
    private final int groundLevel = 300;
    private final int diskHeight = 20;
    private final int maxDiskWidth = 120;
    private final int towerWidth = 15;
    private final int towerTop = 80;
    private final int towerHeight = 220;

    public Disk[] a;
    public int xCtr;
    public char label;
    public int arrayTop;

    public Tower(int var1, char var2, int maxDisks) {
        xCtr = var1;
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

    public void drawTower(Graphics var1, int var2, int var3) {
        if (var2 == 1) {
            if (var3 == 1) {
                eraseDisk(var1, arrayTop + 1);
                return;
            }

            if (var3 == 2) {
                a[arrayTop].drawDisk(var1, xCtr, arrayTop);
                return;
            }
        } else {
            var1.setColor(Color.black);
            int var4 = xCtr - 7;
            var1.fillRect(var4, 80, 15, 220);
            var1.setColor(Color.white);
            var1.drawString(String.valueOf(label), var4 + 4, 95);

            for(int var5 = 0; var5 <= arrayTop && a[var5] != null; ++var5) {
                a[var5].drawDisk(var1, xCtr, var5);
            }
        }

    }

    public void eraseDisk(Graphics var1, int var2) {
        int var3 = xCtr - 60 - 10;
        int var4 = 300 - (var2 + 1) * 20;
        short var5 = 140;
        var1.setColor(Color.lightGray);
        var1.fillRect(var3, var4, var5, 20);
        var1.setColor(Color.black);
        int var6 = xCtr - 7;
        var1.fillRect(var6, var4, 15, 20);
    }
}
