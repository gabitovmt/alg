package workshop.ch02.orderedarray;

import workshop.ch02.AbstractPersonGroupOperation;
import workshop.ch02.OperationMode;
import workshop.ch02.PersonGroup;

class FillOperation extends AbstractPersonGroupOperation {
    private int fillSize;

    FillOperation(PersonGroup group) {
        super(OperationMode.FILL, group);
    }

    @Override
    protected void run1() {
        group.setNote("Enter number of items to fill in");
        setCodePart(2);
    }

    @Override
    protected void run2(Integer value) {
        if (value != null && value >= 0 && value <= group.getCapacity()) {
            fillSize = value;
            group.setNote("Will fill in " + fillSize + " items");
            setCodePart(4);
            return;
        }

        group.setNote("ERROR: can't fill more than " + group.getCapacity() + " items");
        setCodePart(1);
    }

    @Override
    protected void run4() {
        group.setSize(0);
        group.doFill(fillSize);
        group.resetPosition();
        group.setNote("Fill completed; total items = " + group.getSize());
        group.checkDuplicates();
        setCodePart(5);
    }

    @Override
    protected void run5() {
        group.setDefaultNote();
        setCodePart(1);
    }
}
