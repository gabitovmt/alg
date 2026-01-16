package workshop.ch02.array;

import java.awt.*;

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
        return height;
    }

    public Color getColor() {
        return color;
    }
}
