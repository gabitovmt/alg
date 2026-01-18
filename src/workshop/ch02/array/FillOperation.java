package workshop.ch02.array;

import workshop.ch02.BaseOperation;
import workshop.ch02.OperationMode;

class FillOperation extends BaseOperation {
    private int fillSize;

    FillOperation(NonOrderedPersonGroup group) {
        super(OperationMode.FILL);

        addAction(1, it -> {
            group.setNote("Enter number of items to fill in");
            setCodePart(2);
        });

        addAction(2, it -> {
            if (it != null && it >= 0 && it <= group.getCapacity()) {
                fillSize = it;
                group.setNote("Will fill in " + fillSize + " items");
                setCodePart(3);
                return;
            }

            group.setNote("ERROR: can't fill more than " + group.getCapacity() + " items");
            setCodePart(1);
        });

        addAction(3, it -> {
            group.setSize(0);
            group.doFill(fillSize);
            group.resetPosition();
            group.setNote("Fill completed; total items = " + group.getSize());
            if (!group.hasDuplicate()) {
                group.checkDuplicates();
            }
            setCodePart(4);
        });

        addAction(4, it -> {
            group.setDefaultNote();
            setCodePart(1);
        });
    }
}
