package alg.ext02;

public interface Collection<E> extends Iterable<E> {
    int size();
    boolean isEmpty();
    boolean add(E e);
    boolean remove(E e);
    E[] toArray(Class<E> clazz);
}