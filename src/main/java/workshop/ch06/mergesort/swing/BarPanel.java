package workshop.ch06.mergesort.swing;

import workshop.ch06.mergesort.pg.PersonGroup;
import workshop.ch06.mergesort.pg.Size;
import workshop.ch06.mergesort.swing.shape.ArrowTextsShape;
import workshop.ch06.mergesort.swing.shape.Background;
import workshop.ch06.mergesort.swing.shape.BarsShape;
import workshop.ch06.mergesort.swing.shape.TextShape;

import javax.swing.*;
import java.awt.*;

public class BarPanel extends JPanel {
    private static final Font FONT = new Font("Sans-Serif", Font.BOLD, 12);
    private static final int PADDING = 8;
    private static final int COMPARISONS_Y = 8;
    private static final int COPIES_Y = 20;
    private static final int BARS_Y = 32;
    private static final int ARROW_TEXTS_Y = 240;

    private static final int BAR_NORMAL_WIDTH = 18;
    private static final int BAR_HUGE_WIDTH = 2;
    private static final int BAR_NORMAL_SEPARATION = 7;
    private static final int BAR_HUGE_SEPARATION = 1;

    private final transient PersonGroup pg;

    public BarPanel(PersonGroup pg) {
        this.pg = pg;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(FONT);

        new Background().draw(g);
        new TextShape(Color.BLACK, "Comparisons = " + pg.comparisons(), new Point(PADDING, COMPARISONS_Y)).draw(g);
        new TextShape(Color.BLACK, "Copies = " + pg.copies(), new Point(PADDING, COPIES_Y)).draw(g);
        new BarsShape(pg, new Point(PADDING, BARS_Y), barWidth(), barSeparation()).draw(g);
        new ArrowTextsShape(pg, new Point(PADDING, ARROW_TEXTS_Y), barWidth(), barSeparation()).draw(g);
    }

    private int barWidth() {
        return pg.size() == Size.NORMAL ? BAR_NORMAL_WIDTH : BAR_HUGE_WIDTH;
    }

    private int barSeparation() {
        return pg.size() == Size.NORMAL ? BAR_NORMAL_SEPARATION : BAR_HUGE_SEPARATION;
    }
}
