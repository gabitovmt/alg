package workshop.ch02.array;

import workshop.ch02.AbstractPersonGroupOperation;
import workshop.ch02.OperationMode;
import workshop.ch02.PersonGroup;

class FindOperation extends AbstractPersonGroupOperation {
    private int findKey;
    private boolean wasJustFound;

    FindOperation(PersonGroup group) {
        super(OperationMode.FIND, group);
    }

    @Override
    protected void run1() {
        group.resetPosition();
        group.setNote("Enter key of item to find");
        setCodePart(2);
    }

    @Override
    protected void run2(Integer value) {
        if (value != null && value >= 0 && value <= group.getMaxHeight()) {
            findKey = value;
            group.setNote("Looking for item with key " + findKey);
            setCodePart(3);
        } else {
            group.setNote("ERROR: use key between 0 and " + group.getMaxHeight());
            setCodePart(1);
        }
    }

    @Override
    protected void run3() {
        if (group.getPosition() >= group.getSize()) {
            group.setNote("Can't locate item with key " + findKey);
            setCodePart(6);
        } else if (group.getCurrentPerson().getHeight() == findKey) {
            group.setNote("Have found item with key " + findKey);
            wasJustFound = true;
            setCodePart(group.hasDuplicate() ? 4 : 6);
        } else {
            group.nextPosition();
            group.setNote("Checking next cell; index = " + group.getPosition());
            setCodePart(3);
        }
    }

    @Override
    protected void run4() {
        if (wasJustFound) {
            group.nextPosition();
        }

        if (group.getPosition() >= group.getSize()) {
            group.setNote("No additional items with key " + findKey);
            setCodePart(6);
        } else if (group.getCurrentPerson().getHeight() == findKey) {
            group.setNote("Have found additional item with key " + findKey + " at index " + group.getPosition());
            wasJustFound = true;
            setCodePart(4);
        } else {
            if (!wasJustFound) {
                group.nextPosition();
            }

            wasJustFound = false;
            group.setNote("Checking for additional matches; index = " + group.getPosition());
            setCodePart(4);
        }
    }

    @Override
    protected void run6() {
        group.resetPosition();
        group.setDefaultNote();
        setCodePart(1);
    }
}
