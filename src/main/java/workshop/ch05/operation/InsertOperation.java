package workshop.ch05.operation;

import workshop.ch05.pg.Constants;
import workshop.ch05.pg.MutablePersonGroup;
import workshop.ch05.pg.Person;
import workshop.ch05.pg.Utils;

public class InsertOperation extends BaseOperation {
    private int insKey;
    private boolean insertAtEnd;

    public InsertOperation(MutablePersonGroup pg) {
        super(OperationMode.INSERT, pg);
        nextState(new State1());
    }

    private class State1 implements State {
        @Override
        public void run(Integer value) {
            pg.setNote("Enter key of item to insert");
            pg.reset();
            nextState(new State2());
        }
    }

    private class State2 implements State {
        @Override
        public void run(Integer value) {
            if (value == null || value < 0 || value > Constants.MAX_HEIGHT) {
                pg.setNote("CAN'T INSERT: need key between 0 and " + Constants.MAX_HEIGHT);
                nextState(new State1());
                return;
            }

            if (pg.size() >= Constants.MAX_SIZE) {
                pg.setNote("CAN'T INSERT: no room in display");
                nextState(new State6());
                return;
            }

            insKey = value;
            pg.setInsertingPerson(new Person(value, Utils.nextColor()));

            if (pg.isSorted()) {
                pg.setNote("Will search for insertion point");
                nextState(new State3());
            } else {
                pg.setNote("Will insert item with key " + value);
                nextState(new State4());
            }
        }
    }

    private class State3 implements State {
        @Override
        public void run(Integer value) {
            if (pg.currentIndex() == pg.size() - 1 && insKey > pg.getCurrentPerson().height()) {
                pg.setNote("Found insertion point at end of list");
                insertAtEnd = true;
                nextState(new State5());
                return;
            }

            if (insKey > pg.getCurrentPerson().height()) {
                pg.setNote("Searching for insertion point");
                pg.setCurrentIndex(pg.currentIndex() + 1);
                nextState(new State3());
                return;
            }

            pg.setNote("Have found insertion point");
            nextState(new State4());
        }
    }

    private class State4 implements State {
        @Override
        public void run(Integer value) {
            if (pg.isSorted()) {
                pg.setInsertingIndex(pg.currentIndex());
            } else {
                pg.setInsertingIndex(0);
            }

            pg.setNote("Inserted item; will redraw list");
            nextState(new State5());
        }
    }

    private class State5 implements State {
        @Override
        public void run(Integer value) {
            if (insertAtEnd) {
                pg.setCurrentIndex(pg.currentIndex() + 1);
                pg.setNote("Inserted item with key " + insKey + " at end of list");
            } else {
                pg.setInsertingIndex(null);

                for (int i = pg.size(); i > pg.currentIndex(); i--) {
                    pg.setPerson(i, pg.getPerson(i - 1));
                }

                pg.setNote("Inserted item with key " + insKey);
            }

            pg.setPerson(pg.currentIndex(), pg.insertingPerson());
            pg.setInsertingPerson(null);
            pg.setSize(pg.size() + 1);
            nextState(new State6());
        }
    }

    private class State6 implements State {
        @Override
        public void run(Integer value) {
            pg.setNote("Insertion completed; total items = " + pg.size());
            nextState(new State7());
        }
    }

    private class State7 implements State {
        @Override
        public void run(Integer value) {
            pg.reset();
            pg.setDefaultNote();
            nextState(new State1());
        }
    }
}
