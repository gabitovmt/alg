package alg.ext02;

import java.util.NoSuchElementException;

public interface Iterator<E> {
    boolean hasNext();
    E next() throws NoSuchElementException;
}
