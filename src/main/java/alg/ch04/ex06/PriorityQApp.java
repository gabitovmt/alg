package alg.ch04.ex06;

// Работа с приоритетной очередью
public class PriorityQApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var pq = new PriorityQ(5);

        pq.insert(30);
        pq.insert(50);
        pq.insert(10);
        pq.insert(40);
        pq.insert(20);

        while (!pq.isEmpty()) {
            long item = pq.remove();
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
