package workshop.ch06.mergesort.swing.shape;

import workshop.ch06.mergesort.pg.ArrowText;

import java.awt.*;

public record ArrowTextShape(ArrowText arrowText, Rectangle size) implements Shape {
    private static final int HEIGHT_STEP = 16;

    @Override
    public void draw(Graphics g) {
        drawArrow(g);
        drawText(g);
    }

    private int arrowHeight() {
        return (arrowText.y() + 1) * HEIGHT_STEP;
    }

    private void drawArrow(Graphics g) {
        if (arrowText.showArrow()) {
            new ArrowShape(arrowText.color(), new Rectangle(size.x, size.y, size.width, arrowHeight())).draw(g);
        }
    }

    private void drawText(Graphics g) {
        if (arrowText.text() != null) {
            int y = size.y + arrowHeight();
            new TextShape(arrowText.color(), arrowText.text(), new Point(size.x, y)).draw(g);
        }
    }
}
