package workshop.ch04.queue.impl;

import workshop.ch04.operation.AbstractOperation;
import workshop.ch04.operation.QueueOperationMode;
import workshop.ch04.queue.PersonGroupQueue;

class RemoveOperation extends AbstractOperation<QueueOperationMode, PersonGroupQueue> {

    RemoveOperation(PersonGroupQueue personGroup) {
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
        pg.setNote("Item removed; value returned in Number");
        result = pg.getPerson(pg.getFront()).height();
        pg.setPerson(pg.getFront(), null);
        pg.setSize(pg.getSize() - 1);
        pg.setFront(pg.getFront() + 1);
        if (pg.getFront() >= pg.getCapacity()) {
            pg.setFront(0);
        }
        setCodePart(3);
    }

    @Override
    protected void run3() {
        pg.setDefaultNote();
        setCodePart(1);
    }
}
