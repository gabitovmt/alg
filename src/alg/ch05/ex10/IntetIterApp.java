package alg.ch05.ex10;

import alg.ch05.ex09.LinkedList;

public class IntetIterApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var list = new LinkedList();
        var iter = list.getIterator();

        iter.insertAfter(21);
        iter.insertAfter(40);
        iter.insertAfter(30);
        iter.insertAfter(7);
        iter.insertAfter(45);
        System.out.println(list);

        iter.reset();
        if (iter.getCurrent() % 3 == 0) {
            iter.remove();
        }
        while (!iter.atEnd()) {
            iter.next();
            if (iter.getCurrent() % 3 == 0) {
                iter.remove();
            }
        }
        System.out.println(list);
    }
}
