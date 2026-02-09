package alg.ch04.home05;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Random;

public class Shop {
    private final Random rand = new Random();
    private final Queue<Person>[] queues;
    private int nextPersonId = 1;

    @SuppressWarnings("unchecked")
    public Shop(int cashboxCount, int queueMaxSize) {
        queues = (Queue<Person>[]) Array.newInstance(Queue.class, cashboxCount);
        for (int i = 0; i < cashboxCount; i++) {
            queues[i] = new Queue<>(Person.class, queueMaxSize);
        }
    }

    public int cashboxCount() {
        return queues.length;
    }

    public Queue<Person> personQueue(int idx) {
        return queues[idx];
    }

    public void newPerson() {
        int minPeopleIdx = 0;
        for (int i = 1; i < queues.length; i++) {
            if (queues[i].size() < queues[minPeopleIdx].size()) {
                minPeopleIdx = i;
            }
        }

        var queue = queues[minPeopleIdx];
        if (!queue.isFull()) {
            queue.insert(new Person(nextPersonId++, nextColor()));
        }
    }

    private int nextColorValue() {
        return 128 + rand.nextInt(128);
    }

    private Color nextColor() {
        return new Color(nextColorValue(), nextColorValue(), nextColorValue());
    }

    public void workCashboxes() {
        for (Queue<Person> queue : queues) {
            workCashbox(queue);
        }
    }

    private void workCashbox(Queue<Person> queue) {
        if (!queue.isEmpty() && rand.nextDouble() < 0.1) {
            queue.remove();
        }
    }
}
