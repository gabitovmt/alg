package alg.ch05.home04;

// Программный проект 5.3
// Реализация циклического списка
public class CyclicListDemo {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var list = new CyclicList<String>();
        System.out.println(list); // []

        list.push("A");
        list.push("B");
        list.push("C");
        System.out.println(list); // [C, B, A]

        System.out.println(list.poll()); // C
        System.out.println(list); // [B, A]

        System.out.println(list.contains("A")); // true
        System.out.println(list.contains("D")); // false

        System.out.println(list.element()); // A
        list.step();
        System.out.println(list.element()); // B

        list.poll();
        list.poll();
        System.out.println(list); // []
    }
}
