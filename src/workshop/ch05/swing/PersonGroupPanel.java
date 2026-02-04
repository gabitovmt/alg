package workshop.ch05.swing;

import workshop.ch05.pg.Person;
import workshop.ch05.pg.PersonGroup;
import workshop.ch05.swing.shape.Arrow;
import workshop.ch05.swing.shape.Background;
import workshop.ch05.swing.shape.Cell;
import workshop.ch05.swing.shape.Direction;
import workshop.ch05.swing.shape.Line;
import workshop.ch05.swing.shape.Note;
import workshop.ch05.swing.shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class PersonGroupPanel extends JPanel {
    private static final Font FONT = new Font("SansSerif", Font.BOLD, 12);

    private static final Point NOTE_POINT = new Point(16, 16);
    private static final Point MAIN_POINT = new Point(48, 40);

    private static final int CELL_COLS = 8;
    private static final Dimension CELL_SIZE = new Dimension(40, 24);
    private static final Dimension CELL_SPACING = new Dimension(32, 40);
    private static final Dimension NEW_CELL_OFFSET = new Dimension(-CELL_SIZE.width + 8, CELL_SIZE.height + 8);

    private static final List<Arrow.Line> ARROW_SIZE = List.of(new Arrow.Line(CELL_SPACING.width, Direction.EAST));
    private static final List<Arrow.Line> BACK_ARROW_SIZE = List.of(
            new Arrow.Line(CELL_SPACING.width / 2, Direction.EAST),
            new Arrow.Line((CELL_SIZE.height + CELL_SPACING.height) * 3 / 4, Direction.SOUTH),
            new Arrow.Line((CELL_SIZE.width + CELL_SPACING.width) * CELL_COLS, Direction.WEST),
            new Arrow.Line((CELL_SIZE.height + CELL_SPACING.height) / 4, Direction.SOUTH),
            new Arrow.Line(CELL_SPACING.width / 2, Direction.EAST)
    );
    private static final List<Arrow.Line> FRONT_INS_ARROW_SIZE = List.of(
            new Arrow.Line(8, Direction.EAST),
            new Arrow.Line(16, Direction.NORTH),
            new Arrow.Line(24, Direction.WEST),
            new Arrow.Line(16, Direction.NORTH),
            new Arrow.Line(8, Direction.EAST)
    );
    private static final List<Arrow.Line> REAR_INS_ARROW_SIZE = List.of(
            new Arrow.Line(8, Direction.EAST),
            new Arrow.Line(16, Direction.SOUTH),
            new Arrow.Line(16, Direction.WEST),
            new Arrow.Line(16, Direction.SOUTH),
            new Arrow.Line(8, Direction.EAST)
    );
    private static final List<Arrow.Line> BACK_REAR_INS_ARROW_SIZE = List.of(
            new Arrow.Line(BACK_ARROW_SIZE.get(0).size(), Direction.EAST),
            new Arrow.Line(BACK_ARROW_SIZE.get(1).size(), Direction.SOUTH),
            new Arrow.Line(BACK_ARROW_SIZE.get(2).size() + 24, Direction.WEST),
            new Arrow.Line(BACK_ARROW_SIZE.get(3).size() + 32, Direction.SOUTH),
            new Arrow.Line(BACK_ARROW_SIZE.get(4).size() - 8, Direction.EAST)
    );
    private static final Dimension TIP_SIZE = new Dimension(3, 5);
    private static final int DEL_LINE_WIDTH = CELL_SIZE.width + CELL_SPACING.width;

    private static final List<Arrow.Line> POSITION_SIZE = List.of(new Arrow.Line(24, Direction.NORTH));
    private static final Dimension POSITION_TIP_SIZE = new Dimension(6, 10);

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

        var shapes = new ArrayList<Shape>();
        shapes.add(new Background());
        shapes.add(new Note(NOTE_POINT, pg.getNote()));
        shapes.addAll(personCells());
        shapes.addAll(links());
        shapes.add(position());
        shapes.addAll(newPersonCellAndLinks());
        shapes.addAll(delLink());

        shapes.forEach(it -> it.draw(g));
    }

    private int personX(int idx) {
        return MAIN_POINT.x + (CELL_SIZE.width + CELL_SPACING.width) * (idx % CELL_COLS);
    }

    private int personY(int idx) {
        return MAIN_POINT.y + (CELL_SIZE.height + CELL_SPACING.height) * (idx / CELL_COLS);
    }

    private Cell personCell(int x, int y, Person person) {
        var cellRect = new Rectangle(new Point(x, y), CELL_SIZE);
        var label = Integer.toString(person.height());

        return new Cell(cellRect, person.color(), label);
    }

    private Cell personCell(int idx) {
        return personCell(personX(idx), personY(idx), pg.getPerson(idx));
    }

    private List<Cell> personCells() {
        return IntStream.range(0, pg.size())
                .filter(idx -> !Objects.equals(idx, pg.deletingIndex()))
                .mapToObj(this::personCell)
                .toList();
    }

    private Arrow arrowCell(int x, int y) {
        return new Arrow(new Point(x, y), Color.BLACK, ARROW_SIZE, TIP_SIZE, false);
    }

    private Arrow backArrowCell(int x, int y) {
        return new Arrow(new Point(x, y), Color.BLACK, BACK_ARROW_SIZE, TIP_SIZE, false);
    }

    private Arrow link(int idx) {
        int x = personX(idx) + CELL_SIZE.width;
        int y = personY(idx) + CELL_SIZE.height / 2;

        return idx % CELL_COLS != CELL_COLS - 1
                ? arrowCell(x, y)
                : backArrowCell(x, y);
    }

    private List<Arrow> links() {
        return IntStream.range(0, pg.size() - 1)
                .filter(idx -> !Objects.equals(idx + 1, pg.insertingIndex())
//                        && !Objects.equals(idx, pg.deletingIndex())
                        && !Objects.equals(idx + 1, pg.deletingIndex()))
                .mapToObj(this::link)
                .toList();
    }

    private Arrow position() {
        int x = personX(pg.currentIndex()) + CELL_SIZE.width * 3 / 4;
        int y = personY(pg.currentIndex()) + CELL_SIZE.height + POSITION_SIZE.get(0).size();
        var color = pg.insertingIndex() != null || pg.deletingIndex() != null ? Color.RED : Color.BLACK;

        return new Arrow(new Point(x, y), color, POSITION_SIZE, POSITION_TIP_SIZE, true);
    }

    private Arrow frontInsArrowCell(int x, int y) {
        return new Arrow(new Point(x, y), Color.BLACK, FRONT_INS_ARROW_SIZE, TIP_SIZE, false);
    }

    private Arrow rearInsArrowCell(int x, int y) {
        return new Arrow(new Point(x, y), Color.BLACK, REAR_INS_ARROW_SIZE, TIP_SIZE, false);
    }

    private Arrow backRearInsArrowCell(int x, int y) {
        return new Arrow(new Point(x, y), Color.BLACK, BACK_REAR_INS_ARROW_SIZE, TIP_SIZE, false);
    }

    private List<Shape> newPersonCellAndLinks() {
        if (pg.insertingPerson() == null) {
            return List.of();
        }

        int idx = pg.insertingIndex() != null ? pg.insertingIndex() : 0;
        int x = personX(idx) + NEW_CELL_OFFSET.width;
        int y = personY(idx) + NEW_CELL_OFFSET.height;

        Cell cell = personCell(x, y, pg.insertingPerson());
        if (pg.insertingIndex() == null) {
            return List.of(cell);
        }

        Arrow arrow = frontInsArrowCell(x + CELL_SIZE.width, y + CELL_SIZE.height / 2);
        if (pg.insertingIndex() == 0) {
            return List.of(cell, arrow);
        }

        if (pg.insertingIndex() % CELL_COLS == 0) {
            Arrow rearArrow = backRearInsArrowCell(
                    personX(idx - 1) + CELL_SIZE.width, personY(idx - 1) + CELL_SIZE.height / 2
            );
            return List.of(cell, arrow, rearArrow);
        }

        Arrow rearArrow = rearInsArrowCell(personX(idx) - CELL_SPACING.width, personY(idx) + CELL_SIZE.height / 2);
        return List.of(cell, arrow, rearArrow);
    }

    private List<Line> delLink() {
        if (pg.deletingIndex() == null || pg.deletingIndex() == 0) {
            return List.of();
        }

        int x = personX(pg.deletingIndex()) + CELL_SIZE.width;
        int y = personY(pg.deletingIndex()) + CELL_SIZE.height / 2;
        var line = new Line(new Rectangle(x, y, DEL_LINE_WIDTH, 0), Color.BLACK);

        return List.of(line);
    }
}
