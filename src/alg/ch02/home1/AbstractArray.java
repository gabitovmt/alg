package alg.ch02.home1;

public abstract class AbstractArray implements Array {
    protected final long[] a;
    protected int size = 0;

    protected AbstractArray(int max) {
        a = new long[max];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public long get(int index) {
        return a[index];
    }

    @SuppressWarnings("java:S106")
    @Override
    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
