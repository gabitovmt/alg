package workshop.ch02.array;

import java.util.Arrays;

public class PersonGroup {
    private static final String DEFAULT_NOTE = "Press any button";
    private static final int MAX_HEIGHT = 999;
    private static final int MAX_SIZE = 60;

    private Person[] persons;
    private int size = 0;
    private String note = DEFAULT_NOTE;
    private int pos = 0;
    private int prevPos = 0;
    private boolean dupsOK = false;
    private boolean isOKChangeDups = false;
    private Operation operation = new NoneOperation();

    public PersonGroup(int size) {
        persons = new Person[size];
    }

    public Person[] getPersons() {
        return persons;
    }

    public String getNote() {
        return note;
    }

    public boolean getDupsStatus() {
        return dupsOK;
    }

    public void setDupsStatus(boolean dups) {
        if (isOKChangeDups && dups != dupsOK) {
            dupsOK = dups;
        }

        if (!isOKChangeDups) {
            note = "To change duplication status, create array with New";
        }
    }

    public int getPos() {
        return pos;
    }

    public int getPrevPos() {
        return prevPos;
    }

    private void resetPosition() {
        prevPos = pos = 0;
    }

    private void setPosition(int value) {
        prevPos = pos;
        pos = value;
    }

    private void nextPosition() {
        setPosition(pos + 1);
    }

    private static class NoneOperation extends BaseOperation {
        NoneOperation() {
            super(OperationMode.NONE);
            addAction(1, it -> {
            });
        }
    }

    private class NewArrayOperation extends BaseOperation {
        private int newSize;

        NewArrayOperation() {
            super(OperationMode.NEW_ARRAY);

            addAction(1, it -> {
                resetPosition();
                note = "Enter size of array to create";
                setCodePart(2);
            });

            addAction(2, it -> {
                if (it != null && it >= 0 && it <= MAX_SIZE) {
                    newSize = it;
                    note = "Will create empty array with " + newSize + " cells";
                    setCodePart(3);
                } else {
                    note = "ERROR: use size between 0 and " + MAX_SIZE;
                    setCodePart(1);
                }
            });

            addAction(3, it -> {
                note = "Select Duplicates OK, or No Dups";
                isOKChangeDups = true;
                setCodePart(5);
            });

            addAction(5, it -> {
                persons = new Person[newSize];
                size = 0;
                note = "New array created; total items = " + size;
                isOKChangeDups = false;
                setCodePart(6);
            });

            addAction(6, it -> {
                note = DEFAULT_NOTE;
                setCodePart(1);
            });
        }
    }

    public void newArray(Integer value) {
        if (operation.getMode() != OperationMode.NEW_ARRAY) {
            operation = new NewArrayOperation();
        }
        operation.run(value);
    }

    private class FillOperation extends BaseOperation {
        private int fillSize;

        FillOperation() {
            super(OperationMode.FILL);

            addAction(1, it -> {
                note = "Enter number of items to fill in";
                setCodePart(2);
            });

            addAction(2, it -> {
                if (it != null && it >= 0 && it <= persons.length) {
                    fillSize = it;
                    note = "Will fill in " + fillSize + " items";
                    setCodePart(3);
                    return;
                }

                note = "ERROR: can't fill more than " + persons.length + " items";
                setCodePart(1);
            });

            addAction(3, it -> {
                size = 0;
                doFill(fillSize);
                resetPosition();
                note = "Fill completed; total items = " + size;
                if (!dupsOK) {
                    checkDups();
                }
                setCodePart(4);
            });

            addAction(4, it -> {
                note = DEFAULT_NOTE;
                setCodePart(1);
            });
        }
    }

    public void fill(Integer value) {
        if (operation.getMode() != OperationMode.FILL) {
            operation = new FillOperation();
        }
        operation.run(value);
    }

