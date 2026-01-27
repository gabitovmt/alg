package workshop.ch04.pg;

import java.util.Arrays;

public abstract class AbstractPersonGroup implements MutablePersonGroup {
    protected static final String DEFAULT_NOTE = "Press any button";

    protected final Person[] a = new Person[Constants.DEFAULT_CAPACITY];
    protected int size = 0;
    protected String note = DEFAULT_NOTE;

    @Override
    public int getCapacity() {
        return a.length;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Person getPerson(int index) {
        return a[index];
    }

    @Override
    public void setPerson(int idx, Person person) {
        a[idx] = person;
    }

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public void setDefaultNote() {
        note = DEFAULT_NOTE;
    }

    @Override
    public void reset() {
        Arrays.fill(a, null);
        size = 0;
    }
}
