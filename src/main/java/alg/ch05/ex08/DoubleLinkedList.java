package alg.ch05.ex08;

import java.util.NoSuchElementException;

// Двусвязный список
public class DoubleLinkedList {
    private Link first;
    private Link last;

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(long dd) {
        var newLink = new Link(dd);
        if (isEmpty()) {
            last = newLink;
        } else {
            first.previous = newLink;
            newLink.next = first;
        }
        first = newLink;
    }

    public void insertLast(long dd) {
        var newLink = new Link(dd);
        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
            newLink.previous = last;
        }
        last = newLink;
    }

    public long deleteFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        long value = first.dData;
        if (first.next == null) {
            // Только один элемент
            last = null;
        } else {
            first.next.previous = null;
        }
        first = first.next;

        return value;
    }

    public long deleteLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        long value = last.dData;
        if (first.next == null) {
            // Только один элемент
            first = null;
        } else {
            last.previous.next = null;
        }
        last = last.previous;

        return value;
    }

    // Вставка dd в позицию после key
    public boolean insertAfter(long key, long dd) {
        if (isEmpty()) {
            return false;
        }

        // Поиск элемента от начала списка
        var current = first;
        while (current.dData != key) {
            current = current.next;
            if (current == null) {
                return false;
            }
        }

        var newLink = new Link(dd);
        if (current == last) {
            // Элемент последний
            last = newLink;
        } else {
            // Элемент не последний
            newLink.next = current.next;
            current.next.previous = newLink;
        }
        newLink.previous = current;
        current.next = newLink;

        // Ключ найден, вставка выполнена
        return true;
    }

    public boolean deleteKey(long key) {
        if (isEmpty()) {
            return false;
        }

        // Поиск элемента от начала списка
        var current = first;
        while (current.dData != key) {
            current = current.next;
            if (current == null) {
                return false;
            }
        }

        if (current == first) {
            // Элемент первый
            first = current.next;
        } else {
            current.previous.next = current.next;
        }

        if (current == last) {
            // Элемент последний
            last = current.previous;
        } else {
            current.next.previous = current.previous;
        }

        // Ключ найден, удаление выполнено
        return true;
    }

    @SuppressWarnings("java:S106")
    public void displayForward() {
        System.out.println("List (first-->last): ");
        var current = first;
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println();
    }

    @SuppressWarnings("java:S106")
    public void displayBackward() {
        System.out.println("List (last-->first): ");
        var current = last;
        while (current != null) {
            System.out.print(current + " ");
            current = current.previous;
        }
        System.out.println();
    }
}
