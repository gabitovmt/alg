package alg.ch04.home02;

// Упражнение 2
@SuppressWarnings("java:S106")
public class DequeApp {

    public static void main(String[] args) {
        var deque = new Deque(3);
        display(deque);

        deque.insertRight(1);
        deque.insertRight(2);
        deque.insertRight(3);
        display(deque);

        System.out.println(deque.removeLeft());
        System.out.println(deque.removeLeft());
        display(deque);

        deque.insertRight(4);
        display(deque);

        deque.insertLeft(5);
        display(deque);

        System.out.println(deque.removeRight());
        System.out.println(deque.removeRight());
        System.out.println(deque.removeRight());
        display(deque);
    }

    private static void display(Deque deque) {
        deque.display();
        System.out.printf("is empty %b, is full %b, size %d%n", deque.isEmpty(), deque.isFull(), deque.size());
    }
}
