package workshop.ch06.mergesort.operation;

import workshop.ch06.mergesort.pg.PersonGroup;

public abstract class BaseOperation implements Operation {
    protected final PersonGroup pg;
    private State state;

    protected BaseOperation(PersonGroup pg) {
        this.pg = pg;
    }

    @Override
    public void run() {
        state.run();
    }

    protected interface State {
        void run();
    }

    protected void nextState(State state) {
        this.state = state;
    }
}
