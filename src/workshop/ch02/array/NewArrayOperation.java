package workshop.ch02.array;

import workshop.ch02.BaseOperation;
import workshop.ch02.OperationMode;
import workshop.ch02.Person;

class NewArrayOperation extends BaseOperation {
    private int newSize;

    NewArrayOperation(NonOrderedPersonGroup group) {
        super(OperationMode.NEW_ARRAY);

        addAction(1, it -> {
            group.resetPosition();
            group.setNote("Enter size of array to create");
            setCodePart(2);
        });

        addAction(2, it -> {
            if (it != null && it >= 0 && it <= group.getMaxSize()) {
                newSize = it;
                group.setNote("Will create empty array with " + newSize + " cells");
                setCodePart(3);
            } else {
                group.setNote("ERROR: use size between 0 and " + group.getMaxSize());
                setCodePart(1);
            }
        });

        addAction(3, it -> {
            group.setNote("Select Duplicates OK, or No Dups");
            group.setCanChangeDuplicate(true);
            setCodePart(5);
        });

        addAction(5, it -> {
            group.setPersons(new Person[newSize]);
            group.setSize(0);
            group.setNote("New array created; total items = " + group.getSize());
            group.setCanChangeDuplicate(false);
            setCodePart(6);
        });

        addAction(6, it -> {
            group.setDefaultNote();
            setCodePart(1);
        });
    }
}
