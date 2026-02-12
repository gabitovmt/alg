package alg.ch05.home04;

import java.util.NoSuchElementException;

public class CyclicList<E> {
    private int size;
    private Node current;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E element() {
        return current != null ? current.item : null;
    }

    public void push(E item) {
        if (isEmpty()) {
            current = new Node(item);
        } else {
            current.next = new Node(current.item, current.next);
            current.item = item;
        }
        size++;
    }

    public E poll() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E item = current.item;
        if (size == 1) {
            current = null;
        } else {
            current.item = current.next.item;
            current.next = current.next.next;
        }
        size--;

        return item;
    }

    public boolean contains(E item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(current.item)) {
                return true;
            }
            step();
        }

        return false;
    }

    public void step() {
        current = current.next;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("[");

        if (!isEmpty()) {
            sb.append(current.item);
            step();
        }
        for (int i = 1; i < size; i++) {
            sb.append(", ").append(current.item);
            step();
        }

        return sb.append("]").toString();
    }

    private class Node {
        E item;
        Node next;

        Node(E item) {
            this.item = item;
            next = this;
        }

        Node(E item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}
