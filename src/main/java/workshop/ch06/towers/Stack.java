package workshop.ch06.towers;

import java.lang.reflect.Array;

public class Stack<E> {
    private final E[] a;
    private int top;

    @SuppressWarnings("unchecked")
    public Stack(Class<E> clazz, int maxSize) {
        a = (E[]) Array.newInstance(clazz, maxSize);
        top = -1;
    }

    public void push(E item) {
        a[++top] = item;
    }

    public E pop() {
        return a[top--];
    }

    public E peek() {
        return a[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == a.length - 1;
    }
}
