package workshop.ch02.array;

import workshop.ch02.BasePersonGroupOperation;
import workshop.ch02.OperationMode;
import workshop.ch02.Person;
import workshop.ch02.PersonGroup;
import workshop.ch02.Utils;

class InsertOperation extends BasePersonGroupOperation {
    private int insKey;
    private Person newPerson;

    InsertOperation(PersonGroup group) {
        super(OperationMode.INSERT, group);
    }

    @Override
    protected void run1() {
        group.resetPosition();
        group.setNote("Enter key of item to insert");
        setCodePart(2);
    }

    @Override
    protected void run2(Integer value) {
        if (value != null && value >= 0 && value <= group.getMaxHeight()) {
            if (group.getSize() >= group.getCapacity()) {
                group.setNote("CAN'T INSERT: array is full");
                setCodePart(6);
            } else {
                insKey = value;
                newPerson = new Person(insKey, Utils.nextColor());
                group.setNote("Will insert item with key " + insKey);
                setCodePart(4);
            }
        } else {
            group.setNote("CAN'T INSERT: need key between 0 and " + group.getMaxHeight());
            setCodePart(1);
        }
    }

    @Override
    protected void run4() {
        group.setPosition(group.getSize());
        group.setCurrentPerson(newPerson);
        group.setSize(group.getSize() + 1);
        group.setNote("Inserted item with key " + insKey + " at index " + group.getPosition());
        setCodePart(5);
    }

    @Override
    protected void run5() {
        group.setNote("Insertion completed; total items = " + group.getSize());
        if (!group.hasDuplicate()) {
            group.checkDuplicates();
        }
        setCodePart(6);
    }

    @Override
    protected void run6() {
        group.resetPosition();
        group.setDefaultNote();
        setCodePart(1);
    }
}
