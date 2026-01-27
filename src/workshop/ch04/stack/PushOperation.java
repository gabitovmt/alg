package workshop.ch04.stack;

import workshop.ch04.AbstractOperation;
import workshop.ch04.OperationMode;
import workshop.ch04.Person;
import workshop.ch04.PersonGroup;
import workshop.ch04.Utils;

class PushOperation extends AbstractOperation {
    private Person newPerson;

    PushOperation(PersonGroup group) {
        super(OperationMode.PUSH, group);
    }

    @Override
    protected void run1() {
        group.setNote("Enter key of item to push");
        setCodePart(2);
    }

    @Override
    protected void run2() {
        if (arg0 == null || arg0 < 0 || arg0 > PersonGroup.MAX_HEIGHT) {
            group.setNote("CAN'T PUSH: need key between 0 and " + PersonGroup.MAX_HEIGHT);
            setCodePart(1);
            return;
        }

        if (group.getSize() >= group.getCapacity()) {
            group.setNote("CAN'T PUSH: stack is full");
            setCodePart(5);
        } else {
            group.setNote("Will push item with key " + arg0);
            newPerson = new Person(arg0, Utils.nextColor());
            setCodePart(3);
        }
    }

    @Override
    protected void run3() {
        group.setNote("Incremented top");
        group.setSize(group.getSize() + 1);
        setCodePart(4);
    }

    @Override
    protected void run4() {
        group.setNote("Inserted item with key " + newPerson.height() + " at top");
        group.setLastPerson(newPerson);
        setCodePart(5);
    }

    @Override
    protected void run5() {
        group.setDefaultNote();
        setCodePart(1);
    }
}
