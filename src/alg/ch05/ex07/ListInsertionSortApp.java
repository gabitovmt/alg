package alg.ch05.ex07;

import java.util.Arrays;
import java.util.Random;

public class ListInsertionSortApp {
    private static final Random RAND = new Random();

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        // Создание массива и заполнение случайными числами
        int size = 10;
        var linkArray = new Link[size];
        for (int j = 0; j < size; j++) {
            int n = RAND.nextInt(100);
            linkArray[j] = new Link(n);
        }

        // Вывод содержимого массива
        System.out.println("Unsorted array: " + Arrays.toString(linkArray));

        // Копируем массив в список и обратно
        var sortedList = new SortedList(linkArray);
        for (int j = 0; j < size; j++) {
            linkArray[j] = sortedList.remove();
        }

        System.out.println("Sorted array:   " + Arrays.toString(linkArray));
    }
}
