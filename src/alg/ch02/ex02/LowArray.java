package alg.ch02.ex02;

// Класс массива с низкоуровневым интерфейсом
public class LowArray {
    private final long[] a;

    public LowArray(int size) {
        a = new long[size];
    }

    public void setElem(int index, long value) {
        a[index] = value;
    }

    public long getElem(int index) {
        return a[index];
    }
}
