package alg.ch05.home05;

import alg.ch05.home04.CyclicList;

import java.util.NoSuchElementException;

public class Stack<E> {
    private final CyclicList<E> list = new CyclicList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E item) {
        list.push(item);
    }

    public E pop() throws NoSuchElementException {
        return list.poll();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
