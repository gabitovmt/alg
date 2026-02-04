package workshop.ch05.pg;

import workshop.ch05.operation.NewListOperation;
import workshop.ch05.operation.Operation;
import workshop.ch05.operation.OperationMode;

import java.util.Arrays;
import java.util.Comparator;

public class PersonGroupImpl implements MutablePersonGroup {
    private static final String DEFAULT_NOTE = "Press any button";
    private static final int MAX_SIZE = 28;

    private final Link[] a = new Link[MAX_SIZE];
    private int size = 0;
    private Operation operation;

    private Person tempPers;
    private String note;
    private int insKey;
    private int findKey;
    private int delKey;
    private int codePart;
    private int codePart2;
    private int opMode;

    private int curIn;

    private boolean isSorted;
    private boolean canChangeSort;

    private int insDex;
    private boolean areInserting;
    private boolean insertAtEnd;

    private int delDex;
    private boolean areDeleting;

    public PersonGroupImpl() {
        note = DEFAULT_NOTE;
        operation = new NewListOperation(this);
        codePart = 1;
        codePart2 = 1;
    }

    public void newList(Integer size) {
        this.opMode = 1;
        if (operation.getMode() != OperationMode.NEW_LIST) {
            operation = new NewListOperation(this);
        }
        operation.run(size);
    }

    public void insert(Integer var2) {
        this.areDeleting = false;
        if (this.opMode != 3) {
            this.opMode = 3;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.curIn = 0;
                this.insertAtEnd = false;
                this.note = "Enter key of item to insert";
                this.codePart = 2;
                return;
            case 2:
                if (var2 != null && var2 >= 0 && var2 <= 999) {
                    if (this.size >= 28) {
                        this.note = "CAN'T INSERT: no room in display";
                        this.codePart = 6;
                    } else {
                        this.insKey = var2;
                        this.tempPers = new Person(this.insKey, Utils.nextColor());
                        if (!this.isSorted) {
                            this.note = "Will insert item with key " + this.insKey;
                            this.codePart = 4;
                        } else {
                            this.note = "Will search for insertion point";
                            this.codePart = 3;
                        }
                    }
                } else {
                    this.note = "CAN'T INSERT: need key between 0 and " + 999;
                    this.codePart = 1;
                }
                return;
            case 3:
                if (this.curIn == this.size - 1 && this.insKey > this.a[this.curIn].item().height()) {
                    this.note = "Found insertion point at end of list";
                    this.insertAtEnd = true;
                    this.codePart = 5;
                } else if (this.insKey > this.a[this.curIn].item().height()) {
                    this.note = "Searching for insertion point";
                    this.curIn++;
                    this.codePart = 3;
                } else {
                    this.note = "Have found insertion point";
                    this.codePart = 4;
                }
                return;
            case 4:
                this.areInserting = true;
                if (!this.isSorted) {
                    this.insDex = 0;
                } else {
                    this.insDex = this.curIn;
                }

                this.note = "Inserted item; will redraw list";
                this.codePart = 5;
                return;
            case 5:
                if (this.insertAtEnd) {
                    this.curIn++;
                    this.note = "Inserted item with key " + this.insKey + " at end of list";
                } else {
                    this.areInserting = false;

                    for (int var3 = this.size; var3 > this.curIn; --var3) {
                        this.a[var3] = this.a[var3 - 1];
                    }

                    this.note = "Inserted item with key " + this.insKey;
                }

                this.a[this.curIn] = new Link(this.tempPers);
                ++this.size;
                this.codePart = 6;
                return;
            case 6:
                this.note = "Insertion completed; total items = " + this.size;
                this.codePart = 7;
                return;
            case 7:
                this.curIn = 0;
                this.note = "Press any button";
                this.codePart = 1;
                return;
            default:
        }
    }

    public void find(Integer var2) {
        this.areInserting = false;
        this.areDeleting = false;
        if (this.opMode != 4) {
            this.opMode = 4;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.note = "Enter key of item to find";
                this.codePart = 2;
                break;
            case 2:
                if (var2 != null && var2 >= 0 && var2 <= 999) {
                    this.findKey = var2;
                    this.curIn = 0;
                    this.note = "Looking for item with key " + this.findKey;
                    this.codePart = 3;
                } else {
                    this.note = "ERROR: use key between 0 and " + 999;
                    this.codePart = 1;
                }
                break;
            case 3:
                if (this.a[this.curIn].item().height() == this.findKey) {
                    this.note = "Have found item with key " + this.findKey;
                    this.codePart = 6;
                } else if (this.curIn != this.size - 1 && (!this.isSorted || this.a[this.curIn].item().height() <= this.findKey)) {
                    this.note = "Searching for item with key " + this.findKey;
                    this.curIn++;
                    this.codePart = 3;
                } else {
                    this.note = "Can't locate item with key " + this.findKey;
                    this.codePart = 6;
                }
            case 4:
            case 5:
            default:
                break;
            case 6:
                this.curIn = 0;
                this.note = "Press any button";
                this.codePart = 1;
        }
    }

    public void delete(Integer var2) {
        this.areInserting = false;
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
                    this.curIn = 0;
                    this.note = "Looking for item with key " + this.delKey;
                    this.codePart = 3;
                } else {
                    this.note = "ERROR: use key between 0 and " + 999;
                    this.codePart = 1;
                }
                return;
            case 3:
                if (this.a[this.curIn].item().height() == this.delKey) {
                    this.note = "Have found item with key " + this.delKey;
                    if (this.curIn == this.size - 1) {
                        this.codePart = 5;
                    } else {
                        this.codePart = 4;
                    }
                } else if (this.curIn != this.size - 1 && (!this.isSorted || this.a[this.curIn].item().height() <= this.delKey)) {
                    this.note = "Searching for item with key " + this.delKey;
                    this.curIn++;
                    this.codePart = 3;
                } else {
                    this.note = "Can't locate item with key " + this.delKey;
                    this.codePart = 6;
                }

                return;
            case 4:
                this.areDeleting = true;
                this.delDex = this.curIn;
                this.note = "Deleted item; will redraw list";
                this.codePart = 5;
                return;
            case 5:
                this.areDeleting = false;

                for (int var3 = this.curIn; var3 < this.size - 1; ++var3) {
                    this.a[var3] = this.a[var3 + 1];
                }

                --this.size;
                this.curIn = 0;
                this.note = "Deleted item with key " + this.delKey;
                this.codePart = 6;
                return;
            case 6:
                this.curIn = 0;
                this.note = "Press any button";
                this.codePart = 1;
                return;
            default:
        }
    }

    @Override
    public void reset() {
        curIn = 0;
        areInserting = false;
        insDex = 0;
        areDeleting = false;
        delDex = 0;
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
        return a[index].item();
    }

    @Override
    public Person insertingPerson() {
        return tempPers;
    }

    @Override
    public void setInsertingPerson(Person person) {
        tempPers = person;
    }

    @Override
    public Integer insertingIndex() {
        return areInserting ? insDex : null;
    }

    @Override
    public void setInsertingIndex(Integer index) {
        areInserting = index != null;
        insDex = index != null ? index : 0;
    }

    @Override
    public Integer deletingIndex() {
        return areDeleting ? delDex : null;
    }

    @Override
    public void setDeletingIndex(Integer index) {
        areDeleting = index != null;
        delDex = index != null ? index : 0;
    }

    @Override
    public int currentIndex() {
        return curIn;
    }

    @Override
    public void setCurrentIndex(int index) {
        curIn = index;
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
