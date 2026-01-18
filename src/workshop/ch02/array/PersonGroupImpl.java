package workshop.ch02.array;

import workshop.ch02.BasePersonGroup;
import workshop.ch02.Person;
import workshop.ch02.Utils;

import java.util.Arrays;

public class PersonGroupImpl extends BasePersonGroup {
    private static final int MAX_HEIGHT = 999;
    private static final int MAX_SIZE = 60;

    private boolean dupsOK = false;
    private boolean isOKChangeDups = false;
    private Operation operation = new NoneOperation();

    public PersonGroupImpl(int size) {
        setPersons(new Person[size]);
    }

    public boolean getDupsStatus() {
        return dupsOK;
    }

    public void setDupsStatus(boolean dups) {
        if (isOKChangeDups && dups != dupsOK) {
            dupsOK = dups;
        }

        if (!isOKChangeDups) {
            setNote("To change duplication status, create array with New");
        }
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
                setNote("Enter size of array to create");
                setCodePart(2);
            });

            addAction(2, it -> {
                if (it != null && it >= 0 && it <= MAX_SIZE) {
                    newSize = it;
                    setNote("Will create empty array with " + newSize + " cells");
                    setCodePart(3);
                } else {
                    setNote("ERROR: use size between 0 and " + MAX_SIZE);
                    setCodePart(1);
                }
            });

            addAction(3, it -> {
                setNote("Select Duplicates OK, or No Dups");
                isOKChangeDups = true;
                setCodePart(5);
            });

            addAction(5, it -> {
                setPersons(new Person[newSize]);
                setSize(0);
                setNote("New array created; total items = " + getSize());
                isOKChangeDups = false;
                setCodePart(6);
            });

            addAction(6, it -> {
                setDefaultNote();
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
                setNote("Enter number of items to fill in");
                setCodePart(2);
            });

            addAction(2, it -> {
                if (it != null && it >= 0 && it <= getCapacity()) {
                    fillSize = it;
                    setNote("Will fill in " + fillSize + " items");
                    setCodePart(3);
                    return;
                }

                setNote("ERROR: can't fill more than " + getCapacity() + " items");
                setCodePart(1);
            });

            addAction(3, it -> {
                setSize(0);
                doFill(fillSize);
                resetPosition();
                setNote("Fill completed; total items = " + getSize());
                if (!dupsOK) {
                    checkDups();
                }
                setCodePart(4);
            });

            addAction(4, it -> {
                setDefaultNote();
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
        Arrays.fill(getPersons(), null);
        resetPosition();

        while (getSize() < size) {
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
        for (int idx = 0; idx < getCapacity(); ++idx) {
            if (getPerson(idx) != null && getPerson(idx).getHeight() == value) {
                return idx;
            }
        }

        return -1;
    }

    private void checkDups() {
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

    private class InsertOperation extends BaseOperation {
        private int insKey;
        private Person newPerson;

        InsertOperation() {
            super(OperationMode.INSERT);

            addAction(1, it -> {
                resetPosition();
                setNote("Enter key of item to insert");
                setCodePart(2);
            });

            addAction(2, it -> {
                if (it != null && it >= 0 && it <= MAX_HEIGHT) {
                    if (getSize() >= getCapacity()) {
                        setNote("CAN'T INSERT: array is full");
                        setCodePart(6);
                    } else {
                        insKey = it;
                        newPerson = new Person(insKey, Utils.nextColor());
                        setNote("Will insert item with key " + insKey);
                        setCodePart(4);
                    }
                } else {
                    setNote("CAN'T INSERT: need key between 0 and " + MAX_HEIGHT);
                    setCodePart(1);
                }
            });

            addAction(4, it -> {
                setPosition(getSize());
                setCurrentPerson(newPerson);
                setSize(getSize() + 1);
                setNote("Inserted item with key " + insKey + " at index " + getPosition());
                setCodePart(5);
            });

            addAction(5, it -> {
                setNote("Insertion completed; total items = " + getSize());
                if (!dupsOK) {
                    checkDups();
                }
                setCodePart(6);
            });

            addAction(6, it -> {
                resetPosition();
                setDefaultNote();
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
            setNote("Enter key of item to find");
            setCodePart(2);
        }

        private void run2(Integer value) {
            if (value != null && value >= 0 && value <= MAX_HEIGHT) {
                findKey = value;
                setNote("Looking for item with key " + findKey);
                setCodePart(3);
            } else {
                setNote("ERROR: use key between 0 and " + MAX_HEIGHT);
                setCodePart(1);
            }
        }

        private void run3() {
            if (getPosition() >= getSize()) {
                setNote("Can't locate item with key " + findKey);
                setCodePart(6);
            } else if (getCurrentPerson().getHeight() == findKey) {
                setNote("Have found item with key " + findKey);
                wasJustFound = true;
                setCodePart(dupsOK ? 4 : 6);
            } else {
                nextPosition();
                setNote("Checking next cell; index = " + getPosition());
                setCodePart(3);
            }
        }

        private void run4() {
            if (wasJustFound) {
                nextPosition();
            }

            if (getPosition() >= getSize()) {
                setNote("No additional items with key " + findKey);
                setCodePart(6);
            } else if (getCurrentPerson().getHeight() == findKey) {
                setNote("Have found additional item with key " + findKey + " at index " + getPosition());
                wasJustFound = true;
                setCodePart(4);
            } else {
                if (!wasJustFound) {
                    nextPosition();
                }

                wasJustFound = false;
                setNote("Checking for additional matches; index = " + getPosition());
                setCodePart(4);
            }
        }

        private void run6() {
            resetPosition();
            setDefaultNote();
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
            setNote("Enter key of item to delete");
            setCodePart(2);
        }

        private void run2(Integer value) {
            if (value != null && value >= 0 && value <= MAX_HEIGHT) {
                delKey = value;
                setNote("Looking for item with key " + delKey);
                setCodePart(3);
            } else {
                setNote("ERROR: use key between 0 and " + MAX_HEIGHT);
                setCodePart(1);
            }
        }

        private void run3() {
            if (getPosition() >= getSize()) {
                if (lastDeletion == -1) {
                    setNote("No item with key " + delKey + " found");
                    setCodePart(5);
                } else {
                    setNote("No additional items with key " + delKey + " found");
                    setCodePart(6);
                }
            } else if (getCurrentPerson().getHeight() == delKey) {
                setCurrentPerson(null);
                setNote("Have found and deleted item with key " + delKey);
                lastDeletion = getPosition();
                if (dupsOK) {
                    nDeleted = 1;
                    setCodePart(10);
                } else {
                    setCodePart(4);
                }
            } else {
                nextPosition();
                setNote("Checking index = " + getPosition() + " for item");
                setCodePart(3);
            }
        }

        private void run4() {
            if (getPosition() < getSize() - 1) {
                nextPosition();
                setPerson(getPosition() - 1, getCurrentPerson());
                setCurrentPerson(null);
                setNote("Shifted item from " + getPosition() + " to " + (getPosition() - 1));
            } else {
                setSize(getSize() - 1);
                setNote("Shifting completed. Total items = " + getSize());
                setPosition(getSize() - 1);
                if (dupsOK) {
                    setPosition(lastDeletion);
                    setCodePart(3);
                } else {
                    setCodePart(6);
                }
            }
        }

        private void run5() {
            setNote("Deletion not completed");
            setCodePart(6);
        }

        private void run6() {
            resetPosition();
            setDefaultNote();
            setCodePart(1);
        }

        private void run10() {
            setPosition(getPosition() + nDeleted);
            setNote("Will shift item " + nDeleted + " spaces");
            setCodePart(11);
        }

        private void run11() {
            if (getPosition() < getSize()) {
                setPerson(getPosition() - nDeleted, getCurrentPerson());
                setCurrentPerson(null);
                setNote("Shifted item from " + getPosition() + " to " + (getPosition() - nDeleted));
                setPosition(getPosition() - nDeleted);
                setCodePart(12);
            } else {
                setSize(getSize() - nDeleted);
                setNote("Shifts complete; no more items to delete");
                setCodePart(6);
            }
        }

        private void run12() {
            if (getCurrentPerson().getHeight() == delKey) {
                ++nDeleted;
                setCurrentPerson(null);
                setNote("Have deleted additional item with key " + delKey);
                lastDeletion = getPosition();
                setPosition(getPosition() + nDeleted);
            } else {
                setNote("Item at " + getPosition() + " is not a duplicate");
                setPosition(getPosition() + nDeleted + 1);
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
