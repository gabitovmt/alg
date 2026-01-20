package alg.ch02.home1;

public class HighArray {
    private final long[] a;
    private int size;

    public HighArray(int max) {
        a = new long[max];
        size = 0;
    }

    public long getMax() {
        if (size == 0) {
            return -1;
        }

        long max = a[0];
        for (int i = 1; i < size; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }

        return max;
    }

    public void removeMax() {
        long max = getMax();
        if (max == -1) {
            return;
        }

        delete(max);
    }

    public boolean find(long searchKey) {
        for (int i = 0; i < size; i++) {
            if (a[i] == searchKey) {
                return true;
            }
        }

        return false;
    }

    public void insert(long value) {
        a[size++] = value;
    }

    public boolean delete(long value) {
        int i;
        for (i = 0; i < size; i++) {
            if (a[i] == value) {
                break;
            }
        }

        if (i == size) {
            return false;
        }

        for (int k = i; k < size - 1; k++) {
            a[k] = a[k + 1];
        }
        size--;

        return true;
    }

    @SuppressWarnings("java:S106")
    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
