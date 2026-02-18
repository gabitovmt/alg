package workshop.ch06.mergesort.swing.shape;

import workshop.ch06.mergesort.pg.PersonGroup;
import workshop.ch06.mergesort.pg.Size;

import java.awt.*;

public record BarsShape(PersonGroup pg, Point start) implements Shape {
    private static final int NORMAL_WIDTH = 18;
    private static final int HUGE_WIDTH = 2;

    private static final int HEIGHT = 200;

    private static final int NORMAL_SEPARATION = 7;
    private static final int HUGE_SEPARATION = 1;

    @Override
    public void draw(Graphics g) {
        int width;
        int separation;
        if (pg.size() == Size.NORMAL) {
            width = NORMAL_WIDTH;
            separation = NORMAL_SEPARATION;
        } else {
            width = HUGE_WIDTH;
            separation = HUGE_SEPARATION;
        }

        var persons = pg.persons();
        for (int i = 0; i < persons.length; i++) {
            int x = start.x + (width + separation) * i;
            new BarShape(persons[i], i, pg.size(), new Rectangle(x, start.y, width, HEIGHT)).draw(g);
        }
    }
}
