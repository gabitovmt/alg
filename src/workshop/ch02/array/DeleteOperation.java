package workshop.ch02.array;

import workshop.ch02.BaseOperation;
import workshop.ch02.OperationMode;

class DeleteOperation extends BaseOperation {
    private final NonOrderedPersonGroup group;
    private final int maxHeight;
    private int delKey;
    private int lastDeletion;
    private int nDeleted;

    DeleteOperation(NonOrderedPersonGroup group, int maxHeight) {
        super(OperationMode.DELETE);
        this.group = group;
        this.maxHeight = maxHeight;

        addAction(1, it -> run1());
        addAction(2, this::run2);
        addAction(3, it -> run3());
        addAction(4, it -> run4());
        addAction(5, it -> run5());
        addAction(6, it -> run6());
        addAction(10, it -> run10());
        addAction(11, it -> run11());
        addAction(12, it -> run12());
    }

    private void run1() {
        group.resetPosition();

        lastDeletion = -1;
        nDeleted = 0;
        group.setNote("Enter key of item to delete");
        setCodePart(2);
    }

    private void run2(Integer value) {
        if (value != null && value >= 0 && value <= maxHeight) {
            delKey = value;
            group.setNote("Looking for item with key " + delKey);
            setCodePart(3);
        } else {
            group.setNote("ERROR: use key between 0 and " + maxHeight);
            setCodePart(1);
        }
    }

    private void run3() {
        if (group.getPosition() >= group.getSize()) {
            if (lastDeletion == -1) {
                group.setNote("No item with key " + delKey + " found");
                setCodePart(5);
            } else {
                group.setNote("No additional items with key " + delKey + " found");
                setCodePart(6);
            }
        } else if (group.getCurrentPerson().getHeight() == delKey) {
            group.setCurrentPerson(null);
            group.setNote("Have found and deleted item with key " + delKey);
            lastDeletion = group.getPosition();
            if (group.hasDuplicate()) {
                nDeleted = 1;
                setCodePart(10);
            } else {
                setCodePart(4);
            }
        } else {
            group.nextPosition();
            group.setNote("Checking index = " + group.getPosition() + " for item");
            setCodePart(3);
        }
    }

    private void run4() {
        if (group.getPosition() < group.getSize() - 1) {
            group.nextPosition();
            group.setPerson(group.getPosition() - 1, group.getCurrentPerson());
            group.setCurrentPerson(null);
            group.setNote("Shifted item from " + group.getPosition() + " to " + (group.getPosition() - 1));
        } else {
            group.setSize(group.getSize() - 1);
            group.setNote("Shifting completed. Total items = " + group.getSize());
            group.setPosition(group.getSize() - 1);
            if (group.hasDuplicate()) {
                group.setPosition(lastDeletion);
                setCodePart(3);
            } else {
                setCodePart(6);
            }
        }
    }

    private void run5() {
        group.setNote("Deletion not completed");
        setCodePart(6);
    }

    private void run6() {
        group.resetPosition();
        group.setDefaultNote();
        setCodePart(1);
    }

    private void run10() {
        group.setPosition(group.getPosition() + nDeleted);
        group.setNote("Will shift item " + nDeleted + " spaces");
        setCodePart(11);
    }

    private void run11() {
        if (group.getPosition() < group.getSize()) {
            group.setPerson(group.getPosition() - nDeleted, group.getCurrentPerson());
            group.setCurrentPerson(null);
            group.setNote("Shifted item from " + group.getPosition() + " to " + (group.getPosition() - nDeleted));
            group.setPosition(group.getPosition() - nDeleted);
            setCodePart(12);
        } else {
            group.setSize(group.getSize() - nDeleted);
            group.setNote("Shifts complete; no more items to delete");
            setCodePart(6);
        }
    }

    private void run12() {
        if (group.getCurrentPerson().getHeight() == delKey) {
            ++nDeleted;
            group.setCurrentPerson(null);
            group.setNote("Have deleted additional item with key " + delKey);
            lastDeletion = group.getPosition();
            group.setPosition(group.getPosition() + nDeleted);
        } else {
            group.setNote("Item at " + group.getPosition() + " is not a duplicate");
            group.setPosition(group.getPosition() + nDeleted + 1);
        }

        setCodePart(11);
    }
}