    public void doFill(int size) {
        Arrays.fill(persons, null);
        resetPosition();

        while (this.size < size) {
            var insOperation = new InsertOperation();
            insOperation.run(null);
            int height = Utils.nextHeight();

            if (!dupsOK) {
                while (getDuplicate(height) != -1) {
                    height = Utils.nextHeight();
                }
            }
            insOperation.run(height);
            while (!insOperation.atStart()) {
                insOperation.run(null);
            }
        }
    }

    private int getDuplicate(int value) {
        for (int idx = 0; idx < persons.length; ++idx) {
            if (persons[idx] != null && persons[idx].getHeight() == value) {
                return idx;
            }
        }

        return -1;
    }

    private void checkDups() {
        for (int i = 0; i < persons.length - 1; ++i) {
            for (int j = i + 1; j < persons.length; ++j) {
                if (persons[i] != null && persons[j] != null
                        && persons[i].getHeight() == persons[j].getHeight()
                ) {
                    note = "ERROR: " + i + " same as " + j;
                    return;
                }
            }
        }
    }

    private class InsertOperation extends BaseOperation {
        private int insKey;
        private Person newPerson;

        InsertOperation() {
            super(OperationMode.INSERT);

            addAction(1, it -> {
                resetPosition();
                note = "Enter key of item to insert";
                setCodePart(2);
            });

            addAction(2, it -> {
                if (it != null && it >= 0 && it <= MAX_HEIGHT) {
                    if (size >= persons.length) {
                        note = "CAN'T INSERT: array is full";
                        setCodePart(6);
                    } else {
                        insKey = it;
                        newPerson = new Person(insKey);
                        note = "Will insert item with key " + insKey;
                        setCodePart(4);
                    }
                } else {
                    note = "CAN'T INSERT: need key between 0 and " + MAX_HEIGHT;
                    setCodePart(1);
                }
            });

            addAction(4, it -> {
                setPosition(size);
                persons[pos] = newPerson;
                ++size;
                note = "Inserted item with key " + insKey + " at index " + pos;
                setCodePart(5);
            });

            addAction(5, it -> {
                note = "Insertion completed; total items = " + size;
                if (!dupsOK) {
                    checkDups();
                }
                setCodePart(6);
            });

            addAction(6, it -> {
                resetPosition();
                note = DEFAULT_NOTE;
                setCodePart(1);
            });
        }
    }

    public void insert(Integer value) {
        if (operation.getMode() != OperationMode.INSERT) {
            operation = new InsertOperation();
        }
        operation.run(value);
    }

    private class FindOperation extends BaseOperation {
        private int findKey;
        private boolean wasJustFound;

        FindOperation() {
            super(OperationMode.FIND);

            addAction(1, it -> run1());
            addAction(2, this::run2);
            addAction(3, it -> run3());
            addAction(4, it -> run4());
            addAction(6, it -> run6());
        }

        private void run1() {
            resetPosition();
            note = "Enter key of item to find";
            setCodePart(2);
        }

        private void run2(Integer value) {
            if (value != null && value >= 0 && value <= MAX_HEIGHT) {
                findKey = value;
                note = "Looking for item with key " + findKey;
                setCodePart(3);
            } else {
                note = "ERROR: use key between 0 and " + MAX_HEIGHT;
                setCodePart(1);
            }
        }

        private void run3() {
            if (pos >= size) {
                note = "Can't locate item with key " + findKey;
                setCodePart(6);
            } else if (persons[pos].getHeight() == findKey) {
                note = "Have found item with key " + findKey;
                wasJustFound = true;
                setCodePart(dupsOK ? 4 : 6);
            } else {
                nextPosition();
                note = "Checking next cell; index = " + pos;
                setCodePart(3);
            }
        }

        private void run4() {
            if (wasJustFound) {
                nextPosition();
            }

            if (pos >= size) {
                note = "No additional items with key " + findKey;
                setCodePart(6);
            } else if (persons[pos].getHeight() == findKey) {
                note = "Have found additional item with key " + findKey + " at index " + pos;
                wasJustFound = true;
                setCodePart(4);
            } else {
                if (!wasJustFound) {
                    nextPosition();
                }

                wasJustFound = false;
                note = "Checking for additional matches; index = " + pos;
                setCodePart(4);
            }
        }

