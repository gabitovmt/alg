package workshop.ch04.queue.impl;

import workshop.ch04.operation.AbstractOperation;
import workshop.ch04.operation.QueueOperationMode;
import workshop.ch04.queue.PersonGroupQueue;

class NewQueueOperation extends AbstractOperation<QueueOperationMode, PersonGroupQueue> {

    NewQueueOperation(PersonGroupQueue personGroup) {
        super(QueueOperationMode.NEW_QUEUE, personGroup);
    }

    @Override
    protected void run1() {
        pg.setNote("Will create new queue");
        setCodePart(2);
    }

    @Override
    protected void run2() {
        pg.reset();
        pg.setNote("New queue created");
        setCodePart(3);
    }

    @Override
    protected void run3() {
        pg.setDefaultNote();
        setCodePart(1);
    }
}
