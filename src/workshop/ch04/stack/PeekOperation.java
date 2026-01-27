package workshop.ch04.stack;

import workshop.ch04.AbstractOperation;
import workshop.ch04.OperationMode;
import workshop.ch04.PersonGroup;

class PeekOperation extends AbstractOperation {

    PeekOperation(PersonGroup group) {
        super(OperationMode.PEEK, group);
    }

    @Override
    protected void run1() {
        if (group.getSize() == 0) {
            group.setNote("CAN'T PEEK: stack is empty");
            setCodePart(3);
        } else {
            group.setNote("Will peek at item at top of stack");
            setCodePart(2);
        }
        result = null;
    }

    @Override
    protected void run2() {
        group.setNote("Value returned in Number");
        result = group.getLastPerson().height();
        setCodePart(3);
    }

    @Override
    protected void run3() {
        group.setDefaultNote();
        setCodePart(1);
    }
}
