package workshop.ch04.priorityq.impl;

import workshop.ch04.operation.AbstractOperation;
import workshop.ch04.operation.QueueOperationMode;
import workshop.ch04.pg.Constants;
import workshop.ch04.pg.Person;
import workshop.ch04.priorityq.PersonGroupPriorityQ;
import workshop.ch04.support.Utils;

import java.util.Arrays;
import java.util.Comparator;

class DoFillOperation extends AbstractOperation<QueueOperationMode, PersonGroupPriorityQ> {

    DoFillOperation(PersonGroupPriorityQ personGroup) {
        super(QueueOperationMode.FILL, personGroup);
    }

    @Override
    protected void run1() {
        pg.reset();

        var a = new Person[Constants.DEFAULT_SIZE];
        for (int i = 0; i < a.length; i++) {
            a[i] = new Person(Utils.nextHeight(), Utils.nextColor());
        }
        Arrays.sort(a, Comparator.comparing(Person::height).reversed());

        for (int i = 0; i < a.length; i++) {
            pg.setPerson(i, a[i]);
        }

        pg.setSize(a.length);
        pg.setRear(0);
        pg.setFront(a.length - 1);
    }
}
