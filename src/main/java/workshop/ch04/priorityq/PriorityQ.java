package workshop.ch04.priorityq;

public interface PriorityQ<T> {
    void newQueue();
    void doFill();
    void insert(T value);
    T remove();
    T peek();
}
