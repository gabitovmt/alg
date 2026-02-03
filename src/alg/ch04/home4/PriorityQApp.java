package alg.ch04.home4;

// Упражнение 4
public class PriorityQApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var pq = new PriorityQ(5);
        pq.display();

        pq.insert(30);
        pq.insert(50);
        pq.insert(10);
        pq.insert(40);
        pq.insert(20);
        pq.display();

        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
            pq.display();
        }
    }
}
