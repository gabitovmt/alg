package alg.ext01;

import java.util.Random;
import java.util.function.Consumer;

@SuppressWarnings("java:S106")
public class SortDemo {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        int size = 100_000;
        var bubbleSort = new Array(size);
        var selectionSort = new Array(size);
        var insertionSort = new Array(size);
        var oddEvenSort = new Array(size);
        var parallelOddEvenSort = new Array(size);
        var insertionListSort = new Array(size);

        for (int i = 0; i < size; i++) {
            long n = RANDOM.nextLong();
            bubbleSort.set(i, n);
            selectionSort.set(i, n);
            insertionSort.set(i, n);
            oddEvenSort.set(i, n);
            parallelOddEvenSort.set(i, n);
            insertionListSort.set(i, n);
        }

        System.out.println("Size of elements: " + size);
        speedTest("Bubble Sort           ", bubbleSort, Array::bubbleSort);
        speedTest("Selection Sort        ", selectionSort, Array::selectionSort);
        speedTest("Insertion Sort        ", insertionSort, Array::insertionSort);
        speedTest("Odd Even Sort         ", oddEvenSort, Array::oddEvenSort);
        speedTest("Parallel Odd Even Sort", parallelOddEvenSort, Array::parallelOddEvenSort);
        speedTest("Insertion List Sort   ", insertionListSort, Array::insertionListSort);
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
