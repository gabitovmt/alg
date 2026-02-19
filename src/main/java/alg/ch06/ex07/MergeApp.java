package alg.ch06.ex07;

import java.util.Arrays;

// Слияние двух массивов
public class MergeApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        int[] arrayA = {23, 47, 81, 95};
        int[] arrayB = {7, 14, 39, 55, 62, 74};
        int[] arrayC = new int[10];

        merge(arrayA, arrayB, arrayC);
        System.out.println(Arrays.toString(arrayC));
    }

    // Слияние массивов A и B в массив C
    private static void merge(
            int[] arrayA, int[] arrayB, int[] arrayC
    ) {
        int aDex = 0;
        int bDex = 0;
        int cDex = 0;
        while (aDex < arrayA.length && bDex < arrayB.length) {
            // Ни один из массивов не пуст
            if (arrayA[aDex] < arrayB[bDex]) {
                arrayC[cDex++] = arrayA[aDex++];
            } else {
                arrayC[cDex++] = arrayB[bDex++];
            }
        }

        while (aDex < arrayA.length) {
            // Массив arrayB пуст, в arrayA остались элементы
            arrayC[cDex++] = arrayA[aDex++];
        }
        while (bDex < arrayB.length) {
            // Массив arrayA пуст, в arrayB остались элементы
            arrayC[cDex++] = arrayB[bDex++];
        }
    }
}
