package workshop.ch06.towers.gg;

import java.lang.reflect.Array;

public class Stack<E> {
    private final Class<E> clazz;
    private final E[] a;
    private int top;

    @SuppressWarnings("unchecked")
    public Stack(Class<E> clazz, int maxSize) {
        this.clazz = clazz;
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

    @SuppressWarnings("unchecked")
    public E[] toArray() {
        var b = (E[]) Array.newInstance(clazz, top + 1);
        System.arraycopy(a, 0, b, 0, top + 1);

        return b;
    }
}
