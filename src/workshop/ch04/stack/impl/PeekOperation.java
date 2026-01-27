package workshop.ch04.stack.impl;

import workshop.ch04.operation.AbstractOperation;
import workshop.ch04.operation.StackOperationMode;
import workshop.ch04.pg.MutablePersonGroup;

class PeekOperation extends AbstractOperation<StackOperationMode> {

    PeekOperation(MutablePersonGroup personGroup) {
        super(StackOperationMode.PEEK, personGroup);
    }

    @Override
    protected void run1() {
        if (pg.getSize() == 0) {
            pg.setNote("CAN'T PEEK: stack is empty");
            setCodePart(3);
        } else {
            pg.setNote("Will peek at item at top of stack");
            setCodePart(2);
        }
        result = null;
    }

    @Override
    protected void run2() {
        pg.setNote("Value returned in Number");
        result = pg.getPerson(pg.getSize() - 1).height();
        setCodePart(3);
    }

    @Override
    protected void run3() {
        pg.setDefaultNote();
        setCodePart(1);
    }
}
