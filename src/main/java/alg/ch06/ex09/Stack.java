package alg.ch06.ex09;

import alg.ext02.Deque;
import alg.ext02.impl.LinkedList;

public class Stack<E> {
    private final Deque<E> deque = new LinkedList<>();

    public void push(E e) {
        deque.addLast(e);
    }

    public E pop() {
        return deque.removeLast();
    }

    public E peek() {
        return deque.getLast();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }
}
