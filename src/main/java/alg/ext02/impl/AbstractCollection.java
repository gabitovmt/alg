package alg.ext02.impl;

import alg.ext02.Collection;

abstract class AbstractCollection<E> implements Collection<E> {
    protected int size;

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
}
