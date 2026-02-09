package alg.ch04.ex01;

// Стек
public class StackX {
    // Размер массива
    private final int maxSize;
    private final long[] stackArray;
    // Вершина стека
    private int top;

    public StackX(int s) {
        maxSize = s;
        stackArray = new long[maxSize];
        // Пока нет ни одного элемента
        top = -1;
    }

    // Размещение элемента на вершине стека
    public void push(long j) {
        stackArray[++top] = j;
    }

    // Извлечение элемента с вершины стека
    public long pop() {
        return stackArray[top--];
    }

    // Чтение элемента с вершины стека
    public long peek() {
        return stackArray[top];
    }

    // Стек пуст?
    public boolean isEmpty() {
        return top == -1;
    }

    // Стек полон?
    public boolean isFull() {
        return top == maxSize - 1;
    }
}
