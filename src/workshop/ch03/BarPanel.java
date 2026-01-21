package workshop.ch03;

import javax.swing.*;
import java.awt.*;

public class BarPanel extends JPanel {
    private static final int PADDING = 16;
    private static final int BAR_MAX_HEIGHT = 200;
    private static final int STATS_HEIGHT = 24;
    private static final int TEXT_HEIGHT = 12;
    private static final int ARROW_SIZE = 5;
    private static final int NORMAL_BAR_WIDTH = 20;
    private static final int NARROW_BAR_WIDTH = 2;
    private static final int NORMAL_SEPARATION_WIDTH = 10;
    private static final int NARROW_SEPARATION_WIDTH = 1;

    private final PersonGroup pg;

    public BarPanel(PersonGroup personGroup) {
        this.pg = personGroup;
    }

    private void fillBackground(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        var cb = g.getClipBounds();
        g.fillRect((int) cb.getX(), (int) cb.getY(), (int) cb.getWidth(), (int) cb.getHeight());
    }

    private int getBarWidth() {
        return pg.barMode() == BarMode.NORMAL ? NORMAL_BAR_WIDTH : NARROW_BAR_WIDTH;
    }

    private int getBarSeparation() {
        return pg.barMode() == BarMode.NORMAL ? NORMAL_SEPARATION_WIDTH : NARROW_SEPARATION_WIDTH;
    }

    private void drawStats(Graphics g) {
        int x = PADDING;
        int y = PADDING;

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, (int) g.getClipBounds().getWidth() - 2 * PADDING, STATS_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawString("Swaps = " + pg.swaps(), x, y + TEXT_HEIGHT);
        g.drawString("Comparisons = " + pg.comps(), x, y + 2 * TEXT_HEIGHT);
    }

    private void drawOneBar(Graphics g, int idx) {
        int height = pg.person(idx).getHeight();
        int x = PADDING + idx * (getBarWidth() + getBarSeparation());
        int y = PADDING + STATS_HEIGHT + BAR_MAX_HEIGHT - height;
        Color c = pg.person(idx).getColor();

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, PADDING + STATS_HEIGHT, getBarWidth(), BAR_MAX_HEIGHT);
        g.setColor(c);
        g.fill3DRect(x, y, getBarWidth(), height, true);
    }

    private void drawBars(Graphics g) {
        for (int idx = 0; idx < pg.length(); ++idx) {
            drawOneBar(g, idx);
        }
    }

    private void arrowText(Graphics g, Color c, String text, int idxX, int idxY, boolean showArrow, boolean showText) {
        int x = PADDING + idxX * (getBarWidth() + getBarSeparation());
        int y = PADDING + STATS_HEIGHT + BAR_MAX_HEIGHT;
        g.setColor(c);

        if (showText) {
            g.drawString(text, x, y + (idxY + 1) * TEXT_HEIGHT);
        }

        if (showArrow) {
            int x1 = x + getBarWidth() / 2;
            g.drawLine(x1, y, x1, y + idxY * TEXT_HEIGHT);
            g.drawLine(x1, y, x1 - ARROW_SIZE, y + ARROW_SIZE);
            g.drawLine(x1, y, x1 + ARROW_SIZE, y + ARROW_SIZE);
        }
    }

    private void drawArrowTexts(Graphics g) {
        if (pg.barMode() == BarMode.NORMAL) {
            arrowText(g, Color.RED, "outer", pg.outer(), 3, true, true);
            arrowText(g, Color.BLUE, "inner", pg.inner(), 1, true, true);
            arrowText(g, Color.BLUE, "inner+1", pg.inner() + 1, 1, true, true);
            if (pg.doneFlag()) {
                arrowText(g, Color.BLACK, "Sort is complete", pg.inner(), 2, false, true);
            } else if (pg.person(pg.inner()).getHeight() > pg.person(pg.inner() + 1).getHeight()) {
                arrowText(g, Color.BLUE, "Will be swapped", pg.inner(), 2, false, true);
            } else {
                arrowText(g, Color.BLUE, "Will not be swapped", pg.inner(), 2, false, true);
            }
        } else {
            arrowText(g, Color.RED, "xxx", pg.outer(), 3, true, false);
            arrowText(g, Color.BLUE, "xxx", pg.inner(), 1, true, false);
            arrowText(g, Color.BLUE, "xxx", pg.inner() + 1, 1, true, false);
        }
    }

    public void draw(Graphics g) {
        fillBackground(g);
        drawBars(g);
        drawStats(g);
        drawArrowTexts(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}
