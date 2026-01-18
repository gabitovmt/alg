package workshop.ch02.orderedarray;

import workshop.ch02.BaseOperation;
import workshop.ch02.OperationMode;
import workshop.ch02.Person;
import workshop.ch02.PersonGroup;
import workshop.ch02.Utils;

class InsertOperation extends BaseOperation {
    private final PersonGroup group;
    private int insKey;
    private Person newPerson;
    private boolean noShiftsYet;
    private int insertPoint;
    private int lowerBound;
    private int upperBound;

    InsertOperation(PersonGroup group) {
        super(OperationMode.INSERT);
        this.group = group;

        addAction(1, it -> run1());
        addAction(2, this::run2);
        addAction(3, it -> run3());
        addAction(4, it -> run4());
        addAction(6, it -> run6());
        addAction(7, it -> run7());
    }

    private void run1() {
        group.resetPosition();
        group.setNote("Enter key of item to insert");
        setCodePart(2);
    }

    private void run2(Integer value) {
        if (value != null && value >= 0 && value <= group.getMaxHeight()) {
            if (group.getSize() >= group.getCapacity()) {
                group.setNote("CAN'T INSERT: array is full");
                setCodePart(7);
            } else {
                insKey = value;
                newPerson = new Person(insKey, Utils.nextColor());
                group.setNote("Will insert item with key " + insKey);
                if (!group.isLinearSearch()) {
                    lowerBound = 0;
                    upperBound = group.getSize() - 1;
                    group.setPosition((upperBound - lowerBound) / 2);
                }
                setCodePart(3);
            }
        } else {
            group.setNote("CAN'T INSERT: need key between 0 and " + group.getMaxHeight());
            setCodePart(1);
        }
    }

    private void run3() {
        if (group.getPosition() >= group.getSize()) {
            group.setCurrentPerson(newPerson);
            group.setSize(group.getSize() + 1);
            group.setNote("Inserted item " + insKey + " at index " + group.getPosition());
            setCodePart(6);
        } else if (group.isLinearSearch()) {
            if (group.getCurrentPerson().getHeight() == insKey) {
                group.setNote("CAN'T INSERT: duplicate at index " + group.getPosition());
                setCodePart(7);
            } else if (group.getCurrentPerson().getHeight() > insKey) {
                group.setNote("Will insert at index " + group.getPosition() + ", following shift");
                noShiftsYet = true;
                insertPoint = group.getPosition();
                setCodePart(4);
            } else {
                group.nextPosition();
                group.setNote("Checking item at index = " + group.getPosition());
                setCodePart(3);
            }
        } else {
            run3Else();
        }
    }

    private void run3Else() {
        group.setShowRange(true);
        if (group.getCurrentPerson().getHeight() == insKey) {
            group.setNote("CAN'T INSERT: duplicate at " + group.getPosition());
            group.resetBounds();
            setCodePart(7);
        } else if (lowerBound > upperBound) {
            if (group.getCurrentPerson().getHeight() < insKey) {
                group.nextPosition();
            }
            group.setNote("Will insert at index " + group.getPosition() + ", following shift");
            noShiftsYet = true;
            insertPoint = group.getPosition();
            group.resetBounds();
            setCodePart(4);
        } else {
            group.setLowerBound(lowerBound);
            group.setUpperBound(upperBound);
            group.setPosition(lowerBound + (upperBound - lowerBound) / 2);
            group.setNote(
                    "Checking index " + group.getPosition() + ", range = " + lowerBound + " to " + upperBound
            );
            if (group.getCurrentPerson().getHeight() < this.insKey) {
                lowerBound = group.getPosition() + 1;
            } else {
                upperBound = group.getPosition() - 1;
            }
            setCodePart(3);
        }
    }

    private void run4() {
        if (noShiftsYet) {
            group.setShowRange(false);
            noShiftsYet = false;
            group.setNote("Will shift cells to make room");
            group.setPosition(group.getSize());
            setCodePart(4);
        } else if (group.getPosition() == insertPoint) {
            group.setCurrentPerson(newPerson);
            group.setSize(group.getSize() + 1);
            group.setNote("Have inserted item " + insKey + " at index " + group.getPosition());
            setCodePart(6);
        } else {
            group.setCurrentPerson(group.getPerson(group.getPosition() - 1));
            group.setPerson(group.getPosition() - 1, null);
            group.setPosition(group.getPosition() - 1);
            group.setNote("Shifted item from index " + group.getPosition());
            setCodePart(4);
        }
    }

    private void run6() {
        group.setNote("Insertion completed; total items = " + group.getSize());
        setCodePart(7);
    }

    private void run7() {
        group.resetPosition();
        group.setDefaultNote();
        setCodePart(1);
    }
}
