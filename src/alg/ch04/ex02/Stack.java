package alg.ch04.ex02;

public class Stack<T> {
    private final Object[] a;
    private int top;

    public Stack(int size) {
        a = new Object[size];
        top = -1;
    }

    public void push(T j) {
        if (top == a.length - 1) {
            throw new StackIsFullException();
        }

        a[++top] = j;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (top == -1) {
            throw new EmptyStackException();
        }

        return (T) a[top--];
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (top == -1) {
            throw new EmptyStackException();
        }

        return (T) a[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == a.length - 1;
    }
}
