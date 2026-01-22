package alg.ch03.home2;

// Массив с пузырьковой сортировкой
public class ArrayBub {
    private final long[] a;
    private int size;

    public ArrayBub(int max) {
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

    private void checkAndSwap(int i) {
        if (a[i] > a[i + 1]) {
            long temp = a[i];
            a[i] = a[i + 1];
            a[i + 1] = temp;
        }
    }
}
