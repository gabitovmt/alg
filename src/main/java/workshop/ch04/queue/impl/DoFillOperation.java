package workshop.ch04.queue.impl;

import workshop.ch04.operation.AbstractOperation;
import workshop.ch04.operation.QueueOperationMode;
import workshop.ch04.pg.Constants;
import workshop.ch04.pg.Person;
import workshop.ch04.queue.PersonGroupQueue;
import workshop.ch04.support.Utils;

class DoFillOperation extends AbstractOperation<QueueOperationMode, PersonGroupQueue> {

    DoFillOperation(PersonGroupQueue personGroup) {
        super(QueueOperationMode.FILL, personGroup);
    }

    @Override
    protected void run1() {
        pg.reset();

        for (int i = 0; i < Constants.DEFAULT_SIZE; i++) {
            if (pg.getRear() >= pg.getCapacity()) {
                pg.setRear(-1);
            }
            pg.setRear(pg.getRear() + 1);
            pg.setPerson(pg.getRear(), new Person(Utils.nextHeight(), Utils.nextColor()));
            pg.setSize(pg.getSize() + 1);
        }
    }
}
