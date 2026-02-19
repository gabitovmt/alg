package workshop.ch06.mergesort.operation;

import workshop.ch06.mergesort.pg.PersonGroup;
import workshop.ch06.mergesort.support.Stack;

public class StepOperation extends BaseOperation {
    private final Stack<Command> commands;
    private int lower;
    private int upper;
    private int mid;

    public StepOperation(PersonGroup pg) {
        super(pg);
        nextState(new State1());
        commands = new Stack<>(Command.class, pg.persons().length);
    }

    private record Command(int lower, int upper, int mid, State state) {
    }

    private class State1 implements State {
        @Override
        public void run() {
            commands.push(new Command(0, pg.persons().length - 1, 0, new State8()));
            pg.setNote("Initial call to mergeSort");

            nextState(new State2());
        }
    }

    private class State2 implements State {
        @Override
        public void run() {
            var cmd = commands.pop();
            lower = cmd.lower;
            upper = cmd.upper;
            pg.setNote(String.format("Entering mergeSort: %d-%d", lower, upper));

            nextState(lower == upper ? new State7() : new State4());
        }
    }

    private class State3 implements State {
        @Override
        public void run() {

        }
    }

    private class State4 implements State {
        @Override
        public void run() {
            mid = (lower + upper) / 2;
            pg.setNote(String.format("Will sort lower half: %d-%d", lower, mid));
            commands.push(new Command(lower, mid, mid, new State5()));

            nextState(new State2());
        }
    }

    private class State5 implements State {
        @Override
        public void run() {
            var cmd = commands.peek();
            lower = cmd.lower;
            upper = cmd.upper;
            mid = (lower + upper) / 2;
            pg.setNote(String.format("Will sort upper half: %d-%d", mid + 1, upper));
            commands.push(new Command(mid + 1, upper, mid, new State6()));

            nextState(new State2());
        }
    }

    private class State6 implements State {
        @Override
        public void run() {
            var cmd = commands.peek();
            lower = cmd.lower;
            upper = cmd.upper;
            mid = (lower + upper) / 2;
            pg.setNote("Will merge ranges");

            nextState(new State9());
        }
    }

    private class State7 implements State {
        @Override
        public void run() {
            nextState(commands.pop().state);
            if (commands.isEmpty()) {
                pg.setNote("Exciting mergeSort; sort is complete");
            } else {
                pg.setNote(String.format("Exiting mergeSort: %d-%d", lower, upper));
            }
        }
    }

    private class State8 implements State {
        @Override
        public void run() {

        }
    }

    private class State9 implements State {
        @Override
        public void run() {

        }
    }

    private class State10 implements State {
        @Override
        public void run() {

        }
    }
}
