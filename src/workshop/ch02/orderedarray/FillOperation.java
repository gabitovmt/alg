package workshop.ch02.orderedarray;

import workshop.ch02.BaseOperation;
import workshop.ch02.OperationMode;
import workshop.ch02.PersonGroup;

class FillOperation extends BaseOperation {
    private int fillSize;

    FillOperation(PersonGroup group) {
        super(OperationMode.FILL);

        addAction(1, it -> {
            group.setNote("Enter number of items to fill in");
            setCodePart(2);
        });

        addAction(2, it -> {
            if (it != null && it >= 0 && it <= group.getCapacity()) {
                fillSize = it;
                group.setNote("Will fill in " + fillSize + " items");
                setCodePart(4);
                return;
            }

            group.setNote("ERROR: can't fill more than " + group.getCapacity() + " items");
            setCodePart(1);
        });

        addAction(4, it -> {
            group.setSize(0);
            group.doFill(fillSize);
            group.resetPosition();
            group.setNote("Fill completed; total items = " + group.getSize());
            group.checkDuplicates();
            setCodePart(5);
        });

        addAction(5, it -> {
            group.setDefaultNote();
            setCodePart(1);
        });
    }
}
