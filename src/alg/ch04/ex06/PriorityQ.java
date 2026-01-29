package alg.ch04.ex06;

// Приоритетная очередь
public class PriorityQ {
    // Элементы массива сортируются по значению ключа, от максимума (0) до минимума (maxSize - 1)
    private int maxSize;
    private long[] queArray;
    private int nItems;

    public PriorityQ(int s) {
        maxSize = s;
        queArray = new long[maxSize];
        nItems = 0;
    }

    // Вставка элемента
    public void insert(long item) {
        if (nItems == 0) {
            // Очередь пуста. Вставляем в ячейку 0
            queArray[nItems++] = item;
        } else {
            int j;
            // Перебор в обратном направлении
            for (j = nItems - 1; j >= 0; j--) {
                if (item > queArray[j]) {
                    // Новый элемент больше, сдвигаем вверх
                    queArray[j + 1] = queArray[j];
                } else {
                    // Новый элемент меньше. Прекращаем сдвиг
                    break;
                }
            }
            // Вставка элемента
            queArray[j + 1] = item;
            nItems++;
        }
    }

    // Извлечение минимального элемента
    public long remove() {
        return queArray[--nItems];
    }

    // Чтение минимального элемента
    public long peekMin() {
        return queArray[nItems - 1];
    }

    // Очередь пуста?
    public boolean isEmpty() {
        return nItems == 0;
    }

    // Очередь заполнена
    public boolean isFull() {
        return nItems == maxSize;
    }
}
