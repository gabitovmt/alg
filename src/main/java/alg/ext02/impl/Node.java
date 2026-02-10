package alg.ext02.impl;

class Node<E> {
    E item;
    Node<E> prev;
    Node<E> next;

    Node(E item) {
        this.item = item;
    }
}
