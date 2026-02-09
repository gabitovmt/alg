package alg.ch05.ex05;

import alg.ch05.ext01.LinkedList;
import alg.ch05.ext01.TwoEndedList;

public class LinkQueue {
    private final LinkedList<Long> list = new TwoEndedList<>();

    public void insert(long value) {
        list.insertLast(value);
    }

    public long remove() {
        return list.removeFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return "Queue (front-->rear): " + list;
    }
}
