package alg.ext02.impl;

import alg.ext02.Deque;
import alg.ext02.Iterator;

import java.util.NoSuchElementException;

public class LinkedList<E> extends AbstractCollection<E> implements Deque<E> {
    private Node<E> head;
    private Node<E> tail;

    @Override
    public void addFirst(E e) {
        var newNode = new Node<>(e);

        if (isEmpty()) {
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    @Override
    public void addLast(E e) {
        var newNode = new Node<>(e);

        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
    }

    @Override
    public boolean add(E e) {
        addFirst(e);

        return true;
    }

    @Override
    public E removeFirst() throws NoSuchElementException {
        requireNotEmpty();

        E item = head.item;

        if (size == 1) {
            head = tail = null;
            size = 0;

            return item;
        }

        head = head.next;
        head.prev = null;
        size--;

        return item;
    }

    @Override
    public E removeLast() throws NoSuchElementException {
        requireNotEmpty();

        E item = tail.item;

        if (size == 1) {
            head = tail = null;
            size = 0;

            return item;
        }

        tail = tail.prev;
        tail.next = null;
        size--;

        return item;
    }

    @Override
    public E remove() throws NoSuchElementException {
        return removeFirst();
    }

    @Override
    public boolean remove(E e) {
        if (isEmpty()) {
            return false;
        }

        var current = head;
        while (current != null && !e.equals(current.item)) {
            current = current.next;
        }

        if (current == null) {
            return false;
        }
        if (current == head) {
            head = head.next;
        }
        if (current == tail) {
            tail = tail.prev;
        }
        if (current.prev != null) {
            current.prev.next = current.next;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        }
        size--;

        return true;
    }

    @Override
    public E getFirst() throws NoSuchElementException {
        requireNotEmpty();

        return head.item;
    }

    @Override
    public E getLast() throws NoSuchElementException {
        requireNotEmpty();

        return tail.item;
    }

    @Override
    public E element() throws NoSuchElementException {
        return getFirst();
    }

    @Override
    public Iterator<E> iterator() {
        return new NodeIterator<>(head);
    }
}
