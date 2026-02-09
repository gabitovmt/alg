package workshop.ch04.stack;

public interface Stack<T> {
    void newStack();
    void doFill();
    void push(T value);
    T pop();
    T peek();
}
