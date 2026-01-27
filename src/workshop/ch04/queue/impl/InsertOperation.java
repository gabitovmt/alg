package workshop.ch04.queue.impl;

import workshop.ch04.operation.AbstractOperation;
import workshop.ch04.operation.QueueOperationMode;
import workshop.ch04.pg.Constants;
import workshop.ch04.pg.Person;
import workshop.ch04.queue.PersonGroupQueue;
import workshop.ch04.support.Utils;

class InsertOperation extends AbstractOperation<QueueOperationMode, PersonGroupQueue> {
    private Person newPerson;

    InsertOperation(PersonGroupQueue personGroup) {
        super(QueueOperationMode.INSERT, personGroup);
    }

    @Override
    protected void run1() {
        pg.setNote("Enter key of item to insert");
        setCodePart(2);
    }

    @Override
    protected void run2() {
        if (arg0 == null || arg0 < 0 || arg0 > Constants.MAX_HEIGHT) {
            pg.setNote("CAN'T INSERT: need key between 0 and " + Constants.MAX_HEIGHT);
            setCodePart(1);
            return;
        }

        if (pg.getSize() >= pg.getCapacity()) {
            pg.setNote("CAN'T INSERT: queue is full");
            setCodePart(4);
        } else {
            pg.setNote("Will insert item with key " + arg0);
            newPerson = new Person(arg0, Utils.nextColor());
            setCodePart(3);
        }
    }

    @Override
    protected void run3() {
        pg.setNote("Inserted item with key " + newPerson.height());
        if (pg.getRear() >= pg.getCapacity()) {
            pg.setRear(-1);
        }
        pg.setRear(pg.getRear() + 1);
        pg.setPerson(pg.getRear(), newPerson);
        pg.setSize(pg.getSize() + 1);
        setCodePart(4);
    }

    @Override
    protected void run4() {
        pg.setDefaultNote();
        setCodePart(1);
    }
}
