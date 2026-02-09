package alg.ch05.ext01;

public interface LinkedList<T> {
    void insertFirst(T value);
    void insertLast(T value);
    T removeFirst();
    T removeLast();
    boolean isEmpty();
}
