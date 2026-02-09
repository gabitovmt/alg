package alg.ext02.impl;

import alg.ext02.Iterator;
import alg.ext02.PriorityQueue;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class SortedLinkedList<E extends Comparable<E>> extends AbstractCollection<E> implements PriorityQueue<E> {
    private Node<E> head;

    @SuppressWarnings("java:S3516")
    @Override
    public boolean add(E e) {
        var newNode = new Node<>(e);

        if (isEmpty()) {
            head = newNode;
            size++;

            return true;
        }

        Node<E> prev = null;
        Node<E> current = head;
        while (current != null && e.compareTo(current.element) > 0) {
            prev = current;
            current = current.next;
        }

        if (prev == null) {
            head = newNode;
        } else {
            prev.next = newNode;
        }
        newNode.next = current;
        size++;

        return true;
    }

    @Override
    public boolean remove(E e) {
        if (isEmpty()) {
            return false;
        }

        Node<E> prev = null;
        Node<E> current = head;
        while (current != null && !e.equals(current.element)) {
            prev = current;
            current = current.next;
        }

        if (current == null) {
            return false;
        }

        if (prev == null) {
            head = current.next;
        } else {
            prev.next = current.next;
        }
        size--;

        return true;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        var element = head.element;
        head = head.next;
        size--;

        return element;
    }

    @Override
    public E element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return head.element;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorImpl();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray(Class<E> clazz) {
        var array = (E[]) Array.newInstance(clazz, size);
        var current = head;
        for (int i = 0; i < size; i++, current = current.next) {
            array[i] = current.element;
        }

        return array;
    }

    private class IteratorImpl implements Iterator<E> {
        private Node<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            var element = current.element;
            current = current.next;

            return element;
        }
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element) {
            this.element = element;
        }
    }
}
