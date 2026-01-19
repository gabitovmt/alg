package workshop.ch02;

import java.util.Arrays;

public abstract class BasePersonGroup implements PersonGroup {
    protected static final String DEFAULT_NOTE = "Press any button";
    protected static final int MAX_HEIGHT = 999;
    protected static final int MAX_SIZE = 60;

    private Person[] persons;
    private int size = 0;

    private String note = DEFAULT_NOTE;

    private int position = 0;
    private int prevPosition = 0;

    private boolean isShowRange = false;
    private int lowerBound = -1;
    private int upperBound = -1;

    private boolean hasDuplicate = false;
    private boolean canChangeDuplicate = false;

    private boolean isLinearSearch = true;
    private boolean canChangeSearch = false;

    protected Operation operation = new NoneOperation();

    protected BasePersonGroup(int size) {
        setPersons(new Person[size]);
    }

    @Override
    public Person[] getPersons() {
        return persons;
    }

    @Override
    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    @Override
    public Person getPerson(int index) {
        return persons[index];
    }

    @Override
    public Person getCurrentPerson() {
        return persons[position];
    }

    @Override
    public void setPerson(int index, Person person) {
        persons[index] = person;
    }

    @Override
    public void setCurrentPerson(Person person) {
        persons[position] = person;
    }

    @Override
    public int getCapacity() {
        return persons != null ? persons.length : 0;
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
    public String getNote() {
        return note;
    }

    @Override
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public void setDefaultNote() {
        setNote(DEFAULT_NOTE);
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public int getPrevPosition() {
        return prevPosition;
    }

    @Override
    public void resetPosition() {
        prevPosition = position = 0;
    }

    @Override
    public void setPosition(int position) {
        this.prevPosition = this.position;
        this.position = position;
    }

    @Override
    public void nextPosition() {
        setPosition(position + 1);
    }

    @Override
    public boolean isShowRange() {
        return isShowRange;
    }

    @Override
    public void setShowRange(boolean isShowRange) {
        this.isShowRange = isShowRange;
    }

    @Override
    public int getLowerBound() {
        return lowerBound;
    }

    @Override
    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    @Override
    public int getUpperBound() {
        return upperBound;
    }

    @Override
    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public void resetBounds() {
        lowerBound = upperBound = -1;
    }

    @Override
    public int getMaxHeight() {
        return MAX_HEIGHT;
    }

    @Override
    public int getMaxSize() {
        return MAX_SIZE;
    }

    @Override
    public void doFill(int size) {
        Arrays.fill(getPersons(), null);
        resetPosition();

        var prevOperation = operation;
        while (getSize() < size) {
            insert(null);
            int height = Utils.nextHeight();

            if (!hasDuplicate()) {
                while (getDuplicate(height) != -1) {
                    height = Utils.nextHeight();
                }
            }
            insert(height);

            while (!operation.atStart()) {
                insert(null);
            }
        }
        operation = prevOperation;
    }

    @Override
    public int getDuplicate(int value) {
        for (int idx = 0; idx < getCapacity(); ++idx) {
            if (getPerson(idx) != null && getPerson(idx).getHeight() == value) {
                return idx;
            }
        }

        return -1;
    }

    @Override
    public void checkDuplicates() {
        for (int i = 0; i < getCapacity() - 1; ++i) {
            for (int j = i + 1; j < getCapacity(); ++j) {
                if (getPerson(i) != null && getPerson(j) != null &&
                        getPerson(i).getHeight() == getPerson(j).getHeight()
                ) {
                    setNote("ERROR: " + i + " same as " + j);
                    return;
                }
            }
        }
    }

    @Override
    public boolean hasDuplicate() {
        return hasDuplicate;
    }

    @Override
    public void setHasDuplicate(boolean hasDuplicate) {
        if (canChangeDuplicate && hasDuplicate != this.hasDuplicate) {
            this.hasDuplicate = hasDuplicate;
        }

        if (!canChangeDuplicate) {
            setNote("To change duplication status, create array with New");
        }
    }

    @Override
    public void setCanChangeDuplicate(boolean canChangeDuplicate) {
        this.canChangeDuplicate = canChangeDuplicate;
    }

    @Override
    public boolean isLinearSearch() {
        return isLinearSearch;
    }

    @Override
    public void setLinearSearch(boolean isLinearSearch) {
        if (canChangeSearch && isLinearSearch != this.isLinearSearch) {
            this.isLinearSearch = isLinearSearch;
        }

        if (!canChangeSearch) {
            setNote("To change search type, create array with New");
        }
    }

    @Override
    public void setCanChangeSearch(boolean canChangeSearch) {
        this.canChangeSearch = canChangeSearch;
    }

    @Override
    public void newArray(Integer size) {
        if (operation.getMode() != OperationMode.NEW_ARRAY) {
            operation = newArrayOperation(this);
        }
        operation.run(size);
    }

    @Override
    public void fill(Integer size) {
        if (operation.getMode() != OperationMode.FILL) {
            operation = fillOperation(this);
        }
        operation.run(size);
    }

    @Override
    public void insert(Integer key) {
        if (operation.getMode() != OperationMode.INSERT) {
            operation = insertOperation(this);
        }
        operation.run(key);
    }

    @Override
    public void find(Integer key) {
        if (operation.getMode() != OperationMode.FIND) {
            operation = findOperation(this);
        }
        operation.run(key);
    }

    @Override
    public void delete(Integer key) {
        if (operation.getMode() != OperationMode.DELETE) {
            operation = deleteOperation(this);
        }
        operation.run(key);
    }

    protected abstract Operation newArrayOperation(BasePersonGroup group);
    protected abstract Operation fillOperation(BasePersonGroup group);
    protected abstract Operation insertOperation(BasePersonGroup group);
    protected abstract Operation findOperation(BasePersonGroup group);
    protected abstract Operation deleteOperation(BasePersonGroup group);
}
