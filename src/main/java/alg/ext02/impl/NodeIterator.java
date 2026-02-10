package alg.ext02.impl;

import alg.ext02.Iterator;

import java.util.NoSuchElementException;

class NodeIterator<E> implements Iterator<E> {
    protected Node<E> current;

    NodeIterator(Node<E> current) {
        this.current = current;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public E next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        var item = current.item;
        current = current.next;

        return item;
    }
}
