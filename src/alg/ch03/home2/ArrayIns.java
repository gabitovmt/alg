package alg.ch03.home2;

// Массив с сортировкой методом вставки
public class ArrayIns {
    private final long[] a;
    private int size;

    public ArrayIns(int max) {
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
}
