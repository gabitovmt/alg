package alg.ch05.home03;

import alg.ext02.Deque;
import alg.ext02.impl.LinkedList;

// Программный проект 5.2
// Реализация дека на базе двусвязного двустороннего списка
public class DequeDemo {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        Deque<Long> deque = new LinkedList<>();
        deque.addFirst(1L);
        deque.addFirst(2L);
        deque.addFirst(3L);
        deque.addLast(4L);
        deque.addLast(5L);
        deque.addLast(6L);
        System.out.println(deque);

        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque);
    }
}
