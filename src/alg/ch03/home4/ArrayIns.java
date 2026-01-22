package alg.ch03.home4;

// Массив с сортировкой методом вставки
public class ArrayIns {
    private final long[] a;
    private int size;

    public ArrayIns(int max) {
        a = new long[max];
        size = 0;
    }

    public void insert(long value) {
        a[size++] = value;
    }

    @SuppressWarnings("java:S106")
    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public void insertionSort() {
        for (int out = 1; out < size; out++) {
            long temp = a[out];
            int in = out;
            while (in > 0 && a[in - 1] >= temp) {
                a[in] = a[in - 1];
                --in;
            }
            a[in] = temp;
        }
    }

    public long median() {
        return a[size /2];
    }

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var array = new ArrayIns(10);
        array.insert(3);
        array.insert(2);
        array.insert(1);
        array.insert(4);
        array.insert(5);
        array.display();

        array.insertionSort();
        array.display();

        System.out.println("Median: " + array.median());
    }
}
