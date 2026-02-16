package workshop.ch06.towers.gg;

import java.awt.*;

public class Tower {
    private final Color color;
    private final String name;
    private final Stack<Disk> diskStack;
    private final int maxDisks;

    public Tower(Color color, String name, int maxDisks) {
        this.color = color;
        this.name = name;
        this.diskStack = new Stack<>(Disk.class, maxDisks);
        this.maxDisks = maxDisks;
    }

    public Color color() {
        return color;
    }

    public String name() {
        return name;
    }

    public int maxDisks() {
        return maxDisks;
    }

    public void insertDisk(Disk disk) {
        diskStack.push(disk);
    }

    public Disk removeDisk() {
        return diskStack.pop();
    }

    public Disk peekDisk() {
        return diskStack.peek();
    }

    public boolean isEmpty() {
        return diskStack.isEmpty();
    }

    public boolean isFull() {
        return diskStack.isFull();
    }

    public Disk[] toArray() {
        return diskStack.toArray();
    }
}
