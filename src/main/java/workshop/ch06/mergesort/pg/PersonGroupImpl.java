package workshop.ch06.mergesort.pg;

import workshop.ch06.mergesort.operation.StepOperation;
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
    private int lower;
    private int upper;
    private int mid;
    private int ptr;
    private StepOperation operation;

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
        lower = 0;
        upper = 0;
        mid = 0;
        ptr = -1;
        operation = new StepOperation(this);
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
        if (size == Size.HUGE) {
            return new ArrowText[]{
                    new ArrowText(Color.RED, true, null, lower, 0),
                    new ArrowText(Color.RED, true, null, upper, 1),
                    new ArrowText(Color.BLUE, true, null, mid, 2),
            };
        }

        var midArrowText = ptr < 0
                ? new ArrowText(Color.BLUE, true, "mid", mid, 2)
                : new ArrowText(Color.MAGENTA, true, "ptr", ptr, 2);

        return new ArrowText[]{
                new ArrowText(Color.RED, true, "lower", lower, 0),
                new ArrowText(Color.RED, true, "upper", upper, 1),
                midArrowText,
                new ArrowText(Color.BLACK, false, note, 0, 3)
        };
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

    @Override
    public int lower() {
        return lower;
    }

    @Override
    public void setLower(int lower) {
        this.lower = lower;
    }

    @Override
    public int upper() {
        return upper;
    }

    @Override
    public void setUpper(int upper) {
        this.upper = upper;
    }

    @Override
    public int mid() {
        return mid;
    }

    @Override
    public void setMid(int mid) {
        this.mid = mid;
    }

    @Override
    public void setPtr(int ptr) {
        this.ptr = ptr;
    }

    @Override
    public void sortStep() {
        operation.run();
    }
}
