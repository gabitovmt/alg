package alg.ch04.home04;

import alg.ch04.home01.EmptyException;
import alg.ch04.home01.FullException;

import java.util.Arrays;

public class PriorityQ {
    private final long[] a;
    private int size = 0;

    public PriorityQ(int maxSize) {
        a = new long[maxSize];
    }

    public void insert(long value) {
        if (isFull()) {
            throw new FullException();
        }

        a[size++] = value;
    }

    public long remove() {
        if (isEmpty()) {
            throw new EmptyException();
        }

        int minIdx = 0;
        for (int i = 1; i < size; i++) {
            if (a[minIdx] > a[i]) {
                minIdx = i;
            }
        }

        long value = a[minIdx];
        a[minIdx] = a[size - 1];
        size--;

        return value;
    }

    public long peek() {
        return a[size - 1];
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
            System.out.println("PriorityQ[]");
            return;
        }

        long[] b = new long[size];
        System.arraycopy(a, 0, b, 0, size);
        System.out.println("PriorityQ" + Arrays.toString(b));
    }
}
