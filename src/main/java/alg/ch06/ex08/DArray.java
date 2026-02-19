package alg.ch06.ex08;

// Рекурсивная реализация сортировки слиянием
public class DArray {
    private final long[] a;
    private int nElems;

    public DArray(int max) {
        a = new long[max];
    }

    public void insert(long value) {
        a[nElems++] = value;
    }

    @SuppressWarnings("java:S106")
    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    // Вызывается из main()
    public void mergeSort() {
        // Рабочая область
        long[] workSpace = new long[nElems];
        recMergeSort(workSpace, 0, nElems - 1);
    }

    private void recMergeSort(long[] workSpace, int lowerBound, int upperBound) {
        if (lowerBound == upperBound) {
            // Только один элемент. Сортировка не требуется
            return;
        }

        // Поиск середины
        int mid = (lowerBound + upperBound) / 2;
        // Сортировка нижней половины
        recMergeSort(workSpace, lowerBound, mid);
        // Сортировка верхней половины
        recMergeSort(workSpace, mid + 1, upperBound);
        // Слияние
        merge(workSpace, lowerBound, mid + 1, upperBound);
    }

    private void merge(long[] workSpace, int lowPtr, int highPtr, int upperBound) {
        // Индекс в рабочей области
        int i = 0;
        int lowerBound = lowPtr;
        int mid = highPtr - 1;
        // Количество элементов
        int n = upperBound - lowerBound + 1;

        while (lowPtr <= mid && highPtr <= upperBound) {
            if (a[lowPtr] <= a[highPtr]) {
                workSpace[i++] = a[lowPtr++];
            } else {
                workSpace[i++] = a[highPtr++];
            }
        }

        while (lowPtr <= mid) {
            workSpace[i++] = a[lowPtr++];
        }
        while (highPtr <= upperBound) {
            workSpace[i++] = a[highPtr++];
        }

        for (i = 0; i < n; i++) {
            a[lowerBound + i] = workSpace[i];
        }
    }
}
