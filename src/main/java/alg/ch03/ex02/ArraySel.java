package alg.ch03.ex02;

// Массив с сортировкой методом выбора
public class ArraySel {
    private final long[] a;
    private int nElems;

    public ArraySel(int max) {
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

    public void selectionSort() {
        // Внешний цикл
        for (int out = 0; out < nElems - 1; out++) {
            // Минимум
            int min = out;
            // Внутренний цикл
            for (int in = out + 1; in < nElems; in++) {
                if (a[in] < a[min]) {
                    // Значение min больше, значит найден новый минимум
                    min = in;
                }
            }
            // Поменять местами
            swap(out, min);
        }
    }

    private void swap(int one, int two) {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
}
