package alg.ch05.home06;

import alg.ch05.home04.CyclicList;

@SuppressWarnings("java:S106")
public class Harakiri {
    private final CyclicList<Integer> list = new CyclicList<>();

    public Harakiri(int peopleCount, int count, int startIndex) {
        this(peopleCount, count, startIndex, false);
    }

    public Harakiri(int peopleCount, int count, int startIndex, boolean logged) {
        init(peopleCount, logged);
        startAt(startIndex, logged);
        harakiri(count, logged);
    }

    public int alive() {
        return list.isEmpty() ? -1 : list.element();
    }

    private void init(int peopleCount, boolean logged) {
        for (int i = peopleCount; i > 0; i--) {
            list.push(i);
        }
        if (logged) {
            System.out.println("People: " + list);
        }
    }

    private void startAt(int index, boolean logged) {
        for (int i = index - 1; i > 0; i--) {
            list.step();
        }
        if (logged) {
            System.out.println("Start: " + list);
        }
    }

    private void harakiri(int count, boolean logged) {
        while (list.size() > 1) {
            for (int i = 0; i < count; i++) {
                list.step();
            }
            var removed = list.poll();
            if (logged) {
                System.out.println("Remove: " + removed);
            }
        }
        if (logged) {
            System.out.println("End: " + list);
        }
    }
}
