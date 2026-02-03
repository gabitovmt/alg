package alg.ch04.home5;

import alg.ch04.home1.EmptyException;
import alg.ch04.home1.FullException;

import java.lang.reflect.Array;

public class Queue<T> {
    private final T[] a;
    private int front = 0;
    private int rear = -1;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public Queue(Class<T> clazz, int maxSize) {
        a = (T[]) Array.newInstance(clazz, maxSize);
    }

    public synchronized void insert(T item) {
        if (isFull()) {
            throw new FullException();
        }

        if (rear == a.length - 1) {
            rear = -1;
        }
        a[++rear] = item;
        size++;
    }

    public synchronized T remove() {
        if (isEmpty()) {
            throw new EmptyException();
        }

        T item = a[front++];
        if (front == a.length) {
            front = 0;
        }
        size--;

        return item;
    }

    public synchronized T peek() {
        if (isEmpty()) {
            throw new EmptyException();
        }

        return a[front];
    }

    public synchronized boolean isEmpty() {
        return size == 0;
    }

    public synchronized boolean isFull() {
        return size == a.length;
    }

    public synchronized int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public synchronized T[] toArray() {
        var b = (T[]) new Object[size];
        if (rear >= front) {
            System.arraycopy(a, front, b, 0, size);
        } else {
            System.arraycopy(a, front, b, 0, a.length - front);
            System.arraycopy(a, 0, b, a.length - front, rear + 1);
        }

        return b;
    }
}
