package alg.ext01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

class SortTest {
    private static final Random RANDOM = new Random();

    /**
     * Size of elements: 100 000
     * Bubble Sort               : 9128 ms
     * Selection Sort            : 1764 ms
     * Insertion Sort            : 378 ms
     * Odd Even Sort             : 4864 ms
     * Parallel Odd Even Sort    : 11149 ms
     * Insertion List Sort       : 12067 ms
     * Merge Sort                : 9 ms
     */
    @ParameterizedTest
    @MethodSource("performanceTestProvider")
    @DisplayName("Тестирование производительности всех алгоритмов сортировок на небольших массивах (100 000 элементов)")
    void performanceTest(Array a, String testName, Consumer<Array> sortMethod) {
        System.out.printf("%-25s : %d ms%n", testName, speedTest(a, sortMethod));
    }

    static Stream<Arguments> performanceTestProvider() {
        int size = 100_000;
        System.out.printf("Size of elements: %,d%n", size);
        var a = randomArray(size);

        return Stream.of(
                Arguments.of(new Array(a), "Bubble Sort", (Consumer<Array>) Array::bubbleSort),
                Arguments.of(new Array(a), "Selection Sort", (Consumer<Array>) Array::selectionSort),
                Arguments.of(new Array(a), "Insertion Sort", (Consumer<Array>) Array::insertionSort),
                Arguments.of(new Array(a), "Odd Even Sort", (Consumer<Array>) Array::oddEvenSort),
                Arguments.of(new Array(a), "Parallel Odd Even Sort", (Consumer<Array>) Array::parallelOddEvenSort),
                Arguments.of(new Array(a), "Insertion List Sort", (Consumer<Array>) Array::insertionListSort),
                Arguments.of(new Array(a), "Merge Sort", (Consumer<Array>) Array::mergeSort)
        );
    }

    /**
     * Size of elements: 700 000
     * Insertion Sort  : 19154 ms
     * Merge Sort      : 71 ms
     */
    @ParameterizedTest
    @MethodSource("performanceQuickSortTestProvider")
    @DisplayName("Тестирование производительности быстрых алгоритмов сортировок на больших массивах")
    void performanceQuickSortTest(Array a, String testName, Consumer<Array> sortMethod) {
        System.out.printf("%-15s : %d ms%n", testName, speedTest(a, sortMethod));
    }

    static Stream<Arguments> performanceQuickSortTestProvider() {
        int size = 700_000;
        System.out.printf("Size of elements: %,d%n", size);
        var a = randomArray(size);

        return Stream.of(
                Arguments.of(new Array(a), "Insertion Sort", (Consumer<Array>) Array::insertionSort),
                Arguments.of(new Array(a), "Merge Sort", (Consumer<Array>) Array::mergeSort)
        );
    }

    static Array randomArray(int size) {
        var a = new Array(size);
        for (int i = 0; i < size; i++) {
            a.set(i, RANDOM.nextLong());
        }

        return a;
    }

    private static int speedTest(Array a, Consumer<Array> sortMethod) {
        long start = System.currentTimeMillis();
        sortMethod.accept(a);
        long end = System.currentTimeMillis();

        if (!a.isSorted()) {
            System.out.println(a);
            throw new RuntimeException("Sorted array is not sorted");
        }

        return (int) (end - start);
    }
}
