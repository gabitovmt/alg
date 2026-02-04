package workshop.ch05.swing;

import workshop.ch05.pg.Person;
import workshop.ch05.pg.PersonGroup;
import workshop.ch05.swing.shape.Arrow;
import workshop.ch05.swing.shape.Background;
import workshop.ch05.swing.shape.Cell;
import workshop.ch05.swing.shape.Direction;
import workshop.ch05.swing.shape.Note;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PersonGroupPanel extends JPanel {
    private static final Font FONT = new Font("SansSerif", Font.BOLD, 12);
    private static final Point NOTE_POINT = new Point(16, 16);
    private static final Dimension CELL_SIZE = new Dimension(35, 17);
    private static final java.util.List<Arrow.Line> ARROW_SIZE = java.util.List.of(new Arrow.Line(20, Direction.EAST));
    private static final java.util.List<Arrow.Line> BACK_ARROW_SIZE = java.util.List.of(
            new Arrow.Line(8, Direction.EAST),
            new Arrow.Line(38, Direction.SOUTH),
            new Arrow.Line(407, Direction.WEST),
            new Arrow.Line(19, Direction.SOUTH),
            new Arrow.Line(20, Direction.EAST)
    );
    private static final java.util.List<Arrow.Line> FRONT_INS_ARROW_SIZE = java.util.List.of(
            new Arrow.Line(8, Direction.EAST),
            new Arrow.Line(13, Direction.NORTH),
            new Arrow.Line(35, Direction.WEST),
            new Arrow.Line(13, Direction.NORTH),
            new Arrow.Line(7, Direction.EAST)
    );
    private static final java.util.List<Arrow.Line> REAR_INS_ARROW_SIZE = List.of(
            new Arrow.Line(6, Direction.EAST),
            new Arrow.Line(13, Direction.SOUTH),
            new Arrow.Line(10, Direction.WEST),
            new Arrow.Line(13, Direction.SOUTH),
            new Arrow.Line(7, Direction.EAST)
    );
    private static final Dimension TIP_SIZE = new Dimension(3, 5);

    private final transient PersonGroup pg;

    public PersonGroupPanel(PersonGroup pg) {
        this.pg = pg;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.setFont(FONT);
        new Background().draw(g);
        new Note(NOTE_POINT, pg.getNote()).draw(g);

        for (int i = 0; i < pg.size(); ++i) {
            drawLink(g, i);
        }

        if (pg.insertingIndex() != null) {
            drawInsertLink(g);
        } else if (pg.deletingIndex() != null) {
            drawDeleteLink(g);
        }
    }

    private Cell personCell(int x, int y, Person person) {
        var cellRect = new Rectangle(new Point(x, y), CELL_SIZE);
        var label = Integer.toString(person.height());

        return new Cell(cellRect, person.color(), label);
    }

    private Arrow arrowCell(int x, int y) {
        return new Arrow(new Point(x, y), Color.BLACK, ARROW_SIZE, TIP_SIZE);
    }

    private Arrow removeArrowCell(int x, int y) {
        return new Arrow(new Point(x, y), Color.LIGHT_GRAY, ARROW_SIZE, TIP_SIZE);
    }

    private Arrow backArrowCell(int x, int y) {
        return new Arrow(new Point(x, y), Color.BLACK, BACK_ARROW_SIZE, TIP_SIZE);
    }

    private Arrow frontInsArrowCell(int x, int y) {
        return new Arrow(new Point(x, y), Color.BLACK, FRONT_INS_ARROW_SIZE, TIP_SIZE);
    }

    private Arrow rearInsArrowCell(int x, int y) {
        return new Arrow(new Point(x, y), Color.BLACK, REAR_INS_ARROW_SIZE, TIP_SIZE);
    }

    public void drawLink(Graphics g, int idx) {
        int x = 32 + 57 * (idx % 7);
        int y = 89 + 57 * (idx / 7);
        if (pg.getPerson(pg.currentIndex()) != null) {
            personCell(x, y - 5, pg.getPerson(idx)).draw(g);

            if (idx < pg.size() - 1) {
                if (idx % 7 != 6) {
                    arrowCell(x + 35, y + 8 - 4).draw(g);
                } else {
                    backArrowCell(x + 35, y + 8 - 4).draw(g);
                }
            }
        }

        if (idx == pg.currentIndex() && pg.insertingIndex() != null) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.lightGray);
        }

        int x1 = x + 17;
        int y1 = y + 17 - 3;
        g.drawLine(x1, y1, x1, y1 + 19);
        g.drawLine(x1 + 1, y1, x1 + 1, y1 + 19);
        g.drawLine(x1, y1, x1 + 6, y1 + 10);
        g.drawLine(x1 + 1, y1, x1 + 7, y1 + 10);
        g.drawLine(x1, y1, x1 - 6, y1 + 10);
        g.drawLine(x1 + 1, y1, x1 - 5, y1 + 10);
    }

    private void drawInsertLink(Graphics g) {
        int x = 15 + 57 * (pg.insertingIndex() % 7);
        int y = 115 + 57 * (pg.insertingIndex() / 7);

        personCell(x, y - 5, pg.insertingPerson()).draw(g);

        int var6 = y + 8 - 3;
        if (pg.insertingIndex() != 0) {
            removeArrowCell(x - 4, var6 - 27).draw(g);
            rearInsArrowCell(x - 4, var6 - 27).draw(g);
        }

        frontInsArrowCell(x + 35, var6 - 1).draw(g);
    }

    private void drawDeleteLink(Graphics var1) {
        int var2 = 32 + 57 * (pg.deletingIndex() % 7);
        int var3 = 89 + 57 * (pg.deletingIndex() / 7);
        var1.setColor(Color.lightGray);
        var1.fillRect(var2 - 10, var3 - 5, 46, 18);
        if (pg.deletingIndex() == pg.size() - 1) {
            var1.setColor(Color.lightGray);
        } else {
            var1.setColor(Color.black);
        }

        var1.drawLine(var2 - 20 - 1, var3 + 8 - 4, var2 + 35 + 7, var3 + 8 - 4);
    }
}
