package workshop.ch04.stack.impl;

import workshop.ch04.support.Utils;
import workshop.ch04.operation.AbstractOperation;
import workshop.ch04.operation.StackOperationMode;
import workshop.ch04.pg.Constants;
import workshop.ch04.pg.MutablePersonGroup;
import workshop.ch04.pg.Person;

class PushOperation extends AbstractOperation<StackOperationMode> {
    private Person newPerson;

    PushOperation(MutablePersonGroup personGroup) {
        super(StackOperationMode.PUSH, personGroup);
    }

    @Override
    protected void run1() {
        pg.setNote("Enter key of item to push");
        setCodePart(2);
    }

    @Override
    protected void run2() {
        if (arg0 == null || arg0 < 0 || arg0 > Constants.MAX_HEIGHT) {
            pg.setNote("CAN'T PUSH: need key between 0 and " + Constants.MAX_HEIGHT);
            setCodePart(1);
            return;
        }

        if (pg.getSize() >= pg.getCapacity()) {
            pg.setNote("CAN'T PUSH: stack is full");
            setCodePart(5);
        } else {
            pg.setNote("Will push item with key " + arg0);
            newPerson = new Person(arg0, Utils.nextColor());
            setCodePart(3);
        }
    }

    @Override
    protected void run3() {
        pg.setNote("Incremented top");
        pg.setSize(pg.getSize() + 1);
        setCodePart(4);
    }

    @Override
    protected void run4() {
        pg.setNote("Inserted item with key " + newPerson.height() + " at top");
        pg.setPerson(pg.getSize() - 1, newPerson);
        setCodePart(5);
    }

    @Override
    protected void run5() {
        pg.setDefaultNote();
        setCodePart(1);
    }
}
