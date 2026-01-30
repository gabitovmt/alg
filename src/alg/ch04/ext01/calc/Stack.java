package alg.ch04.ext01.calc;

import java.util.Arrays;

class Stack {
    private final ExprElement[] a;
    private int top = -1;

    Stack(int maxSize) {
        a = new ExprElement[maxSize];
    }

    void push(ExprElement value) {
        if (isFull()) {
            throw new FullStackException();
        }

        a[++top] = value;
    }

    ExprElement pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return a[top--];
    }

    ExprElement peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return a[top];
    }

    boolean isEmpty() {
        return top == -1;
    }

    boolean isFull() {
        return top == a.length - 1;
    }

    ExprElement[] toArray() {
        var newArray = new ExprElement[top + 1];
        System.arraycopy(a, 0, newArray, 0, newArray.length);

        return newArray;
    }

    @Override
    public String toString() {
        return Arrays.toString(a);
    }
}
