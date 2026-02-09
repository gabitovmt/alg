package alg.ch04.ex07;

public class StackX {
    private final char[] stackArray;
    private int top;

    public StackX(int maxSize) {
        stackArray = new char[maxSize];
        top = -1;
    }

    public void push(char j) {
        stackArray[++top] = j;
    }

    public char pop() {
        return stackArray[top--];
    }

    public char peek() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    @SuppressWarnings("java:S106")
    public void displayStack(String s) {
        System.out.printf("%sStack (bottom-->top): ", s);
        for (int j = 0; j < size(); j++) {
            System.out.printf("%c ", stackArray[j]);
        }
        System.out.println();
    }
}
