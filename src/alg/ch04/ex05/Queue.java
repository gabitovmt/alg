package alg.ch04.ex05;

public class Queue {
    private final int maxSize;
    private final long[] queArray;
    private int front;
    private int rear;

    public Queue(int s) {
        maxSize = s + 1;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
    }

    public void insert(long j) {
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queArray[++rear] = j;
    }

    public long remove() {
        long temp = queArray[front++];
        if (front == maxSize) {
            front = 0;
        }

        return temp;
    }

    public long peek() {
        return queArray[front];
    }

    public boolean isEmpty() {
        return (rear + 1 == front) || (front + maxSize - 1 == rear);
    }

    public boolean isFull() {
        return (rear + 2 == front) || (front + maxSize - 2 == rear);
    }

    public int size() {
        // Предполагается, что очередь не пуста
        if (rear >= front) {
            // Непрерывная последовательность
            return rear - front + 1;
        } else {
            // Несвязанная последовательность
            return maxSize - front + rear + 1;
        }
    }
}
