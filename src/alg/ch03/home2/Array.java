package alg.ch03.home2;

public class Array {
    private final long[] a;
    private int size;

    public Array(int max) {
        a = new long[max];
        size = 0;
    }

    public void insert(long value) {
        a[size++] = value;
    }

    @SuppressWarnings("java:S106")
    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public void bubbleSort() {
        int outL = 0;
        int outR = size - 1;

        while (outL < outR) {
            for (int in = outL; in < outR; in++) {
                checkAndSwap(in);
            }
            outR--;
            for (int in = outR - 1; in >= outL; in--) {
                checkAndSwap(in);
            }
            outL++;
        }
    }

    private boolean checkAndSwap(int i) {
        if (a[i] > a[i + 1]) {
            long temp = a[i];
            a[i] = a[i + 1];
            a[i + 1] = temp;
            return true;
        }

        return false;
    }

    public void insertionSort() {
        for (int out = 1; out < size; out++) {
            long temp = a[out];
            int in = out;
            while (in > 0 && a[in - 1] >= temp) {
                a[in] = a[in - 1];
                --in;
            }
            a[in] = temp;
        }
    }

    public long median() {
        return a[size /2];
    }

    public void noDups() {
        int nDeleted = 0;
        for (int i = 1; i < size; i++) {
            if (a[i - 1] == a[i]) {
                nDeleted++;
            } else if (nDeleted > 0) {
                a[i - nDeleted] = a[i];
            }
        }
        size -= nDeleted;
    }

    public void oddEvenSort() {
        boolean wasSwap;
        do {
            wasSwap = false;
            for (int i = 1; i < size - 1; i += 2) {
                wasSwap = checkAndSwap(i) || wasSwap;
            }
            for (int i = 0; i < size - 1; i += 2) {
                wasSwap = checkAndSwap(i) || wasSwap;
            }
        } while (wasSwap);
    }
}
