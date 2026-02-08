package alg.ch04.home03;

import alg.ch04.home02.Deque;

public class Stack extends Deque {

    public Stack(int maxSize) {
        super(maxSize);
    }

    public void push(long value) {
        insertRight(value);
    }

    public long pop() {
        return removeRight();
    }
}
