package alg.ch05.ex09;

import java.util.NoSuchElementException;

public class LinkedList implements List {
    private Node first;

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator getIterator() {
        return new ListIterator();
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("[");
        var current = first;
        while (current != null) {
            if (current != first) {
                sb.append(", ");
            }
            sb.append(current);
            current = current.next;
        }
        sb.append("]");

        return sb.toString();
    }

    private class ListIterator implements Iterator {
        Node prev;
        Node current;

        ListIterator() {
            reset();
        }

        @Override
        public void reset() {
            prev = null;
            current = first;
        }

        @Override
        public boolean atEnd() {
            return current == null || current.next == null;
        }

        @Override
        public void next() {
            if (atEnd()) {
                throw new NoSuchElementException();
            }
            prev = current;
            current = current.next;
        }

        @Override
        public long getCurrent() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            return current.value;
        }

        @Override
        public void insertBefore(long value) {
            var newNode = new Node(value);
            if (prev == null) {
                newNode.next = first;
                first = newNode;
            } else {
                newNode.next = current;
                prev.next = newNode;
            }
            current = newNode;
        }

        @Override
        public void insertAfter(long value) {
            var newNode = new Node(value);
            if (isEmpty()) {
                first = newNode;
                current = newNode;
            } else {
                newNode.next = current.next;
                current.next = newNode;
                next();
            }
        }

        @Override
        public long remove() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }

            long value = current.value;
            if (prev == null) {
                first = current.next;
                reset();
            } else {
                prev.next = current.next;
                if (atEnd()) {
                    reset();
                } else {
                    current = current.next;
                }
            }

            return value;
        }
    }

    private static class Node {
        long value;
        Node next;

        Node(long value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return Long.toString(value);
        }
    }
}
