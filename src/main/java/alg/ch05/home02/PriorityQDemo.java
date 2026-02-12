package alg.ch05.home02;

import alg.ext02.PriorityQueue;
import alg.ext02.impl.SortedLinkedList;

// Программный проект 5.1
// Реализация приоритетной очереди на базе сортированного связанного списка
public class PriorityQDemo {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        PriorityQueue<Long> pq = new SortedLinkedList<>();
        pq.add(50L);
        pq.add(30L);
        pq.add(100L);
        System.out.println(pq);

        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq);
    }
}
