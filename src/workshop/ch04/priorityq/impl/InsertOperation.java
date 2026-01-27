package workshop.ch04.priorityq.impl;

import workshop.ch04.operation.AbstractOperation;
import workshop.ch04.operation.QueueOperationMode;
import workshop.ch04.pg.Constants;
import workshop.ch04.pg.Person;
import workshop.ch04.priorityq.PersonGroupPriorityQ;
import workshop.ch04.support.Utils;

class InsertOperation extends AbstractOperation<QueueOperationMode, PersonGroupPriorityQ> {
    private Person newPerson;

    InsertOperation(PersonGroupPriorityQ personGroup) {
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
            setCodePart(5);
            return;
        }

        pg.setNote("Will insert item with key " + arg0);
        newPerson = new Person(arg0, Utils.nextColor());
        pg.setPosition(pg.getSize() - 1);
        setCodePart(3);
    }

    @Override
    protected void run3() {
        pg.setNote("Found insertion point");
        setCodePart(3);

        if (pg.getSize() == 0 || pg.getPosition() < 0) {
            pg.setPosition(0);
            return;
        }

        if (arg0 <= pg.getPerson(pg.getPosition()).height()) {
            pg.setPosition(pg.getPosition() + 1);
            return;
        }

        pg.setNote("Shifting; searching for insertion point");
        pg.setPerson(pg.getPosition() + 1, pg.getPerson(pg.getPosition()));
        pg.setPerson(pg.getPosition(), null);
        pg.setPosition(pg.getPosition() - 1);
        setCodePart(3);
    }

    @Override
    protected void run4() {
        pg.setNote("Inserted item with key " + arg0);
        pg.setFront(pg.getFront() + 1);
        pg.setSize(pg.getSize() + 1);
        pg.setPerson(pg.getPosition(), newPerson);
        setCodePart(5);
    }

    @Override
    protected void run5() {
        pg.setDefaultNote();
        pg.setPosition(-9);
        setCodePart(1);
    }
}
