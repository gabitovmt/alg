package workshop.ch02.orderedarray;

import workshop.ch02.BasePersonGroupOperation;
import workshop.ch02.OperationMode;
import workshop.ch02.PersonGroup;

class DeleteOperation extends BasePersonGroupOperation {
    private int delKey;
    private int lowerBound;
    private int upperBound;

    DeleteOperation(PersonGroup group) {
        super(OperationMode.DELETE, group);
    }

    @Override
    protected void run1() {
        group.resetPosition();
        group.setNote("Enter key of item to delete");
        setCodePart(2);
    }

    @Override
    protected void run2(Integer value) {
        if (value != null && value >= 0 && value <= group.getMaxHeight()) {
            if (!group.isLinearSearch()) {
                lowerBound = 0;
                upperBound = group.getSize() - 1;
                group.setPosition((upperBound - lowerBound) / 2);
            }

            delKey = value;
            group.setNote("Looking for item with key " + delKey);
            setCodePart(3);
        } else {
            group.setNote("ERROR: use key between 0 and " + group.getMaxHeight());
            setCodePart(1);
        }
    }

    @Override
    protected void run3() {
        if (group.isLinearSearch()) {
            run3LinearSearch();
        } else {
            run3BinarySearch();
        }
    }

    private void run3LinearSearch() {
        if (group.getPosition() < group.getSize() && group.getCurrentPerson().getHeight() <= delKey) {
            if (group.getCurrentPerson().getHeight() == delKey) {
                group.setCurrentPerson(null);
                group.setNote("Have found and deleted item with key " + delKey);
                setCodePart(4);
            } else {
                group.nextPosition();
                group.setNote("Checking index = " + group.getPosition() + " for item");
                setCodePart(3);
            }
        } else {
            group.setNote("No item with key " + delKey + " found");
            setCodePart(5);
        }
    }

    private void run3BinarySearch() {
        group.setShowRange(true);
        if (group.getCurrentPerson().getHeight() == delKey) {
            group.setCurrentPerson(null);
            group.setNote("Have found and deleted item with key " + delKey);
            group.resetBounds();
            setCodePart(4);
        } else if (lowerBound > upperBound) {
            group.setNote("No item with key " + delKey + " found");
            group.resetBounds();
            setCodePart(5);
        } else {
            group.setLowerBound(lowerBound);
            group.setUpperBound(upperBound);
            group.setPosition(lowerBound + (upperBound - lowerBound) / 2);
            group.setNote(
                    "Checking index " + group.getPosition() + ", range = " + lowerBound + " to " + upperBound
            );
            if (group.getCurrentPerson().getHeight() < delKey) {
                lowerBound = group.getPosition() + 1;
            } else {
                upperBound = group.getPosition() - 1;
            }
            setCodePart(3);
        }
    }

    @Override
    protected void run4() {
        if (group.getPosition() < group.getSize() - 1) {
            group.nextPosition();
            group.setPerson(group.getPosition() - 1, group.getCurrentPerson());
            group.setCurrentPerson(null);
            group.setNote("Shifted item from " + group.getPosition() + " to " + (group.getPosition() - 1));
            setCodePart(4);
        } else {
            group.setSize(group.getSize() - 1);
            group.setNote("Shifting completed. Total items = " + group.getSize());
            group.setPosition(group.getSize() - 1);
            setCodePart(6);
        }
    }

    @Override
    protected void run5() {
        group.setNote("Deletion not completed");
        setCodePart(6);
    }

    @Override
    protected void run6() {
        group.resetPosition();
        group.setDefaultNote();
        setCodePart(1);
    }
}
