package workshop.ch06.towers.gg;

import workshop.ch06.towers.operation.NewOperation;
import workshop.ch06.towers.operation.Operation;
import workshop.ch06.towers.operation.OperationMode;
import workshop.ch06.towers.operation.StepOperation;
import workshop.ch06.towers.support.Utils;
import workshop.ch06.towers.swing.shape.TowersShape;

public class GameImpl implements Game {
    private String note;
    private int disksCount;
    private final Tower[] towers = new Tower[3];
    private boolean isDone;
    private Operation operation;
    private Tower from;

    public GameImpl(int disksCount) {
        reset(disksCount);
    }

    @Override
    public String note() {
        return note;
    }

    @Override
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public Tower[] towers() {
        return towers;
    }

    @Override
    public Tower tower(int index) {
        return towers[index];
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public int disksCount() {
        return disksCount;
    }

    @Override
    public void reset(int disksCount) {
        this.disksCount = disksCount;

        towers[0] = new Tower(Utils.nextColor(), "A", disksCount);
        towers[1] = new Tower(Utils.nextColor(), "B", disksCount);
        towers[2] = new Tower(Utils.nextColor(), "C", disksCount);

        for (int i = 0; i < disksCount; ++i) {
            towers[0].insertDisk(new Disk(Utils.nextColor(), disksCount - i - 1));
        }

        note = "Press any button, or drag Disk to another post";
    }

    @Override
    public void startDrag(int x, int y) {
        from = closeTo(x, y);
        if (from == null) {
            note = "DRAG the CENTER of the Disk";
        } else if (from.isEmpty()) {
            note = "NO DISKS on tower " + from.name();
            from = null;
        } else {
            note = "Dragging from tower " + from.name();
        }
    }

    @Override
    public void endDrag(int x, int y) {
        Tower to = closeTo(x, y);

        if (from == null || to == null || from == to) {
            note = "Drag a colored DISK to a different black TOWER";
            return;
        }

        note = "Dragged to tower " + to.name();
        if (!to.isEmpty() && from.peekDisk().num() > to.peekDisk().num()) {
            note = "Must put a SMALLER Disk ON a LARGER Disk";
            return;
        }

        Disk disk = from.removeDisk();
        to.insertDisk(disk);
        note = String.format(
                "Moved Disk %s from %s to %s", disk.label(), from.name(), to.name()
        );
        if (towers[2].isFull()) {
            note = "Congratulations! You moved all the disks!";
        }
    }

    private Tower closeTo(int x, int y) {
        return new TowersShape(this).getTowerByCoords(x, y);
    }

    @Override
    public void newGame(Integer disksCount) {
        if (operation == null || operation.mode() != OperationMode.NEW) {
            operation = new NewOperation(this);
        }
        operation.run(disksCount);
    }

    @Override
    public void step() {
        if (operation == null || operation.mode() != OperationMode.STEP) {
            operation = new StepOperation(this);
        }
        operation.run(null);
    }
}
