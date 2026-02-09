package alg.ch05.ext01;

class Node<T> {
    final T value;
    Node<T> next;

    Node(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
