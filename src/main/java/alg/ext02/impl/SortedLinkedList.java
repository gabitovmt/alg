package alg.ext02.impl;

import alg.ext02.Iterator;
import alg.ext02.PriorityQueue;

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
        while (current != null && e.compareTo(current.item) > 0) {
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
    public boolean remove(E e) throws NoSuchElementException {
        if (isEmpty()) {
            return false;
        }

        Node<E> prev = null;
        Node<E> current = head;
        while (current != null && !e.equals(current.item)) {
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
    public E remove() throws NoSuchElementException {
        requireNotEmpty();

        var element = head.item;
        head = head.next;
        size--;

        return element;
    }

    @Override
    public E element() throws NoSuchElementException {
        requireNotEmpty();

        return head.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new NodeIterator<>(head);
    }
}
