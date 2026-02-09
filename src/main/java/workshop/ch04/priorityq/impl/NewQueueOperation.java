package workshop.ch04.priorityq.impl;

import workshop.ch04.operation.AbstractOperation;
import workshop.ch04.operation.QueueOperationMode;
import workshop.ch04.priorityq.PersonGroupPriorityQ;

class NewQueueOperation extends AbstractOperation<QueueOperationMode, PersonGroupPriorityQ> {

    NewQueueOperation(PersonGroupPriorityQ personGroup) {
        super(QueueOperationMode.NEW_QUEUE, personGroup);
    }

    @Override
    protected void run1() {
        pg.setNote("Will create new queue");
        setCodePart(2);
    }

    @Override
    protected void run2() {
        pg.setNote("New queue created");
        pg.reset();
        setCodePart(3);
    }

    @Override
    protected void run3() {
        pg.setDefaultNote();
        setCodePart(1);
    }
}
