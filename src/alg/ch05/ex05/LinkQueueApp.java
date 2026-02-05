package alg.ch05.ex05;

// Реализация очереди на базе двустороннего списка
public class LinkQueueApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var queue = new LinkQueue();
        queue.insert(20);
        queue.insert(40);
        System.out.println(queue);

        queue.insert(60);
        queue.insert(80);
        System.out.println(queue);

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue);
    }
}
