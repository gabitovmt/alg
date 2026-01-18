package workshop.ch02.array;

import workshop.ch02.BaseOperation;
import workshop.ch02.OperationMode;
import workshop.ch02.PersonGroup;

class FindOperation extends BaseOperation {
    private final PersonGroup group;
    private int findKey;
    private boolean wasJustFound;

    FindOperation(PersonGroup group) {
        super(OperationMode.FIND);
        this.group = group;

        addAction(1, it -> run1());
        addAction(2, this::run2);
        addAction(3, it -> run3());
        addAction(4, it -> run4());
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
            setCodePart(3);
        } else {
            group.setNote("ERROR: use key between 0 and " + group.getMaxHeight());
            setCodePart(1);
        }
    }

    private void run3() {
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

    private void run4() {
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

    private void run6() {
        group.resetPosition();
        group.setDefaultNote();
        setCodePart(1);
    }
}
