package workshop.ch06.mergesort.swing.shape;

import workshop.ch06.mergesort.pg.PersonGroup;

import java.awt.*;

public record BarsShape(PersonGroup pg, Point start, int barWidth, int barSeparation) implements Shape {
    private static final int HEIGHT = 200;

    @Override
    public void draw(Graphics g) {
        var persons = pg.persons();
        for (int i = 0; i < persons.length; i++) {
            int x = start.x + (barWidth + barSeparation) * i;
            new BarShape(persons[i], i, pg.size(), new Rectangle(x, start.y, barWidth, HEIGHT)).draw(g);
        }
    }
}
