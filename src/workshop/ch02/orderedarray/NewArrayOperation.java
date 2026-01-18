package workshop.ch02.orderedarray;

import workshop.ch02.BaseOperation;
import workshop.ch02.OperationMode;
import workshop.ch02.Person;
import workshop.ch02.PersonGroup;

class NewArrayOperation extends BaseOperation {
    private int newSize;

    NewArrayOperation(PersonGroup group) {
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
            group.setNote("Select Linear or Binary Search");
            group.setCanChangeSearch(true);
            setCodePart(4);
        });

        addAction(4, it -> {
            group.setNote(group.isLinearSearch() ? "User linear search" : "User binary search");
            setCodePart(5);
        });

        addAction(5, it -> {
            group.setPersons(new Person[newSize]);
            group.setSize(0);
            group.setNote("New array created; total items = " + group.getSize());
            group.resetPosition();
            setCodePart(6);
        });

        addAction(6, it -> {
            group.setDefaultNote();
            setCodePart(1);
        });
    }
}
