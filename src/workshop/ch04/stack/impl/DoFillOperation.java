package workshop.ch04.stack.impl;

import workshop.ch04.support.Utils;
import workshop.ch04.operation.AbstractOperation;
import workshop.ch04.operation.StackOperationMode;
import workshop.ch04.pg.Constants;
import workshop.ch04.pg.MutablePersonGroup;
import workshop.ch04.pg.Person;

class DoFillOperation extends AbstractOperation<StackOperationMode> {

    DoFillOperation(MutablePersonGroup personGroup) {
        super(StackOperationMode.FILL, personGroup);
    }

    @Override
    protected void run1() {
        pg.reset();

        for (int i = 0; i < Constants.DEFAULT_SIZE; i++) {
            pg.setPerson(pg.getSize(), new Person(Utils.nextHeight(), Utils.nextColor()));
            pg.setSize(pg.getSize() + 1);
        }
    }
}
