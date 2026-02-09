package alg.ext02;

import java.util.NoSuchElementException;

public interface Queue<E> extends Collection<E> {
    boolean add(E e);
    E remove() throws NoSuchElementException;
    E element() throws NoSuchElementException;
}
