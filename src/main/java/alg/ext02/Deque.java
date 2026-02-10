package alg.ext02;

import java.util.NoSuchElementException;

public interface Deque<E> extends Queue<E> {
    void addFirst(E e);
    void addLast(E e);
    E removeFirst() throws NoSuchElementException;
    E removeLast() throws NoSuchElementException;
    E getFirst() throws NoSuchElementException;
    E getLast() throws NoSuchElementException;
}
