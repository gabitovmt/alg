package alg.ch02.ex03;

// Класс массива с высокоуровневым интерфейсом
public class HighArray {
    private final long[] a;
    private int nElems;

    public HighArray(int max) {
        a = new long[max];
        nElems = 0;
    }

    // Поиск заданного значения
    public boolean find(long searchKey) {
        for (int i = 0; i < nElems; i++) {
            if (a[i] == searchKey) {
                return true;
            }
        }

        return false;
    }

    // Вставка элемента в массив
    public void insert(long value) {
        a[nElems++] = value;
    }

    // Удаление значения из массива
    public boolean delete(long value) {
        int i;
        for (i = 0; i < nElems; i++) {
            if (a[i] == value) {
                break;
            }
        }

        if (i == nElems) {
            return false;
        }

        for (int k = i; k < nElems - 1; k++) {
            a[k] = a[k + 1];
        }
        nElems--;

        return true;
    }

    // Вывод содержимого массива
    @SuppressWarnings("java:S106")
    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
