package workshop.ch06.mergesort.operation;

import workshop.ch06.mergesort.pg.Person;
import workshop.ch06.mergesort.pg.PersonGroup;
import workshop.ch06.mergesort.support.Stack;

public class StepOperation extends BaseOperation {
    private final Stack<Command> commands;

    private int lowPtr;
    private int highPtr;
    private int lowerBound;
    private int upperBound;
    private final Person[] workSpace;

    public StepOperation(PersonGroup pg) {
        super(pg);
        nextState(new State1());
        commands = new Stack<>(Command.class, pg.persons().length);
        workSpace = new Person[pg.persons().length];
    }

    private record Command(int lower, int upper, State state) {
    }

    private int lower() {
        return pg.lower();
    }

    private void lower(int lower) {
        pg.setLower(lower);
    }

    private int upper() {
        return pg.upper();
    }

    private void upper(int upper) {
        pg.setUpper(upper);
    }

    private int mid() {
        return pg.mid();
    }

    private void mid(int mid) {
        pg.setMid(mid);
    }

    private int mid(int lower, int upper) {
        return (lower + upper) / 2;
    }

    private class State1 implements State {
        @Override
        public void run() {
            commands.push(new Command(0, pg.persons().length - 1, new State8()));
            pg.setNote("Initial call to mergeSort");

            nextState(new State2());
        }
    }

    private class State2 implements State {
        @Override
        public void run() {
            var cmd = commands.peek();
            lower(cmd.lower);
            upper(cmd.upper);

            pg.setNote(String.format("Entering mergeSort: %d-%d", lower(), upper()));

            nextState(lower() == upper() ? new State7() : new State4());
        }
    }

    private class State4 implements State {
        @Override
        public void run() {
            mid(mid(lower(), upper()));

            pg.setNote(String.format("Will sort lower half: %d-%d", lower(), mid()));
            commands.push(new Command(lower(), mid(), new State5()));

            nextState(new State2());
        }
    }

    private class State5 implements State {
        @Override
        public void run() {
            var cmd = commands.peek();
            lower(cmd.lower);
            upper(cmd.upper);
            mid(mid(lower(), upper()));

            pg.setNote(String.format("Will sort upper half: %d-%d", mid() + 1, upper()));
            commands.push(new Command(mid() + 1, upper(), new State6()));

            nextState(new State2());
        }
    }

    private class State6 implements State {
        @Override
        public void run() {
            var cmd = commands.peek();
            lower(cmd.lower);
            upper(cmd.upper);
            mid(mid(lower(), upper()));

            pg.setNote("Will merge ranges");
            lowPtr = lower();
            highPtr = mid() + 1;
            upperBound = upper();

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
                pg.setNote(String.format("Exiting mergeSort: %d-%d", lower(), upper()));
            }
        }
    }

    private class State8 implements State {
        @Override
        public void run() {
            pg.setDone();
            pg.setNote("Sort is complete; Press New or Order or Size");

            nextState(new State1());
        }
    }

    private class State9 implements State {
        @Override
        public void run() {
            int i = 0;
            int midM = highPtr - 1;
            lowerBound = lowPtr;
            pg.setPtr(lowPtr);
            pg.setNote(String.format("Merged %d-%d and %d-%d into workspace", lowPtr, midM, highPtr, upperBound));

            while (lowPtr <= midM && highPtr <= upperBound) {
                pg.incComparisons();
                pg.incCopies();
                if (pg.persons()[lowPtr].height() < pg.persons()[highPtr].height()) {
                    workSpace[i++] = pg.persons()[lowPtr++];
                } else {
                    workSpace[i++] = pg.persons()[highPtr++];
                }
            }

            while (lowPtr <= midM) {
                pg.incCopies();
                workSpace[i++] = pg.persons()[lowPtr++];
            }

            while (highPtr <= upperBound) {
                pg.incCopies();
                workSpace[i++] = pg.persons()[highPtr++];
            }

            nextState(new State10());
        }
    }

    private class State10 implements State {
        private int i;
        private final int n = upperBound - lowerBound + 1;

        @Override
        public void run() {
            if (i == n) {
                pg.setNote("Merge completed");
                pg.setPtr(-1);
                nextState(new State7());
                return;
            }

            pg.incCopies();
            pg.persons()[lowerBound + i] = workSpace[i];
            pg.setNote(String.format("Copied workspace into %d", lowerBound + i));
            ++i;
            pg.setPtr(lowerBound + i - 1);
        }
    }
}
