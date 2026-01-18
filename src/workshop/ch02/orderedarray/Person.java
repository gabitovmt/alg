package workshop.ch02.orderedarray;

import java.awt.Color;

public class Person {
    private final int height;
    private final Color color;

    public Person(int height, Color color) {
        this.height = height;
        this.color = color;
    }

    public Person(int height) {
        this(height, Utils.nextColor());
    }

    public int getHeight() {
        return this.height;
    }

    public Color getColor() {
        return this.color;
    }
}
