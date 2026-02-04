package workshop.ch05.operation;

import workshop.ch05.pg.Constants;
import workshop.ch05.pg.MutablePersonGroup;

public class DeleteOperation extends BaseOperation {
    private int delKey;

    public DeleteOperation(MutablePersonGroup pg) {
        super(OperationMode.DELETE, pg);
        nextState(new State1());
    }

    private class State1 implements State {
        @Override
        public void run(Integer value) {
            pg.setNote("Enter key of item to delete");
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

            delKey = value;
            pg.setNote("Looking for item with key " + delKey);
            nextState(new State3());
        }
    }

    private class State3 implements State {
        @Override
        public void run(Integer value) {
            if (pg.getCurrentPerson().height() == delKey) {
                pg.setNote("Have found item with key " + delKey);
                nextState(pg.currentIndex() == pg.size() - 1 ? new State5() : new State4());
                return;
            }

            if (pg.currentIndex() != pg.size() - 1 && (!pg.isSorted() || pg.getCurrentPerson().height() <= delKey)) {
                pg.setNote("Searching for item with key " + delKey);
                pg.setCurrentIndex(pg.currentIndex() + 1);
                nextState(new State3());
                return;
            }

            pg.setNote("Can't locate item with key " + delKey);
            nextState(new State6());
        }
    }

    private class State4 implements State {
        @Override
        public void run(Integer value) {
            pg.setDeletingIndex(pg.currentIndex());
            pg.setNote("Deleted item; will redraw list");
            nextState(new State5());
        }
    }

    private class State5 implements State {
        @Override
        public void run(Integer value) {
            pg.setDeletingIndex(null);

            for (int i = pg.currentIndex(); i < pg.size() - 1; i++) {
                pg.setPerson(i, pg.getPerson(i + 1));
            }

            pg.setSize(pg.size() - 1);
            pg.setCurrentIndex(0);
            pg.setNote("Deleted item with key " + delKey);
            nextState(new State6());
        }
    }

    private class State6 implements State {
        @Override
        public void run(Integer value) {
            pg.reset();
            pg.setDefaultNote();
            nextState(new State1());
        }
    }
}
