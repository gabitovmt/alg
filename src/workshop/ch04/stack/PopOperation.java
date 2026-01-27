package workshop.ch04.stack;

import workshop.ch04.AbstractOperation;
import workshop.ch04.OperationMode;
import workshop.ch04.PersonGroup;

class PopOperation extends AbstractOperation {

    PopOperation(PersonGroup group) {
        super(OperationMode.POP, group);
    }

    @Override
    protected void run1() {
        if (group.getSize() == 0) {
            group.setNote("CAN'T POP: stack is empty");
            setCodePart(4);
        } else {
            group.setNote("Will pop item from top of stack");
            setCodePart(2);
        }
        result = null;
    }

    @Override
    protected void run2() {
        group.setNote("Item removed; value returned in Number");
        result = group.getLastPerson().height();
        group.setLastPerson(null);
        setCodePart(3);
    }

    @Override
    protected void run3() {
        group.setNote("Decremented top");
        group.setSize(group.getSize() - 1);
        setCodePart(4);
    }

    @Override
    protected void run4() {
        group.setDefaultNote();
        setCodePart(1);
    }
}
