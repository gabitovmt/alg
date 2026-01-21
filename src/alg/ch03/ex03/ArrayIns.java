package alg.ch03.ex03;

// Массив с сортировкой методом вставки
public class ArrayIns {
    private final long[] a;
    private int nElems;

    public ArrayIns(int max) {
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

    public void insertionSort() {
        // out - разделительный маркер
        for (int out = 1; out < nElems; out++) {
            // Скопировать помеченный элемент
            long temp = a[out];
            // Начать перемещения с out пока не найден меньший элемент
            int in = out;
            while (in > 0 && a[in - 1] >= temp) {
                // Сдвинуть элемент вправо
                a[in] = a[in - 1];
                // Перейти на одну позицию влево
                --in;
            }
            // Вставить помеченный элемент
            a[in] = temp;
        }
    }
}
