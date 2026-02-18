package workshop.ch06.towers.operation;

import workshop.ch06.towers.gg.Game;

public abstract class BaseOperation implements Operation {
    private final OperationMode mode;
    protected final Game game;
    private State state;

    protected BaseOperation(OperationMode mode, Game game) {
        this.mode = mode;
        this.game = game;
    }

    @Override
    public OperationMode mode() {
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
