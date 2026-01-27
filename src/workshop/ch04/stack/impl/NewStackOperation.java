package workshop.ch04.stack.impl;

import workshop.ch04.operation.AbstractOperation;
import workshop.ch04.operation.StackOperationMode;
import workshop.ch04.stack.PersonGroupStack;

class NewStackOperation extends AbstractOperation<StackOperationMode, PersonGroupStack> {

    NewStackOperation(PersonGroupStack personGroup) {
        super(StackOperationMode.NEW_STACK, personGroup);
    }

    @Override
    protected void run1() {
        pg.setNote("Will create new, empty stack");
        setCodePart(2);
    }

    @Override
    protected void run2() {
        pg.reset();
        pg.setNote("New stack created");
        setCodePart(3);
    }

    @Override
    protected void run3() {
        pg.setDefaultNote();
        setCodePart(1);
    }
}
