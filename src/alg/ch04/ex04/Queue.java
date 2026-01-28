package alg.ch04.ex04;

// Очередь
public class Queue {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int nItems;

    public Queue(int s) {
        maxSize = s;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    // Вставка элемента в конец очереди
    public void insert(long j) {
        if (rear == maxSize - 1) {
            // Циклический перенос
            rear = -1;
        }
        // Увеличение rear и вставка. Увеличение количества элементов
        queArray[++rear] = j;
        nItems++;
    }

    // Извлечение элемента в начале очереди
    public long remove() {
        // Выборка и увеличение front
        long temp = queArray[front++];
        if (front == maxSize) {
            // Циклический перенос
            front = 0;
        }
        // Уменьшение количества элементов
        nItems--;

        return temp;
    }

    // Чтение элемента в начале очереди
    public long peekFront() {
        return queArray[front];
    }

    // Очередь пуста?
    public boolean isEmpty() {
        return nItems == 0;
    }

    // Очередь заполнена?
    public boolean isFull() {
        return nItems == maxSize;
    }

    // Количество элементов в очереди
    public int size() {
        return nItems;
    }
}