        private void run6() {
            resetPosition();
            note = DEFAULT_NOTE;
            setCodePart(1);
        }
    }

    public void find(Integer value) {
        if (operation.getMode() != OperationMode.FIND) {
            operation = new FindOperation();
        }
        operation.run(value);
    }

    private class DeleteOperation extends BaseOperation {
        private int delKey;
        private int lastDeletion;
        private int nDeleted;

        DeleteOperation() {
            super(OperationMode.DELETE);

            addAction(1, it -> run1());
            addAction(2, this::run2);
            addAction(3, it -> run3());
            addAction(4, it -> run4());
            addAction(5, it -> run5());
            addAction(6, it -> run6());
            addAction(10, it -> run10());
            addAction(11, it -> run11());
            addAction(12, it -> run12());
        }

        private void run1() {
            resetPosition();

            lastDeletion = -1;
            nDeleted = 0;
            note = "Enter key of item to delete";
            setCodePart(2);
        }

        private void run2(Integer value) {
            if (value != null && value >= 0 && value <= MAX_HEIGHT) {
                delKey = value;
                note = "Looking for item with key " + delKey;
                setCodePart(3);
            } else {
                note = "ERROR: use key between 0 and " + MAX_HEIGHT;
                setCodePart(1);
            }
        }

        private void run3() {
            if (pos >= size) {
                if (lastDeletion == -1) {
                    note = "No item with key " + delKey + " found";
                    setCodePart(5);
                } else {
                    note = "No additional items with key " + delKey + " found";
                    setCodePart(6);
                }
            } else if (persons[pos].getHeight() == delKey) {
                persons[pos] = null;
                note = "Have found and deleted item with key " + delKey;
                lastDeletion = pos;
                if (dupsOK) {
                    nDeleted = 1;
                    setCodePart(10);
                } else {
                    setCodePart(4);
                }
            } else {
                nextPosition();
                note = "Checking index = " + pos + " for item";
                setCodePart(3);
            }
        }

        private void run4() {
            if (pos < size - 1) {
                nextPosition();
                persons[pos - 1] = persons[pos];
                persons[pos] = null;
                note = "Shifted item from " + pos + " to " + (pos - 1);
            } else {
                --size;
                note = "Shifting completed. Total items = " + size;
                setPosition(size-1);
                if (dupsOK) {
                    setPosition(lastDeletion);
                    setCodePart(3);
                } else {
                    setCodePart(6);
                }
            }
        }

        private void run5() {
            note = "Deletion not completed";
            setCodePart(6);
        }

        private void run6() {
            resetPosition();
            note = DEFAULT_NOTE;
            setCodePart(1);
        }

        private void run10() {
            setPosition(pos + nDeleted);
            note = "Will shift item " + nDeleted + " spaces";
            setCodePart(11);
        }

        private void run11() {
            if (pos < size) {
                persons[pos - nDeleted] = persons[pos];
                persons[pos] = null;
                note = "Shifted item from " + pos + " to " + (pos - nDeleted);
                setPosition(pos - nDeleted);
                setCodePart(12);
            } else {
                size -= nDeleted;
                note = "Shifts complete; no more items to delete";
                setCodePart(6);
            }
        }

        private void run12() {
            if (persons[pos].getHeight() == delKey) {
                ++nDeleted;
                persons[pos] = null;
                note = "Have deleted additional item with key " + delKey;
                lastDeletion = pos;
                setPosition(pos + nDeleted);
            } else {
                note = "Item at " + pos + " is not a duplicate";
                setPosition(pos + nDeleted + 1);
            }

            setCodePart(11);
        }
    }

    public void delete(Integer value) {
        if (operation.getMode() != OperationMode.DELETE) {
            operation = new DeleteOperation();
        }
        operation.run(value);
    }
}
