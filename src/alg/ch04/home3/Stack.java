package alg.ch04.home3;

import alg.ch04.home2.Deque;

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
