package alg.ext02.impl;

import alg.ext02.Collection;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

abstract class AbstractCollection<E> implements Collection<E> {
    protected int size;

    protected void requireNotEmpty() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("[");
        var iter = iterator();

        if (iter.hasNext()) {
            sb.append(iter.next());
        }
        while (iter.hasNext()) {
            sb.append(", ").append(iter.next());
        }

        return sb.append("]").toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray(Class<E> clazz) {
        var array = (E[]) Array.newInstance(clazz, size);

        var iter = iterator();
        for (int i = 0; i < size; i++) {
            array[i] = iter.next();
        }

        return array;
    }
}
