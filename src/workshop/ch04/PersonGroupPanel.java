package workshop.ch04;

import javax.swing.*;
import java.awt.*;

public class PersonGroupPanel extends JPanel {
    private static final int START_X = 160;
    private static final int START_Y = 184;
    private static final int IDX_W = 20;
    private static final int RECT_W = 40;
    private static final int CELL_H = 16;
    private static final int PADDING = 3;
    private static final int ARROW_W = 20;
    private static final int ARROW_SIZE = 5;
    private static final int NOTE_X = 16;
    private static final int NOTE_Y = 24;

    private final transient PersonGroup group;

    public PersonGroupPanel(PersonGroup personGroup) {
        this.group = personGroup;
    }

    public void drawPerson(Graphics g, int idx) {
        int x = START_X;
        int y = START_Y - CELL_H * idx;

        drawIdx(g, idx, x, y);
        drawRect(g, idx, x + IDX_W, y);
    }

    private void drawIdx(Graphics g, int idx, int x, int y) {
        g.setColor(Color.BLACK);
        var idxText = String.valueOf(idx);
        int textWidth = g.getFontMetrics().stringWidth(idxText);
        g.drawString(idxText, x + IDX_W - textWidth - PADDING, y + CELL_H - PADDING);
    }

    private void drawRect(Graphics g, int idx, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, RECT_W, CELL_H);
        if (group.getPerson(idx) == null) {
            g.setColor(Color.LIGHT_GRAY);
            g.fill3DRect(x + 1, y +1, RECT_W - 1, CELL_H - 1, true);
        } else {
            g.setColor(group.getPerson(idx).color());
            g.fill3DRect(x + 1, y +1, RECT_W - 1, CELL_H - 1, true);

            var heightText = String.valueOf(group.getPerson(idx).height());
            int textWidth = g.getFontMetrics().stringWidth(heightText);

            g.setColor(Color.BLACK);
            g.drawString(heightText, x + RECT_W - textWidth - PADDING, y + CELL_H - PADDING);
        }
    }

    private void drawArrow(Graphics g) {
        int x = START_X + IDX_W + RECT_W + 2;
        int y = START_Y - CELL_H * group.getCurrentPosition() + CELL_H / 2;

        g.setColor(Color.RED);

        ((Graphics2D) g).setStroke(new BasicStroke(2f));
        g.drawLine(x, y, x + ARROW_W, y);
        g.drawLine(x, y, x + ARROW_SIZE, y - ARROW_SIZE);
        g.drawLine(x, y, x + ARROW_SIZE, y + ARROW_SIZE);
        ((Graphics2D) g).setStroke(new BasicStroke(1f));

        g.drawString(group.getArrowText(), x + ARROW_W + PADDING, y + CELL_H / 2 - PADDING);
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

    public void draw(Graphics g) {
        fillBackground(g);
        drawNote(g);
        for (int idx = 0; idx < group.getCapacity(); ++idx) {
            drawPerson(g, idx);
        }
        drawArrow(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}
