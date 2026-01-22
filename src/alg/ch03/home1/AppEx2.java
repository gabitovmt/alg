package alg.ch03.home1;

import alg.ch03.ex01.ArrayBub;
import alg.ch03.ex02.ArraySel;
import alg.ch03.ex03.ArrayIns;

// Упражнение 2
public class AppEx2 {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        int maxSize = 100_000;

        var bubArray = new ArrayBub(maxSize);
        var selArray = new ArraySel(maxSize);
        var insArray = new ArrayIns(maxSize);

        for (int i = 0; i < maxSize; i++) {
            long n = (long) maxSize - i - 1;
            bubArray.insert(n);
            selArray.insert(n);
            insArray.insert(n);
        }

        long start = System.currentTimeMillis();

        bubArray.bubbleSort();
        long step1 = System.currentTimeMillis();

        selArray.selectionSort();
        long step2 = System.currentTimeMillis();

        insArray.insertionSort();
        long step3 = System.currentTimeMillis();

        System.out.println("Elements: DESC");
        System.out.println("Size of elements: " + maxSize);
        System.out.println("Bubble Sort: " + (step1 - start) + " ms");
        System.out.println("Selection Sort: " + (step2 - step1) + " ms");
        System.out.println("Insertion Sort: " + (step3 - step2) + " ms");
    }
}
