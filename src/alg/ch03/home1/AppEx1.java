package alg.ch03.home1;

import alg.ch03.ex01.ArrayBub;
import alg.ch03.ex02.ArraySel;
import alg.ch03.ex03.ArrayIns;
import alg.ch03.home2.Array;

import java.util.Random;

// Упражнение 1
public class AppEx1 {
    private static final Random RANDOM = new Random();

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        int maxSize = 100_000;

        var bubArray = new ArrayBub(maxSize);
        var selArray = new ArraySel(maxSize);
        var insArray = new ArrayIns(maxSize);
        // Добавил массив с поддержкой сортировки чётно-нечётных перестановок для интереса
        var oddEvenArray = new Array(maxSize);

        for (int i = 0; i < maxSize; i++) {
            long n = RANDOM.nextLong();
            bubArray.insert(n);
            selArray.insert(n);
            insArray.insert(n);
            oddEvenArray.insert(n);
        }

        long start = System.currentTimeMillis();

        bubArray.bubbleSort();
        long step1 = System.currentTimeMillis();

        selArray.selectionSort();
        long step2 = System.currentTimeMillis();

        insArray.insertionSort();
        long step3 = System.currentTimeMillis();

        oddEvenArray.oddEvenSort();
        long step4 = System.currentTimeMillis();

        System.out.println("Elements: RANDOM");
        System.out.println("Size of elements: " + maxSize);
        System.out.println("Bubble Sort: " + (step1 - start) + " ms");
        System.out.println("Selection Sort: " + (step2 - step1) + " ms");
        System.out.println("Insertion Sort: " + (step3 - step2) + " ms");
        System.out.println("Odd Even Sort: " + (step4 - step3) + " ms");
    }
}
