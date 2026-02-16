package workshop.ch06.towers;

public class Stack {
    private final Params[] a;
    private int top;

    public Stack(int maxSize) {
        a = new Params[maxSize];
        top = -1;
    }

    public void push(Params p) {
        a[++top] = p;
    }

    public Params pop() {
        return a[top--];
    }

    public Params peek() {
        return a[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == a.length - 1;
    }
}
