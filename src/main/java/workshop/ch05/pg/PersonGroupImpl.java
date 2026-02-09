package workshop.ch05.pg;

import workshop.ch05.operation.DeleteOperation;
import workshop.ch05.operation.FindOperation;
import workshop.ch05.operation.InsertOperation;
import workshop.ch05.operation.NewListOperation;
import workshop.ch05.operation.Operation;
import workshop.ch05.operation.OperationMode;

import java.util.Arrays;
import java.util.Comparator;

public class PersonGroupImpl implements MutablePersonGroup {
    private static final String DEFAULT_NOTE = "Press any button";

    private String note = DEFAULT_NOTE;

    private final Link[] a = new Link[Constants.MAX_SIZE];
    private int size = 0;

    private Person insertingPerson;
    private int currentIndex;
    private Integer insertingIndex;
    private Integer deletingIndex;

    private boolean isSorted;
    private boolean canChangeSort;

    private Operation operation = new NewListOperation(this);

    public void newList(Integer size) {
        if (operation.getMode() != OperationMode.NEW_LIST) {
            operation = new NewListOperation(this);
        }
        operation.run(size);
    }

    public void insert(Integer insKey) {
        if (operation.getMode() != OperationMode.INSERT) {
            operation = new InsertOperation(this);
        }
        operation.run(insKey);
    }

    public void find(Integer findKey) {
        if (operation.getMode() != OperationMode.FIND) {
            operation = new FindOperation(this);
        }
        operation.run(findKey);
    }

    public void delete(Integer delKey) {
        if (operation.getMode() != OperationMode.DELETE) {
            operation = new DeleteOperation(this);
        }
        operation.run(delKey);
    }

    @Override
    public void reset() {
        currentIndex = 0;
        insertingIndex = null;
        deletingIndex = null;
        insertingPerson = null;
        canChangeSort = false;
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
    public int size() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void doFill(int size) {
        this.size = size;
        Arrays.fill(a, null);
        reset();

        for (int i = 0; i < this.size; ++i) {
            a[i] = new Link(Utils.nextPerson());
        }

        if (isSorted) {
            Arrays.sort(a, Comparator.nullsLast(Comparator.comparing((Link it) -> it.item().height())));
        }
    }

    @Override
    public Person getPerson(int index) {
        return a[index] != null ? a[index].item() : null;
    }

    @Override
    public Person getCurrentPerson() {
        return getPerson(currentIndex);
    }

    @Override
    public void setPerson(int index, Person person) {
        a[index] = new Link(person);
    }

    @Override
    public Person insertingPerson() {
        return insertingPerson;
    }

    @Override
    public void setInsertingPerson(Person person) {
        insertingPerson = person;
    }

    @Override
    public Integer insertingIndex() {
        return insertingIndex;
    }

    @Override
    public void setInsertingIndex(Integer index) {
        insertingIndex = index;
    }

    @Override
    public Integer deletingIndex() {
        return deletingIndex;
    }

    @Override
    public void setDeletingIndex(Integer index) {
        deletingIndex = index;
    }

    @Override
    public int currentIndex() {
        return currentIndex;
    }

    @Override
    public void setCurrentIndex(int index) {
        currentIndex = index;
    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }

    @Override
    public void setSorted(boolean isSorted) {
        if (canChangeSort) {
            this.isSorted = isSorted;
        } else {
            note = "To change sort status, create list with New";
        }
    }

    @Override
    public void setCanChangeSorted(boolean canChange) {
        canChangeSort = canChange;
    }
}
