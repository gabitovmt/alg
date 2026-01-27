package workshop.ch04.stack;

import workshop.ch04.AbstractOperation;
import workshop.ch04.OperationMode;
import workshop.ch04.Person;
import workshop.ch04.PersonGroup;
import workshop.ch04.Utils;

class DoFillOperation extends AbstractOperation {

    DoFillOperation(PersonGroup group) {
        super(OperationMode.FILL, group);
    }

    @Override
    protected void run1() {
        group.reset();

        for (int i = 0; i < PersonGroup.DEFAULT_SIZE; i++) {
            group.setSize(group.getSize() + 1);
            group.setLastPerson(new Person(Utils.nextHeight(), Utils.nextColor()));
        }
    }
}
