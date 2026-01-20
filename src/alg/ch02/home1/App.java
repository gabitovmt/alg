package alg.ch02.home1;

@SuppressWarnings("java:S106")
public class App {
    private static final String MAX_ELEMENT = "Max element: ";

    // Упражнения 2.1 и 2.2
    public static void main(String[] args) {
        ex1();
        ex2();
    }

    private static void ex1() {
        System.out.println("Exercise 1");

        var arr = new HighArray(10);
        // Массив пуст. Поэтому значение -1
        System.out.println(MAX_ELEMENT + arr.getMax());
        fillHighArray(arr);

        // Максимальный элемент - 99
        System.out.println(MAX_ELEMENT + arr.getMax());
    }

    private static void ex2() {
        System.out.println("Exercise 2");

        var arr = new HighArray(10);
        fillHighArray(arr);
        // Удалим максимальный элемент
        arr.removeMax();
        // Максимальный элемент - 88
        System.out.println(MAX_ELEMENT + arr.getMax());
    }

    private static void fillHighArray(HighArray arr) {
        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(0);
        arr.insert(66);
        arr.insert(33);
    }
}
