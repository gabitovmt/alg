package alg.ch04.home01;

// Упражнение 1
public class QueueApp {

    public static void main(String[] args) {
        var queue = new Queue(5);
        queue.display();

        queue.insert(10);
        queue.insert(20);
        queue.insert(30);
        queue.insert(40);
        queue.display();

        queue.remove();
        queue.remove();
        queue.remove();
        queue.display();

        queue.insert(50);
        queue.insert(60);
        queue.insert(70);
        queue.insert(80);
        queue.display();
    }
}
