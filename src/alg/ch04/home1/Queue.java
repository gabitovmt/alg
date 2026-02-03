package alg.ch04.home1;

import java.util.Arrays;

public class Queue {
    private final long[] a;
    private int front = 0;
    private int rear = -1;
    private int size = 0;

    public Queue(int maxSize) {
        a = new long[maxSize];
    }

    public void insert(long value) {
        if (isFull()) {
            throw new FullException();
        }

        if (rear == a.length - 1) {
            rear = -1;
        }
        a[++rear] = value;
        size++;
    }

    public long remove() {
        if (isEmpty()) {
            throw new EmptyException();
        }

        long el = a[front++];
        if (front == a.length) {
            front = 0;
        }
        size--;

        return el;
    }

    public long peek() {
        if (isEmpty()) {
            throw new EmptyException();
        }

        return a[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == a.length;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("java:S106")
    public void display() {
        var b = new long[size];
        if (!isEmpty()) {
            if (rear >= front) {
                System.arraycopy(a, front, b, 0, rear - front + 1);
            } else {
                System.arraycopy(a, front, b, 0, a.length - front);
                System.arraycopy(a, 0, b, a.length - front, rear + 1);
            }
        }

        System.out.printf("Queue%s%n", Arrays.toString(b));
    }
}
