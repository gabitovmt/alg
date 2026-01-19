package alg.ch02.ex04;

// Упорядоченный массив
public class OrdArray {
    private final long[] a;
    private int nElems;

    public OrdArray(int max) {
        a = new long[max];
        nElems = 0;
    }

    public int size() {
        return nElems;
    }

    public int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn;

        while (true) {
            curIn = (lowerBound + upperBound) / 2;
            if (a[curIn] == searchKey) {
                // Элемент найден
                return curIn;
            }
            if (lowerBound > upperBound) {
                // Элемент не найден
                return nElems;
            }

            if (a[curIn] < searchKey) {
                // В верхней половине
                lowerBound = curIn + 1;
            } else {
                // В нижней половине
                upperBound = curIn - 1;
            }
        }
    }

    // Вставка элемента в массив
    public void insert(long value) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn = (lowerBound + upperBound) / 2;

        while (true) {
            if (curIn >= nElems) {
                // Элемент вставляется в конец массива
                a[nElems++] = value;
                return;
            }

            if (a[curIn] == value) {
                // Дубликаты запрещены
                return;
            }

            if (lowerBound > upperBound) {
                if (a[curIn] < value) {
                    curIn++;
                }
                break;
            }

            curIn = (lowerBound + upperBound) / 2;
            if (a[curIn] < value) {
                lowerBound = curIn + 1;
            } else {
                upperBound = curIn - 1;
            }
        }

        // Перемещение последующих элементов
        for (int k = nElems; k > curIn; k--) {
            a[k] = a[k - 1];
        }

        // Вставка и увеличение размера
        a[curIn] = value;
        nElems++;
    }

    public boolean delete(long value) {
        int j = find(value);

        if (j == nElems) {
            // Найти не удалось
            return false;
        }

        // Элемент найден
        // Перемещение последующих элементов
        for (int k = j; k < nElems; k++) {
            a[k] = a[k + 1];
        }

        // Уменьшение размера
        nElems--;

        return true;
    }

    // Вывод содержимого массива
    @SuppressWarnings("java:S106")
    public void display() {
        for (int j = 0; j < nElems; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println();
    }
}
