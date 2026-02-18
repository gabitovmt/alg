package workshop.ch06.towers.operation;

import workshop.ch06.towers.gg.Disk;
import workshop.ch06.towers.gg.MutableGame;
import workshop.ch06.towers.gg.Stack;

public class StepOperation extends BaseOperation {
    private Command c;
    private final Stack<Command> commands;

    public StepOperation(MutableGame game) {
        super(OperationMode.STEP, game);
        nextState(new State1());
        commands = new Stack<>(Command.class, game.disksCount());
    }

    private class State1 implements State {
        @Override
        public void run(Integer value) {
            if (!game.tower(0).isFull()) {
                game.setNote("You must begin with ALL DISKS ON TOWER A");
                nextState(new State1());
                return;
            }

            game.setNote("Will shift all disks from A to C");
            c = new Command(game.disksCount(), 0, 2, 1, new State8());
            commands.push(c);
            nextState(new State2());
        }
    }

    private class State2 implements State {
        @Override
        public void run(Integer value) {
            c = commands.peek();
            game.setNote(String.format("Entering function with %d disks", c.n));
            nextState(new State3());
        }
    }

    private class State3 implements State {
        @Override
        public void run(Integer value) {
            if (c.n != 1) {
                game.setNote("More than one Disk, will continue");
                nextState(new State4());
                return;
            }

            var disk = moveDisk(c.from, c.to);

            game.setNote(String.format(
                    "Moved last Disk %s from %s to %s", disk.label(), towerName(c.from), towerName(c.to)
            ));
            if (game.tower(2).isFull()) {
                game.setNote("Congratulations! You moved all the disks!");
            }

            nextState(new State7());
        }
    }

    private class State4 implements State {
        @Override
        public void run(Integer value) {
            game.setNote(String.format(
                    "Will move top %d disks from %s to %s", c.n - 1, towerName(c.from), towerName(c.inter)
            ));
            commands.push(new Command(c.n - 1, c.from, c.inter, c.to, new State5()));
            nextState(new State2());
        }
    }

    private class State5 implements State {
        @Override
        public void run(Integer value) {
            moveDisk(c.from, c.to);
            game.setNote(String.format(
                    "Moved remaining Disk %d from %s to %s", c.n, towerName(c.from), towerName(c.to)
            ));
            nextState(new State6());
        }
    }

    private class State6 implements State {
        @Override
        public void run(Integer value) {
            game.setNote(String.format(
                    "Will move top %d disks from %s to %s", c.n - 1, towerName(c.inter), towerName(c.to)
            ));
            c = new Command(c.n - 1, c.inter, c.to, c.from, new State7());
            commands.push(c);
            nextState(new State2());
        }
    }

    private class State7 implements State {
        @Override
        public void run(Integer value) {
            int prevN = c.n;
            nextState(c.state);
            commands.pop();
            if (!commands.isEmpty()) {
                c = commands.peek();
            }
            game.setNote(String.format("Returning from function with %d disks", prevN));
        }
    }

    private class State8 implements State {
        @Override
        public void run(Integer value) {
            game.setNote("Press New to reset");
            game.setDone(true);
            nextState(new State1());
        }
    }

    private Disk moveDisk(int from, int to) {
        var disk = game.tower(from).removeDisk();
        game.tower(to).insertDisk(disk);

        return disk;
    }

    private String towerName(int idx) {
        return game.tower(idx).name();
    }

    private record Command(int n, int from, int to, int inter, State state) {
    }
}
