package alg.ext01;

import java.util.NoSuchElementException;

public class SortedList {
    private ListNode first;

    public void insert(long data) {
        var newNode = new ListNode(data);

        ListNode prev = null;
        ListNode current = first;

        while (current != null && data > current.value) {
            prev = current;
            current = current.next;
        }

        if (prev == null) {
            first = newNode;
        } else {
            prev.next = newNode;
        }
        newNode.next = current;
    }

    public long remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        long value = first.value;
        first = first.next;

        return value;
    }

    private boolean isEmpty() {
        return first == null;
    }
}
