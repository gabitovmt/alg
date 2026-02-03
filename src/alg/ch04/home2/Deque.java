package alg.ch04.home2;

import alg.ch04.home1.EmptyException;
import alg.ch04.home1.FullException;

import java.util.Arrays;

public class Deque {
    private final long[] a;
    private int left;
    private int right;
    private int size;

    public Deque(int maxSize) {
        a = new long[maxSize];
        left = 0;
        right = maxSize - 1;
    }

    public void insertLeft(long value) {
        if (isFull()) {
            throw new FullException();
        }

        left = left == 0 ? a.length - 1 : left - 1;
        a[left] = value;
        size++;
    }

    public void insertRight(long value) {
        if (isFull()) {
            throw new FullException();
        }

        right = right == a.length - 1 ? 0 : right + 1;
        a[right] = value;
        size++;
    }

    public long removeLeft() {
        if (isEmpty()) {
            throw new EmptyException();
        }

        long value = a[left];
        left = left == a.length - 1 ? 0 : left + 1;
        size--;

        return value;
    }

    public long removeRight() {
        if (isEmpty()) {
            throw new EmptyException();
        }

        long value = a[right];
        right = right == 0 ? a.length - 1 : right - 1;
        size--;

        return value;
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
        if (isEmpty()) {
            System.out.println("Queue[]");
            return;
        }

        var b = new long[size];
        if (left <= right) {
            System.arraycopy(a, left, b, 0, right - left + 1);
        } else {
            System.arraycopy(a, left, b, 0, a.length - left);
            System.arraycopy(a, 0, b, a.length - left, right + 1);
        }

        System.out.printf("Queue%s%n", Arrays.toString(b));
    }
}
