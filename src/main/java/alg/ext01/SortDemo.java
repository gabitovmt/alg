package alg.ext01;

import java.util.Random;
import java.util.function.Consumer;

@SuppressWarnings("java:S106")
public class SortDemo {
    private static final Random RANDOM = new Random();

    /**
     * Size of elements: 100000
     * Bubble Sort            : 9390 ms
     * Selection Sort         : 1759 ms
     * Insertion Sort         : 343 ms
     * Odd Even Sort          : 5047 ms
     * Parallel Odd Even Sort : 11411 ms
     * Insertion List Sort    : 12703 ms
     * Merge Sort             : 23 ms
     */
    public static void main(String[] args) {
        int size = 100_000;
        var bubbleSort = new Array(size);
        var selectionSort = new Array(size);
        var insertionSort = new Array(size);
        var oddEvenSort = new Array(size);
        var parallelOddEvenSort = new Array(size);
        var insertionListSort = new Array(size);
        var mergeSort = new Array(size);

        for (int i = 0; i < size; i++) {
            long n = RANDOM.nextLong();
            bubbleSort.set(i, n);
            selectionSort.set(i, n);
            insertionSort.set(i, n);
            oddEvenSort.set(i, n);
            parallelOddEvenSort.set(i, n);
            insertionListSort.set(i, n);
            mergeSort.set(i, n);
        }

        System.out.println("Size of elements: " + size);
        speedTest("Bubble Sort           ", bubbleSort, Array::bubbleSort);
        speedTest("Selection Sort        ", selectionSort, Array::selectionSort);
        speedTest("Insertion Sort        ", insertionSort, Array::insertionSort);
        speedTest("Odd Even Sort         ", oddEvenSort, Array::oddEvenSort);
        speedTest("Parallel Odd Even Sort", parallelOddEvenSort, Array::parallelOddEvenSort);
        speedTest("Insertion List Sort   ", insertionListSort, Array::insertionListSort);
        speedTest("Merge Sort            ", mergeSort, Array::mergeSort);
    }

    private static void speedTest(String nameTest, Array a, Consumer<Array> sort) {
        System.out.printf("%s : %d ms%n", nameTest, speedTest(a, sort));
    }

    @SuppressWarnings("java:S112") // Это учебный код
    private static int speedTest(Array a, Consumer<Array> sort) {
        long start = System.currentTimeMillis();
        sort.accept(a);
        long end = System.currentTimeMillis();

        if (!a.isSorted()) {
            System.out.println(a);
            throw new RuntimeException("Sorted array is not sorted");
        }

        return (int) (end - start);
    }
}
