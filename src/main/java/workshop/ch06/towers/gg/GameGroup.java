package workshop.ch06.towers.gg;

import workshop.ch06.towers.support.Utils;

public class GameGroup {
    private String note;
    private int opMode;
    private int codePart;
    private Tower[] towers;
    private int nDisks;
    private Stack<Params> theStack;
    private Params theseParams;
    private int n;
    private int from;
    private int to;
    private int inter;
    private boolean isDoneFlag;

    public GameGroup(int var1) {
        this.nDisks = var1;
        this.towers = new Tower[3];
        this.towers[0] = new Tower(Utils.nextColor(), "A", this.nDisks);
        this.towers[1] = new Tower(Utils.nextColor(), "B", this.nDisks);
        this.towers[2] = new Tower(Utils.nextColor(), "C", this.nDisks);

        for (int var2 = 0; var2 < this.nDisks; ++var2) {
            Disk var8 = new Disk(Utils.nextColor(), this.nDisks - var2 - 1);
            this.towers[0].insertDisk(var8);
        }

        this.theStack = new Stack<>(Params.class, this.nDisks);
        this.note = "Press any button, or drag Disk to another post";
    }

    public String note() {
        return note;
    }

    public Tower[] towers() {
        return towers;
    }

    public void creationError() {
        this.note = "Before using New, enter number of disks (1 to 10)";
    }

    public boolean isDone() {
        return this.isDoneFlag;
    }

    public void setDone(boolean var1) {
        this.isDoneFlag = var1;
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
        if (this.opMode != 1) {
            this.opMode = 1;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                if (!this.towers[0].isEmpty() && this.towers[1].isEmpty() && this.towers[2].isEmpty()) {
                    this.note = "Will shift all disks from A to C";
                    this.theseParams = new Params(this.nDisks, 0, 2, 1, 8);
                    this.theStack.push(this.theseParams);
                    this.codePart = 2;
                } else {
                    this.note = "You must begin with ALL DISKS ON TOWER A";
                    this.codePart = 1;
                }
                break;
            case 2:
                this.theseParams = this.theStack.peek();
                this.n = this.theseParams.n();
                this.from = this.theseParams.from();
                this.to = this.theseParams.to();
                this.inter = this.theseParams.inter();
                this.note = "Entering function with " + this.n + " disks";
                this.codePart = 3;
                break;
            case 3:
                if (this.n == 1) {
                    Disk var3 = this.towers[this.from].removeDisk();
                    this.towers[this.to].insertDisk(var3);
                    this.note = "Moved last Disk " + var3.label() + " from " + this.towers[this.from].name() + " to " + this.towers[this.to].name();
                    if (this.towers[2].isFull()) {
                        this.note = "Congratulations! You moved all the disks!";
                    }

                    this.codePart = 7;
                } else {
                    this.note = "More than one Disk, will continue";
                    this.codePart = 4;
                }
                break;
            case 4:
                this.note = "Will move top " + (this.n - 1) + " disks from " + this.towers[this.from].name() + " to " + this.towers[this.inter].name();
                this.theseParams = new Params(this.n - 1, this.from, this.inter, this.to, 5);
                this.theStack.push(this.theseParams);
                this.codePart = 2;
                break;
            case 5:
                Disk var1 = this.towers[this.from].removeDisk();
                this.towers[this.to].insertDisk(var1);
                this.note = "Moved remaining Disk " + this.n + " from " + this.towers[this.from].name() + " to " + this.towers[this.to].name();
                this.codePart = 6;
                break;
            case 6:
                this.note = "Will move top " + (this.n - 1) + " disks from " + this.towers[this.inter].name() + " to " + this.towers[this.to].name();
                this.theseParams = new Params(this.n - 1, this.inter, this.to, this.from, 7);
                this.theStack.push(this.theseParams);
                this.codePart = 2;
                break;
            case 7:
                int var2 = this.n;
                this.codePart = this.theseParams.codePart();
                this.theStack.pop();
                if (!this.theStack.isEmpty()) {
                    this.theseParams = this.theStack.peek();
                    this.n = this.theseParams.n();
                    this.from = this.theseParams.from();
                    this.to = this.theseParams.to();
                    this.inter = this.theseParams.inter();
                }

                this.note = "Returning from function with " + var2 + " disks";
                break;
            case 8:
                this.note = "Press New to reset";
                this.isDoneFlag = true;
                this.codePart = 1;
        }
    }

    public void warningNew() {
        this.note = "ARE YOU SURE? Press again to reset game";
    }
}
