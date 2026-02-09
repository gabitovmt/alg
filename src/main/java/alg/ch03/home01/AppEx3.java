package alg.ch03.home01;

import alg.ch03.ex01.ArrayBub;
import alg.ch03.ex02.ArraySel;
import alg.ch03.ex03.ArrayIns;

// Упражнение 3
public class AppEx3 {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        int maxSize = 100_000;

        var bubArray = new ArrayBub(maxSize);
        var selArray = new ArraySel(maxSize);
        var insArray = new ArrayIns(maxSize);

        for (int i = 0; i < maxSize; i++) {
            bubArray.insert(i);
            selArray.insert(i);
            insArray.insert(i);
        }

        long start = System.currentTimeMillis();

        bubArray.bubbleSort();
        long step1 = System.currentTimeMillis();

        selArray.selectionSort();
        long step2 = System.currentTimeMillis();

        insArray.insertionSort();
        long step3 = System.currentTimeMillis();

        System.out.println("Elements: ASC");
        System.out.println("Size of elements: " + maxSize);
        System.out.println("Bubble Sort: " + (step1 - start) + " ms");
        System.out.println("Selection Sort: " + (step2 - step1) + " ms");
        System.out.println("Insertion Sort: " + (step3 - step2) + " ms");
    }
}
