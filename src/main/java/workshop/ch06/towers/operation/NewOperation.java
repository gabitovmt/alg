package workshop.ch06.towers.operation;

import workshop.ch06.towers.gg.Game;

public class NewOperation extends BaseOperation {
    private static final int MIN_DISKS = 1;
    private static final int MAX_DISKS = 10;

    public NewOperation(Game game) {
        super(OperationMode.NEW, game);
        nextState(new State2());
    }

    private class State1 implements State {
        @Override
        public void run(Integer value) {
            game.setNote("ARE YOU SURE? Press again to reset game");
            nextState(new State2());
        }
    }

    private class State2 implements State {
        @Override
        public void run(Integer value) {
            if (value == null || value < MIN_DISKS || value > MAX_DISKS) {
                game.setNote(String.format("Before using New, enter number of disks (%d to %d)", MIN_DISKS, MAX_DISKS));
                nextState(new State2());
                return;
            }

            game.reset(value);
            nextState(new State1());
        }
    }
}
