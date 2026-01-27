package workshop.ch04.stack;

import workshop.ch04.AbstractOperation;
import workshop.ch04.OperationMode;
import workshop.ch04.PersonGroup;

class NewStackOperation extends AbstractOperation {

    NewStackOperation(PersonGroup group) {
        super(OperationMode.NEW_STACK, group);
    }

    @Override
    protected void run1() {
        group.setNote("Will create new, empty stack");
        setCodePart(2);
    }

    @Override
    protected void run2() {
        group.reset();
        group.setNote("New stack created");
        setCodePart(3);
    }

    @Override
    protected void run3() {
        group.setDefaultNote();
        setCodePart(1);
    }
}
