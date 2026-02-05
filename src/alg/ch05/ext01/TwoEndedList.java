package alg.ch05.ext01;

public class TwoEndedList<T> implements LinkedList<T> {
    private Node<T> first;
    private Node<T> last;

    @Override
    public void insertFirst(T value) {
        var newNode = new Node<>(value);

        if (isEmpty()) {
            last = newNode;
        }
        newNode.next = first;
        first = newNode;
    }

    @Override
    public void insertLast(T value) {
        var newNode = new Node<>(value);

        if (isEmpty()) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        T value = first.value;

        if (first.next == null) {
            last = null;
        }
        first = first.next;

        return value;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        var prev = first;
        var curr = first;

        if (first.next == null) {
            first = last = null;
            return prev.value;
        }

        while (curr.next != null) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;
        last = prev;

        return curr.value;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("[");

        var curr = first;
        while (curr != null) {
            if (curr != first) {
                sb.append(", ");
            }
            sb.append(curr.value);
            curr = curr.next;
        }

        sb.append("]");
        return sb.toString();
    }
}
