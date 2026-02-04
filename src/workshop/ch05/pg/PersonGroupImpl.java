package workshop.ch05.pg;

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

    private int delKey;
    private int codePart;
    private int opMode;

    private boolean areDeleting;

    private Operation operation = new NewListOperation(this);

    public void newList(Integer size) {
        opMode = 1;
        if (operation.getMode() != OperationMode.NEW_LIST) {
            operation = new NewListOperation(this);
        }
        operation.run(size);
    }

    public void insert(Integer insKey) {
        opMode = 3;
        if (operation.getMode() != OperationMode.INSERT) {
            operation = new InsertOperation(this);
        }
        operation.run(insKey);
    }

    public void find(Integer findKey) {
        opMode = 4;
        if (operation.getMode() != OperationMode.FIND) {
            operation = new FindOperation(this);
        }
        operation.run(findKey);
    }

    public void delete(Integer var2) {
        if (this.opMode != 5) {
            this.opMode = 5;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.note = "Enter key of item to delete";
                this.codePart = 2;
                return;
            case 2:
                if (var2 != null && var2 >= 0 && var2 <= 999) {
                    this.delKey = var2;
                    this.currentIndex = 0;
                    this.note = "Looking for item with key " + this.delKey;
                    this.codePart = 3;
                } else {
                    this.note = "ERROR: use key between 0 and " + 999;
                    this.codePart = 1;
                }
                return;
            case 3:
                if (this.a[this.currentIndex].item().height() == this.delKey) {
                    this.note = "Have found item with key " + this.delKey;
                    if (this.currentIndex == this.size - 1) {
                        this.codePart = 5;
                    } else {
                        this.codePart = 4;
                    }
                } else if (this.currentIndex != this.size - 1 && (!this.isSorted || this.a[this.currentIndex].item().height() <= this.delKey)) {
                    this.note = "Searching for item with key " + this.delKey;
                    this.currentIndex++;
                    this.codePart = 3;
                } else {
                    this.note = "Can't locate item with key " + this.delKey;
                    this.codePart = 6;
                }

                return;
            case 4:
                this.areDeleting = true;
                this.deletingIndex = this.currentIndex;
                this.note = "Deleted item; will redraw list";
                this.codePart = 5;
                return;
            case 5:
                this.areDeleting = false;

                for (int var3 = this.currentIndex; var3 < this.size - 1; ++var3) {
                    this.a[var3] = this.a[var3 + 1];
                }

                --this.size;
                this.currentIndex = 0;
                this.note = "Deleted item with key " + this.delKey;
                this.codePart = 6;
                return;
            case 6:
                this.currentIndex = 0;
                this.note = "Press any button";
                this.codePart = 1;
                return;
            default:
        }
    }

    @Override
    public void reset() {
        currentIndex = 0;
        insertingIndex = null;
        deletingIndex = null;
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
