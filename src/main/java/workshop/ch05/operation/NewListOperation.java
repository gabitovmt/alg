package workshop.ch05.operation;

import workshop.ch05.pg.Constants;
import workshop.ch05.pg.MutablePersonGroup;

public class NewListOperation extends BaseOperation {
    private int newSize;

    public NewListOperation(MutablePersonGroup pg) {
        super(OperationMode.NEW_LIST, pg);
        nextState(new State1());
    }

    private class State1 implements State {
        @Override
        public void run(Integer value) {
            pg.setNote("Enter size of linked list to create");
            pg.reset();
            nextState(new State2());
        }
    }

    private class State2 implements State {
        @Override
        public void run(Integer value) {
            if (value != null && value >= 0 && value <= Constants.MAX_SIZE) {
                pg.setNote("Will create list with " + value + " links");
                newSize = value;
                nextState(new State3());
            } else {
                pg.setNote("ERROR: use size between 0 and " + Constants.MAX_SIZE);
                nextState(new State1());
            }
        }
    }

    private class State3 implements State {
        @Override
        public void run(Integer value) {
            pg.setNote("Select unsorted or sorted data");
            pg.setCanChangeSorted(true);
            nextState(new State4());
        }
    }

    private class State4 implements State {
        @Override
        public void run(Integer value) {
            pg.setNote(pg.isSorted() ? "Data will be sorted" : "Data will not be sorted");
            pg.setCanChangeSorted(false);
            pg.setSize(0);
            nextState(new State5());
        }
    }

    private class State5 implements State {
        @Override
        public void run(Integer value) {
            pg.setSize(newSize);
            pg.doFill(newSize);
            pg.setNote("New list created; total links = " + pg.size());
            nextState(new State6());
        }
    }

    private class State6 implements State {
        @Override
        public void run(Integer value) {
            pg.setDefaultNote();
            nextState(new State1());
        }
    }
}
