package workshop.ch05.operation;

import workshop.ch05.pg.MutablePersonGroup;

public abstract class BaseOperation implements Operation {
    private final OperationMode mode;
    protected final MutablePersonGroup pg;
    private State state;

    protected BaseOperation(OperationMode mode, MutablePersonGroup pg) {
        this.mode = mode;
        this.pg = pg;
    }

    @Override
    public OperationMode getMode() {
        return mode;
    }

    @Override
    public void run(Integer value) {
        state.run(value);
    }

    protected interface State {
        void run(Integer value);
    }

    protected void nextState(State state) {
        this.state = state;
    }
}
