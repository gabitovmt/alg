package workshop.ch02.orderedarray;

import workshop.ch02.BaseOperation;
import workshop.ch02.OperationMode;
import workshop.ch02.PersonGroup;

class FindOperation extends BaseOperation {
    private final PersonGroup group;
    private int findKey;
    private int lowerBound;
    private int upperBound;

    FindOperation(PersonGroup group) {
        super(OperationMode.FIND);
        this.group = group;

        addAction(1, it -> run1());
        addAction(2, this::run2);
        addAction(3, it -> run3());
        addAction(6, it -> run6());
    }

    private void run1() {
        group.resetPosition();
        group.setNote("Enter key of item to find");
        setCodePart(2);
    }

    private void run2(Integer value) {
        if (value != null && value >= 0 && value <= group.getMaxHeight()) {
            findKey = value;
            group.setNote("Looking for item with key " + findKey);
            if (!group.isLinearSearch()) {
                lowerBound = 0;
                upperBound = group.getSize() - 1;
                group.setPosition((upperBound - lowerBound) / 2);
            }
            setCodePart(3);
        } else {
            group.setNote("ERROR: use key between 0 and " + group.getMaxHeight());
            setCodePart(1);
        }
    }

    private void run3() {
        if (group.isLinearSearch()) {
            if (group.getPosition() < group.getSize() && group.getCurrentPerson().getHeight() <= findKey) {
                if (group.getCurrentPerson().getHeight() == findKey) {
                    group.setNote("Have found item with key " + findKey);
                    setCodePart(6);
                } else {
                    group.nextPosition();
                    group.setNote("Checking next cell; index = " + group.getPosition());
                    setCodePart(3);
                }
            } else {
                group.setNote("Can't locate item with key " + findKey);
                setCodePart(6);
            }
        } else {
            run3Else();
        }
    }

    private void run3Else() {
        group.setShowRange(true);
        if (group.getCurrentPerson().getHeight() == findKey) {
            group.setNote("Have found item with key " + findKey);
            group.resetBounds();
            setCodePart(6);
        } else if (lowerBound > upperBound) {
            group.setNote("Can't locate item with key " + findKey);
            group.resetBounds();
            setCodePart(6);
        } else {
            group.setLowerBound(lowerBound);
            group.setUpperBound(upperBound);
            group.setPosition(lowerBound + (upperBound - lowerBound) / 2);
            group.setNote("Checking index " + group.getPosition() + ", range = " + lowerBound + " to " + upperBound);
            if (group.getCurrentPerson().getHeight() < findKey) {
                lowerBound = group.getPosition() + 1;
            } else {
                upperBound = group.getPosition() - 1;
            }

            setCodePart(3);
        }
    }

    private void run6() {
        group.resetPosition();
        group.setShowRange(false);
        group.setDefaultNote();
        setCodePart(1);
    }
}
