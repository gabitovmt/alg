package alg.ch03.ex01;

// Массив с пузырьковой сортировкой
public class ArrayBub {
    private long[] a;
    private int nElems;

    public ArrayBub(int max) {
        a = new long[max];
        nElems = 0;
    }

    public void insert(long value) {
        a[nElems++] = value;
    }

    @SuppressWarnings("java:S106")
    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public void bubbleSort() {
        // Внешний цикл
        for (int out = nElems - 1; out > 1; out--) {
            // Внутренний цикл
            for (int in = 0; in < out; in++) {
                // Порядок нарушен?
                if (a[in] > a[in + 1]) {
                    // Поменять местами
                    swap(in, in + 1);
                }
            }
        }
    }

    private void swap(int one, int two) {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
}
