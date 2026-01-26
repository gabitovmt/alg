package workshop.ch04;

public interface Stack<T> {
    void newStack();
    void doFill();
    void push(T value);
    T pop();
    T peek();
}
