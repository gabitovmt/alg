package workshop.ch04.queue;

public interface Queue<T> {
    void newQueue();
    void doFill();
    void insert(T value);
    T remove();
    T peek();
}
