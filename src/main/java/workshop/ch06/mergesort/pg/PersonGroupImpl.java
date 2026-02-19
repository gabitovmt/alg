package workshop.ch06.mergesort.pg;

import workshop.ch06.mergesort.support.Stack;
import workshop.ch06.mergesort.support.Utils;

import java.awt.*;
import java.util.Random;

public class PersonGroupImpl implements PersonGroup {
    private static final int MAX_HEIGHT = 200;
    private static final Random RANDOM = new Random();

    private boolean isDone;
    private String note;
    private Size size = Size.NORMAL;
    private Order order = Order.RANDOM;
    private int comparisons;
    private int copies;
    private Person[] persons;

    public PersonGroupImpl() {
        reset();
    }

    @Override
    public Person[] persons() {
        return persons;
    }

    @Override
    public void reset() {
        switch (size) {
            case NORMAL -> persons = new Person[12];
            case HUGE -> persons = new Person[100];
            default -> throw new IllegalArgumentException("Unsupported size");
        }

        switch (order) {
            case RANDOM -> {
                for (int i = 0; i < persons.length; i++) {
                    persons[i] = new Person(RANDOM.nextInt(MAX_HEIGHT), Utils.nextColor());
                }
            }
            case DESC -> {
                for (int i = 0; i < persons.length; i++) {
                    int height = MAX_HEIGHT - MAX_HEIGHT * i / persons.length;
                    persons[i] = new Person(height, Utils.nextColor());
                }
            }
            default -> throw new IllegalArgumentException("Unsupported order");
        }

        note = "Press any button";
        comparisons = 0;
        copies = 0;
        codePart = 1;
        theStack = new Stack<>(params.class, persons.length);
        workSpace = new Person[persons.length * 2];
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void setDone() {
        isDone = true;
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
    public ArrowText[] arrowTexts() {
//        var1.setColor(Color.lightGray);
//        var1.fillRect(0, 230, 370, 78);
//        if (this.aSize == 12) {
//            this.arrowText(var1, Color.red, "lower", this.lower, 1, true, true);
//            this.arrowText(var1, Color.red, "upper", this.upper, 2, true, true);
//            int var3 = this.lowerBoundM + this.jM - 1;
//            if (this.oldCodePart == 9) {
//                this.arrowText(var1, Color.magenta, "ptr", this.lowerBoundM, 3, true, true);
//            } else if (this.oldCodePart == 10) {
//                this.arrowText(var1, Color.magenta, "ptr", var3, 3, true, true);
//            } else {
//                this.arrowText(var1, Color.blue, "mid", this.mid, 3, true, true);
//            }
//
//            this.arrowText(var1, Color.black, this.note, 0, 4, false, true);
//        } else {
//            this.arrowText(var1, Color.red, "xxx", this.lower, 1, true, false);
//            this.arrowText(var1, Color.red, "xxx", this.upper, 2, true, false);
//            this.arrowText(var1, Color.blue, "xxx", this.mid, 3, true, false);
//        }
        if (size == Size.HUGE) {
            return new ArrowText[]{
                    new ArrowText(Color.RED, true, null, lower, 0),
                    new ArrowText(Color.RED, true, null, upper, 1),
                    new ArrowText(Color.BLUE, true, null, mid, 2),
            };
        }

        var at = new ArrowText[]{
                new ArrowText(Color.RED, true, "lower", lower, 0),
                new ArrowText(Color.RED, true, "upper", upper, 1),
                null,
                new ArrowText(Color.BLACK, false, note, 0, 3)
        };
        at[2] = new ArrowText(Color.BLUE, true, "mid", mid, 2);

        return at;
    }

    @Override
    public void switchSize() {
        size = size == Size.NORMAL ? Size.HUGE : Size.NORMAL;
        reset();
    }

    @Override
    public void switchOrder() {
        order = order == Order.RANDOM ? Order.DESC : Order.RANDOM;
        reset();
    }

    @Override
    public Size size() {
        return size;
    }

    @Override
    public Order order() {
        return order;
    }

    @Override
    public int comparisons() {
        return comparisons;
    }

    @Override
    public int copies() {
        return copies;
    }

    @Override
    public void incComparisons() {
        comparisons++;
    }

    @Override
    public void incCopies() {
        copies++;
    }

    private record params(int lower, int upper, int mid, int codePart) {
    }

    private Person[] workSpace;
    private boolean doneFlag;
    private int codePart;
    private int drawMode;
    private int oldCodePart;
    private Stack<params> theStack;
    private params theseParams;
    private int lower;
    private int upper;
    private int mid;
    private int lowPtrM;
    private int highPtrM;
    private int upperBoundM;
    private int lowerBoundM;
    private int jM;
    private int midM;
    private int nM;
    private boolean mergingFlag;/*

    public PersonGroup(int var1, int var2) {
        this.workSpace = new person[this.aSize * 2];
    }

    public void arrowText(Graphics var1, Color var2, String var3, int var4, int var5, boolean var6, boolean var7) {
        int var8 = 35 + var4 * (this.barWidth + this.barSeparation);
        int var9 = 230 + (var5 + 1) * 13;
        var1.setColor(var2);
        if (var7) {
            var1.drawString(var3, var8, var9);
        }

        if (var6) {
            var1.drawLine(var8 + this.barWidth / 2, 232, var8 + this.barWidth / 2, var9 - 13);
            var1.drawLine(var8 + this.barWidth / 2, 232, var8 + this.barWidth / 2 - 3, 237);
            var1.drawLine(var8 + this.barWidth / 2, 232, var8 + this.barWidth / 2 + 3, 237);
        }
    }

    public void draw(Graphics var1) {
        var1.setColor(Color.lightGray);
        var1.fillRect(0, 230, 370, 78);
        if (this.aSize == 12) {
            this.arrowText(var1, Color.red, "lower", this.lower, 1, true, true);
            this.arrowText(var1, Color.red, "upper", this.upper, 2, true, true);
            int var3 = this.lowerBoundM + this.jM - 1;
            if (this.oldCodePart == 9) {
                this.arrowText(var1, Color.magenta, "ptr", this.lowerBoundM, 3, true, true);
            } else if (this.oldCodePart == 10) {
                this.arrowText(var1, Color.magenta, "ptr", var3, 3, true, true);
            } else {
                this.arrowText(var1, Color.blue, "mid", this.mid, 3, true, true);
            }

            this.arrowText(var1, Color.black, this.note, 0, 4, false, true);
        } else {
            this.arrowText(var1, Color.red, "xxx", this.lower, 1, true, false);
            this.arrowText(var1, Color.red, "xxx", this.upper, 2, true, false);
            this.arrowText(var1, Color.blue, "xxx", this.mid, 3, true, false);
        }
    }
*/

    @Override
    public void sortStep() {
        switch (this.codePart) {
            case 1:
                this.theseParams = new params(0, this.persons.length - 1, 0, 8);
                this.theStack.push(this.theseParams);
                this.note = "Initial call to mergeSort";
                this.oldCodePart = this.codePart;
                this.codePart = 2;
                return;
            case 2:
                this.theseParams = this.theStack.peek();
                this.lower = this.theseParams.lower;
                this.upper = this.theseParams.upper;
                this.note = "Entering mergeSort: " + this.lower + "-" + this.upper;
                this.oldCodePart = this.codePart;
                if (this.lower == this.upper) {
                    this.codePart = 7;
                } else {
                    this.codePart = 4;
                }

                this.drawMode = 0;
                return;
            case 3:
            default:
                return;
            case 4:
                this.mid = (this.lower + this.upper) / 2;
                this.note = "Will sort lower half: " + this.lower + "-" + this.mid;
                params var1 = new params(this.lower, this.mid, this.mid, 5);
                this.theStack.push(var1);
                this.drawMode = 0;
                this.oldCodePart = this.codePart;
                this.codePart = 2;
                return;
            case 5:
                this.theseParams = this.theStack.peek();
                this.lower = this.theseParams.lower;
                this.upper = this.theseParams.upper;
                this.mid = (this.lower + this.upper) / 2;
                this.note = "Will sort upper half: " + (this.mid + 1) + "-" + this.upper;
                params var2 = new params(this.mid + 1, this.upper, this.mid, 6);
                this.theStack.push(var2);
                this.drawMode = 0;
                this.oldCodePart = this.codePart;
                this.codePart = 2;
                return;
            case 6:
                this.theseParams = this.theStack.peek();
                this.lower = this.theseParams.lower;
                this.upper = this.theseParams.upper;
                this.mid = (this.lower + this.upper) / 2;
                this.note = "Will merge ranges";
                this.lowPtrM = this.lower;
                this.highPtrM = this.mid + 1;
                this.upperBoundM = this.upper;
                this.drawMode = 0;
                this.oldCodePart = this.codePart;
                this.codePart = 9;
                return;
            case 7:
                this.oldCodePart = this.codePart;
                this.codePart = this.theseParams.codePart;
                this.theStack.pop();
                if (!this.theStack.isEmpty()) {
                    this.theseParams = this.theStack.peek();
                    this.note = "Exiting mergeSort: " + this.lower + "-" + this.upper;
                } else {
                    this.note = "Exciting mergeSort; sort is complete";
                }

                this.drawMode = 0;
                return;
            case 8:
                this.doneFlag = true;
                this.note = "Sort is complete; Press New or Size";
                this.drawMode = 0;
                this.oldCodePart = this.codePart;
                this.codePart = 1;
                return;
            case 9:
                this.jM = 0;
                this.lowerBoundM = this.lowPtrM;
                this.midM = this.highPtrM - 1;
                this.nM = this.upperBoundM - this.lowerBoundM + 1;
                this.note = "Merged " + this.lowPtrM + "-" + this.midM + " and " + this.highPtrM + "-" + this.upperBoundM + " into workspace";

                while (this.lowPtrM <= this.midM && this.highPtrM <= this.upperBoundM) {
                    ++this.comparisons;
                    ++this.copies;
                    if (this.persons[this.lowPtrM].height() < this.persons[this.highPtrM].height()) {
                        this.workSpace[this.jM++] = this.persons[this.lowPtrM++];
                    } else {
                        this.workSpace[this.jM++] = this.persons[this.highPtrM++];
                    }
                }

                while (this.lowPtrM <= this.midM) {
                    ++this.copies;
                    this.workSpace[this.jM++] = this.persons[this.lowPtrM++];
                }

                while (this.highPtrM <= this.upperBoundM) {
                    ++this.copies;
                    this.workSpace[this.jM++] = this.persons[this.highPtrM++];
                }

                this.mergingFlag = true;
                this.jM = 0;
                this.oldCodePart = this.codePart;
                this.codePart = 10;
                this.drawMode = 0;
                return;
            case 10:
                this.oldCodePart = this.codePart;
                if (this.jM == this.nM) {
                    this.note = "Merge completed";
                    this.codePart = 7;
                    this.drawMode = 0;
                } else {
                    ++this.copies;
                    this.persons[this.lowerBoundM + this.jM] = this.workSpace[this.jM];
                    this.note = "Copied workspace into " + (this.lowerBoundM + this.jM);
                    ++this.jM;
                    this.codePart = 10;
                    this.drawMode = 1;
                }
        }
    }
}
