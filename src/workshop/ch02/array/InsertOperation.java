package workshop.ch02.array;

import workshop.ch02.BaseOperation;
import workshop.ch02.OperationMode;
import workshop.ch02.Person;
import workshop.ch02.PersonGroup;
import workshop.ch02.Utils;

class InsertOperation extends BaseOperation {
    private int insKey;
    private Person newPerson;

    InsertOperation(PersonGroup group) {
        super(OperationMode.INSERT);

        addAction(1, it -> {
            group.resetPosition();
            group.setNote("Enter key of item to insert");
            setCodePart(2);
        });

        addAction(2, it -> {
            if (it != null && it >= 0 && it <= group.getMaxHeight()) {
                if (group.getSize() >= group.getCapacity()) {
                    group.setNote("CAN'T INSERT: array is full");
                    setCodePart(6);
                } else {
                    insKey = it;
                    newPerson = new Person(insKey, Utils.nextColor());
                    group.setNote("Will insert item with key " + insKey);
                    setCodePart(4);
                }
            } else {
                group.setNote("CAN'T INSERT: need key between 0 and " + group.getMaxHeight());
                setCodePart(1);
            }
        });

        addAction(4, it -> {
            group.setPosition(group.getSize());
            group.setCurrentPerson(newPerson);
            group.setSize(group.getSize() + 1);
            group.setNote("Inserted item with key " + insKey + " at index " + group.getPosition());
            setCodePart(5);
        });

        addAction(5, it -> {
            group.setNote("Insertion completed; total items = " + group.getSize());
            if (!group.hasDuplicate()) {
                group.checkDuplicates();
            }
            setCodePart(6);
        });

        addAction(6, it -> {
            group.resetPosition();
            group.setDefaultNote();
            setCodePart(1);
        });
    }
}
