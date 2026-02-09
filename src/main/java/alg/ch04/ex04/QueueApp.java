package alg.ch04.ex04;

// Работа с очередью
public class QueueApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var queue = new Queue(5);

        queue.insert(10);
        queue.insert(20);
        queue.insert(30);
        queue.insert(40);

        // Извлечение 3 элементов (10, 20, 30)
        queue.remove();
        queue.remove();
        queue.remove();

        // Вставка ещё 4 элементов (с циклическим переносом)
        queue.insert(50);
        queue.insert(60);
        queue.insert(70);
        queue.insert(80);

        while (!queue.isEmpty()) {
            long n = queue.remove();
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
