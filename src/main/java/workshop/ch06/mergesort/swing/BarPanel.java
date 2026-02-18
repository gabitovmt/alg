package workshop.ch06.mergesort.swing;

import workshop.ch06.mergesort.pg.PersonGroup;
import workshop.ch06.mergesort.swing.shape.Background;
import workshop.ch06.mergesort.swing.shape.BarsShape;
import workshop.ch06.mergesort.swing.shape.LabelShape;

import javax.swing.*;
import java.awt.*;

public class BarPanel extends JPanel {
    private static final Font FONT = new Font("Sans-Serif", Font.BOLD, 12);
    private static final int PADDING = 8;
    private static final int COMPARISONS_Y = 8;
    private static final int COPIES_Y = 20;
    private static final int BARS_Y = 32;

    private final transient PersonGroup pg;

    public BarPanel(PersonGroup pg) {
        this.pg = pg;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(FONT);

        new Background().draw(g);
        new LabelShape("Comparisons = " + pg.comparisons(), new Point(PADDING, COMPARISONS_Y)).draw(g);
        new LabelShape("Copies = " + pg.copies(), new Point(PADDING, COPIES_Y)).draw(g);
        new BarsShape(pg, new Point(PADDING, BARS_Y)).draw(g);
    }
}
