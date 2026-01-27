package workshop.ch04.priorityq.impl;

import workshop.ch04.operation.AbstractOperation;
import workshop.ch04.operation.QueueOperationMode;
import workshop.ch04.priorityq.PersonGroupPriorityQ;

class PeekOperation extends AbstractOperation<QueueOperationMode, PersonGroupPriorityQ> {

    PeekOperation(PersonGroupPriorityQ personGroup) {
        super(QueueOperationMode.PEEK, personGroup);
    }

    @Override
    protected void run1() {
        if (pg.getSize() == 0) {
            pg.setNote("CAN'T PEEK: queue is empty");
            setCodePart(3);
        } else {
            pg.setNote("Will peek at item at front of queue");
            setCodePart(2);
        }

        result = null;
    }

    @Override
    protected void run2() {
        pg.setNote("Value returned in Number");
        result = pg.getPerson(pg.getFront()).height();
        setCodePart(3);
    }

    @Override
    protected void run3() {
        pg.setDefaultNote();
        setCodePart(1);
    }
}
