package workshop.ch06.towers.gg;

import workshop.ch06.towers.operation.Operation;
import workshop.ch06.towers.operation.OperationMode;
import workshop.ch06.towers.operation.StepOperation;
import workshop.ch06.towers.support.Utils;

public class MutableGameImpl implements MutableGame {
    private String note;
    private final int disksCount;
    private final Tower[] towers;

    private int opMode;
    private int codePart;
    private int from;
    private int to;
    private boolean isDone;

    private Operation operation;

    public MutableGameImpl(int disksCount) {
        this.disksCount = disksCount;
        this.towers = new Tower[3];
        this.towers[0] = new Tower(Utils.nextColor(), "A", disksCount);
        this.towers[1] = new Tower(Utils.nextColor(), "B", disksCount);
        this.towers[2] = new Tower(Utils.nextColor(), "C", disksCount);

        for (int var2 = 0; var2 < this.disksCount; ++var2) {
            Disk var8 = new Disk(Utils.nextColor(), this.disksCount - var2 - 1);
            this.towers[0].insertDisk(var8);
        }

        this.note = "Press any button, or drag Disk to another post";
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

    public void creationError() {
        this.note = "Before using New, enter number of disks (1 to 10)";
    }

    public void startDrag(int var1, int var2) {
        this.from = this.closeTo(var1, var2);
        if (this.from == -1) {
            this.note = "DRAG the CENTER of the Disk";
        } else if (this.towers[this.from].isEmpty()) {
            this.note = "NO DISKS on tower " + this.towers[this.from].name();
            this.from = -1;
        } else {
            this.note = "Dragging from tower " + this.towers[this.from].name();
        }
    }

    public void endDrag(int var1, int var2) {
        this.to = this.closeTo(var1, var2);
        if (this.from != -1 && this.to != -1 && this.from != this.to) {
            this.note = "Dragged to tower " + this.towers[this.to].name();
            if (!this.towers[this.to].isEmpty() && this.towers[this.from].peekDisk().num() > this.towers[this.to].peekDisk().num()) {
                this.note = "Must put a SMALLER Disk ON a LARGER Disk";
            } else {
                Disk var3 = this.towers[this.from].removeDisk();
                this.towers[this.to].insertDisk(var3);
                this.note = "Moved Disk " + var3.label() + " from " + this.towers[this.from].name() + " to " + this.towers[this.to].name();
                if (this.towers[2].isFull()) {
                    this.note = "Congratulations! You moved all the disks!";
                } else {
                }
            }
        } else {
            this.note = "Drag a colored DISK to a different black TOWER";
        }
    }

    public int closeTo(int var1, int var2) {
        byte var3 = 35;
        if (Math.abs(var1 - 80) < var3) {
            return 0;
        } else if (Math.abs(var1 - 220) < var3) {
            return 1;
        } else {
            return Math.abs(var1 - 360) < var3 ? 2 : -1;
        }
    }

    public void step() {
        if (operation == null || operation.mode() != OperationMode.STEP) {
            operation = new StepOperation(this);
        }
        operation.run(null);
    }

    public void warningNew() {
        this.note = "ARE YOU SURE? Press again to reset game";
    }
}
