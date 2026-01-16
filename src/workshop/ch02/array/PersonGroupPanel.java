package workshop.ch02.array;

import javax.swing.*;
import java.awt.*;

public class PersonGroupPanel extends JPanel {
    private static final int START_X = 8;
    private static final int START_Y = 24;
    private static final int CELL_W = 80;
    private static final int CELL_H = 16;
    private static final int SPACING = 8;
    private static final int IDX_W = 20;
    private static final int RECT_W = 40;
    private static final int ARROW_W = 20;
    private static final int ARROW_SIZE = 5;
    private static final int ROWS_MAX = 12;
    private static final int PADDING = 4;
    private static final int NOTE_X = 8;
    private static final int NOTE_Y = 16;

    private final transient PersonGroup group;

    public PersonGroupPanel(PersonGroup personGroup) {
        this.group = personGroup;
    }

    private void drawPerson(Graphics g, int idx) {
        if (idx >= group.getPersons().length) {
            return;
        }

        int x = START_X + (CELL_W + SPACING) * (idx / ROWS_MAX);
        int y = START_Y + CELL_H * (idx % ROWS_MAX);

        drawIdx(g, idx, x, y);
        drawRect(g, idx, x + IDX_W, y);
        drawArrow(g, idx, x + IDX_W + RECT_W + 2, y + CELL_H / 2);
    }

    private void drawIdx(Graphics g, int idx, int x, int y) {
        if (group.getDrawMode() != DrawMode.FULL) {
            return;
        }

        g.setColor(Color.BLACK);
        var idxText = String.valueOf(idx);
        int textWidth = g.getFontMetrics().stringWidth(idxText);
        g.drawString(idxText, x + IDX_W - textWidth - PADDING, y + CELL_H - PADDING);
    }

    private void drawRect(Graphics g, int idx, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, RECT_W, CELL_H);
        if (group.getPersons()[idx] == null) {
            g.setColor(Color.LIGHT_GRAY);
            g.fill3DRect(x + 1, y + 1, RECT_W - 1, CELL_H - 1, true);
        } else {
            g.setColor(group.getPersons()[idx].getColor());
            g.fill3DRect(x + 1, y + 1, RECT_W - 1, CELL_H - 1, true);

            var heightText = String.valueOf(group.getPersons()[idx].getHeight());
            int textWidth = g.getFontMetrics().stringWidth(heightText);
            g.setColor(Color.BLACK);
            g.drawString(heightText, x + RECT_W - textWidth - PADDING, y + CELL_H - PADDING);
        }
    }

    private void drawArrow(Graphics g, int idx, int x, int y) {
        g.setColor(idx == group.getCurIn() ? Color.RED : Color.LIGHT_GRAY);
        ((Graphics2D) g).setStroke(new BasicStroke(2f));

        g.drawLine(x, y, x + ARROW_W, y);
        g.drawLine(x, y, x + ARROW_SIZE, y - ARROW_SIZE);
        g.drawLine(x, y, x + ARROW_SIZE, y + ARROW_SIZE);

        ((Graphics2D) g).setStroke(new BasicStroke(1f));
    }

    private void drawNote(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString(group.getNote(), NOTE_X, NOTE_Y);
    }

    private void fillBackground(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        var cb = g.getClipBounds();
        g.fillRect((int) cb.getX(), (int) cb.getY(), (int) cb.getWidth(), (int) cb.getHeight());
    }

    private void draw(Graphics g) {
        if (group.getDrawMode() == DrawMode.SHORT) {
            fillBackground(g);
            drawNote(g);
            drawPerson(g, group.getOldCurIn());
            drawPerson(g, group.getCurIn());
        } else if (group.getDrawMode() == DrawMode.FULL) {
            fillBackground(g);
            drawNote(g);
            for (int idx = 0; idx < group.getPersons().length; ++idx) {
                drawPerson(g, idx);
            }
        }

        group.setDrawMode(DrawMode.FULL);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}
