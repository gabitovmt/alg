package workshop.ch04.priorityq.impl;

import workshop.ch04.operation.AbstractOperation;
import workshop.ch04.operation.QueueOperationMode;
import workshop.ch04.priorityq.PersonGroupPriorityQ;

class RemoveOperation extends AbstractOperation<QueueOperationMode, PersonGroupPriorityQ> {

    RemoveOperation(PersonGroupPriorityQ personGroup) {
        super(QueueOperationMode.REMOVE, personGroup);
    }

    @Override
    protected void run1() {
        if (pg.getSize() == 0) {
            pg.setNote("CAN'T REMOVE: queue is empty");
            setCodePart(3);
        } else {
            pg.setNote("Will remove item from front of queue");
            setCodePart(2);
        }

        result = null;
    }

    @Override
    protected void run2() {
        pg.setNote("Item removed");

        result = pg.getPerson(pg.getSize() - 1).height();
        pg.setPerson(pg.getSize() - 1, null);

        pg.setFront(pg.getFront() - 1);
        pg.setSize(pg.getSize() - 1);

        setCodePart(3);
    }

    @Override
    protected void run3() {
        pg.setDefaultNote();
        setCodePart(1);
    }
}
