package alg.ext02.impl;

import java.util.NoSuchElementException;

class DescendingIterator<E> extends NodeIterator<E> {

    DescendingIterator(Node<E> current) {
        super(current);
    }

    @Override
    public E next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        var item = current.item;
        current = current.prev;

        return item;
    }
}
