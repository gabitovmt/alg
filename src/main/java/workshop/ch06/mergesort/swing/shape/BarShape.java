package workshop.ch06.mergesort.swing.shape;

import workshop.ch06.mergesort.pg.Person;
import workshop.ch06.mergesort.pg.Size;

import java.awt.*;

public record BarShape(Person person, int index, Size pgSize, Rectangle size) implements Shape {
    private static final int LABEL_OFFSET = 8;

    @Override
    public void draw(Graphics g) {
        drawBar(g);
        if (pgSize == Size.NORMAL) {
            drawLabel(g);
        }
    }

    private void drawBar(Graphics g) {
        g.setColor(person.color());
        g.fill3DRect(size.x, size.y + size.height - person.height(), size.width, person().height(), true);
    }

    private void drawLabel(Graphics g) {
        String label = Integer.toString(index);
        var fm = g.getFontMetrics();
        int x = size.x + (size.width - fm.stringWidth(label)) / 2;
        int y = size.y + size.height - LABEL_OFFSET;

        g.setColor(Color.BLACK);
        g.drawString(label, x, y);
    }
}
