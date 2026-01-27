package workshop.ch04.stack.impl;

import workshop.ch04.operation.AbstractOperation;
import workshop.ch04.operation.StackOperationMode;
import workshop.ch04.pg.MutablePersonGroup;

class PopOperation extends AbstractOperation<StackOperationMode> {

    PopOperation(MutablePersonGroup personGroup) {
        super(StackOperationMode.POP, personGroup);
    }

    @Override
    protected void run1() {
        if (pg.getSize() == 0) {
            pg.setNote("CAN'T POP: stack is empty");
            setCodePart(4);
        } else {
            pg.setNote("Will pop item from top of stack");
            setCodePart(2);
        }
        result = null;
    }

    @Override
    protected void run2() {
        pg.setNote("Item removed; value returned in Number");
        result = pg.getPerson(pg.getSize() - 1).height();
        pg.setPerson(pg.getSize() - 1, null);
        setCodePart(3);
    }

    @Override
    protected void run3() {
        pg.setNote("Decremented top");
        pg.setSize(pg.getSize() - 1);
        setCodePart(4);
    }

    @Override
    protected void run4() {
        pg.setDefaultNote();
        setCodePart(1);
    }
}
