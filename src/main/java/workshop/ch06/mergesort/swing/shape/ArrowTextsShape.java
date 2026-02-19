package workshop.ch06.mergesort.swing.shape;

import workshop.ch06.mergesort.pg.ArrowText;
import workshop.ch06.mergesort.pg.PersonGroup;

import java.awt.*;

public record ArrowTextsShape(PersonGroup pg, Point start, int barWidth, int barSeparation) implements Shape {
    private static final int HEIGHT = 16 * 5;

    @Override
    public void draw(Graphics g) {
        for (ArrowText a : pg.arrowTexts()) {
            int x = start.x + (barWidth + barSeparation) * a.x();
            new ArrowTextShape(a, new Rectangle(x, start.y, barWidth, HEIGHT)).draw(g);
        }
    }
}
