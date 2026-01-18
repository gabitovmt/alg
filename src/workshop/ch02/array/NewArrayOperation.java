package workshop.ch02.array;

import workshop.ch02.BasePersonGroupOperation;
import workshop.ch02.OperationMode;
import workshop.ch02.Person;
import workshop.ch02.PersonGroup;

class NewArrayOperation extends BasePersonGroupOperation {
    private int newSize;

    NewArrayOperation(PersonGroup group) {
        super(OperationMode.NEW_ARRAY, group);
    }

    @Override
    protected void run1() {
        group.resetPosition();
        group.setNote("Enter size of array to create");
        setCodePart(2);
    }

    @Override
    protected void run2(Integer value) {
        if (value != null && value >= 0 && value <= group.getMaxSize()) {
            newSize = value;
            group.setNote("Will create empty array with " + newSize + " cells");
            setCodePart(3);
        } else {
            group.setNote("ERROR: use size between 0 and " + group.getMaxSize());
            setCodePart(1);
        }
    }

    @Override
    protected void run3() {
        group.setNote("Select Duplicates OK, or No Dups");
        group.setCanChangeDuplicate(true);
        setCodePart(5);
    }

    @Override
    protected void run5() {
        group.setPersons(new Person[newSize]);
        group.setSize(0);
        group.setNote("New array created; total items = " + group.getSize());
        group.setCanChangeDuplicate(false);
        group.resetPosition();
        setCodePart(6);
    }

    @Override
    protected void run6() {
        group.setDefaultNote();
        setCodePart(1);
    }
}
