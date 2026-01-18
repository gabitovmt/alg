package workshop.ch02.array;

import workshop.ch02.BasePersonGroup;
import workshop.ch02.NoneOperation;
import workshop.ch02.Operation;
import workshop.ch02.OperationMode;
import workshop.ch02.Person;
import workshop.ch02.Utils;

import java.util.Arrays;

public class PersonGroupImpl extends BasePersonGroup implements NonOrderedPersonGroup {
    private static final int MAX_HEIGHT = 999;
    private static final int MAX_SIZE = 60;

    private boolean hasDuplicate = false;
    private boolean canChangeDuplicate = false;
    private Operation operation = new NoneOperation();

    public PersonGroupImpl(int size) {
        setPersons(new Person[size]);
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
    public void doFill(int size) {
        Arrays.fill(getPersons(), null);
        resetPosition();

        while (getSize() < size) {
            var insOperation = new InsertOperation(this);
            insOperation.run(null);
            int height = Utils.nextHeight();

            if (!hasDuplicate) {
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
    public void newArray(Integer size) {
        if (operation.getMode() != OperationMode.NEW_ARRAY) {
            operation = new NewArrayOperation(this);
        }
        operation.run(size);
    }

    @Override
    public void fill(Integer size) {
        if (operation.getMode() != OperationMode.FILL) {
            operation = new FillOperation(this);
        }
        operation.run(size);
    }

    @Override
    public void insert(Integer key) {
        if (operation.getMode() != OperationMode.INSERT) {
            operation = new InsertOperation(this);
        }
        operation.run(key);
    }

    @Override
    public void find(Integer key) {
        if (operation.getMode() != OperationMode.FIND) {
            operation = new FindOperation(this);
        }
        operation.run(key);
    }

    @Override
    public void delete(Integer key) {
        if (operation.getMode() != OperationMode.DELETE) {
            operation = new DeleteOperation(this, MAX_HEIGHT);
        }
        operation.run(key);
    }
}
