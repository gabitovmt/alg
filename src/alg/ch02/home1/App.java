package alg.ch02.home1;

import java.util.Random;

@SuppressWarnings("java:S106")
public class App {
    private static final String MAX_ELEMENT = "Max element: ";
    private static final Random RANDOM = new Random();

    // Упражнения 2.1 и 2.2
    public static void main(String[] args) {
        ex1();
        ex2();
        ex3();
        ex4();
        ex5();
        ex6();
    }

    private static void ex1() {
        System.out.println("\nExercise 1");

        var arr = new HighArray(10);
        // Массив пуст. Поэтому значение -1
        System.out.println(MAX_ELEMENT + arr.getMax());
        fillArray(arr);

        // Максимальный элемент - 99
        System.out.println(MAX_ELEMENT + arr.getMax());
    }

    private static void ex2() {
        System.out.println("\nExercise 2");

        var arr = new HighArray(10);
        fillArray(arr);
        // Удалим максимальный элемент
        arr.removeMax();
        // Максимальный элемент - 88
        System.out.println(MAX_ELEMENT + arr.getMax());
    }

    private static void ex3() {
        System.out.println("\nExercise 3");

        // Простая сортировка
        var arr1 = new HighArray(10);
        fillArray(arr1);
        arr1.display();

        var arr2 = new HighArray(10);
        while (!arr1.isEmpty()) {
            arr2.insert(arr1.getMax());
            arr1.removeMax();
        }
        arr2.display();
    }

    private static void ex4() {
        System.out.println("\nExercise 4");

        var arr = new OrderedArray(10);
        fillArray(arr);
        arr.display();
    }

    private static void ex5() {
        System.out.println("\nExercise 5");

        var arr1 = new OrderedArray(20);
        for (int i = 0; i < 10; i++) {
            arr1.insert(RANDOM.nextInt(100));
        }
        arr1.display();

        var arr2 = new OrderedArray(10);
        for (int i = 0; i < 10; i++) {
            arr2.insert(RANDOM.nextInt(100));
        }
        arr2.display();

        for (int i = 0; i < arr2.size(); i++) {
            arr1.insert(arr2.get(i));
        }
        arr1.display();
    }

    private static void ex6() {
        System.out.println("\nExercise 6");

        var arr = new HighArray(10);
        arr.insert(1);
        arr.insert(7);
        arr.insert(3);
        arr.insert(7);
        arr.insert(4);
        arr.insert(3);
        arr.insert(7);
        arr.insert(2);
        arr.display();

        arr.noDups();
        arr.display();
        System.out.println("Size of array: " + arr.size());
    }

    private static void fillArray(Array arr) {
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
