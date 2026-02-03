package alg.ch04.ex08;

public class StackX {
    private final int[] stackArray;
    private int top;

    public StackX(int maxSize) {
        stackArray = new int[maxSize];
        top = -1;
    }

    public void push(int j) {
        stackArray[++top] = j;
    }

    public int pop() {
        return stackArray[top--];
    }

    public int size() {
        return top + 1;
    }

    @SuppressWarnings("java:S106")
    public void displayStack(String s) {
        System.out.printf("%sStack (bottom-->top): ", s);
        for (int j = 0; j < size(); j++) {
            System.out.printf("%d ", stackArray[j]);
        }
        System.out.println();
    }
}
