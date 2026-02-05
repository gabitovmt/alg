package alg.ch05.ext01;

public class TwoEndedListDemo {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var list = new TwoEndedList<Integer>();
        System.out.println(list);

        list.insertFirst(1);
        list.insertFirst(2);
        list.insertLast(3);
        list.insertLast(4);
        System.out.println(list);

        System.out.println(list.removeFirst());
        System.out.println(list.removeLast());
        System.out.println(list.removeLast());
        System.out.println(list.removeLast());
        System.out.println(list);
    }
}
