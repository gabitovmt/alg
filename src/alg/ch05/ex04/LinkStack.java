package alg.ch05.ex04;

import alg.ch05.ext01.LinkedList;
import alg.ch05.ext01.TwoEndedList;

public class LinkStack {
    private final LinkedList<Long> list = new TwoEndedList<>();

    public void push(long value) {
        list.insertFirst(value);
    }

    public long pop() {
        return list.removeFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return "Stack (top-->bottom): " + list;
    }
}
