package workshop.ch05.operation;

import workshop.ch05.pg.Constants;
import workshop.ch05.pg.MutablePersonGroup;

public class FindOperation extends BaseOperation {
    private int findKey;

    public FindOperation(MutablePersonGroup pg) {
        super(OperationMode.FIND, pg);
        nextState(new State1());
    }

    private class State1 implements State {
        @Override
        public void run(Integer value) {
            pg.setNote("Enter key of item to find");
            pg.reset();
            nextState(new State2());
        }
    }

    private class State2 implements State {
        @Override
        public void run(Integer value) {
            if (value == null || value < 0 || value > Constants.MAX_HEIGHT) {
                pg.setNote("ERROR: use key between 0 and " + Constants.MAX_HEIGHT);
                nextState(new State1());
                return;
            }

            findKey = value;
            pg.setNote("Looking for item with key " + findKey);
            nextState(new State3());
        }
    }

    private class State3 implements State {
        @Override
        public void run(Integer value) {
            if (pg.getCurrentPerson().height() == findKey) {
                pg.setNote("Have found item with key " + findKey);
                nextState(new State6());
                return;
            }

            if (pg.currentIndex() != pg.size() - 1 && (!pg.isSorted() || pg.getCurrentPerson().height() <= findKey)) {
                pg.setNote("Searching for item with key " + findKey);
                pg.setCurrentIndex(pg.currentIndex() + 1);
                nextState(new State3());
                return;
            }

            pg.setNote("Can't locate item with key " + findKey);
            nextState(new State6());
        }
    }

    private class State6 implements State {
        @Override
        public void run(Integer value) {
            pg.setDefaultNote();
            pg.setCurrentIndex(0);
            nextState(new State1());
        }
    }
}
