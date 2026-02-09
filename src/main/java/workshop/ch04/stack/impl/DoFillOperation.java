package workshop.ch04.stack.impl;

import workshop.ch04.operation.AbstractOperation;
import workshop.ch04.operation.StackOperationMode;
import workshop.ch04.pg.Constants;
import workshop.ch04.pg.Person;
import workshop.ch04.stack.PersonGroupStack;
import workshop.ch04.support.Utils;

class DoFillOperation extends AbstractOperation<StackOperationMode, PersonGroupStack> {

    DoFillOperation(PersonGroupStack personGroup) {
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
