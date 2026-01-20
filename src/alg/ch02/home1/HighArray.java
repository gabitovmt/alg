package alg.ch02.home1;

public class HighArray extends AbstractArray {

    public HighArray(int max) {
        super(max);
    }

    public long getMax() {
        if (isEmpty()) {
            return -1;
        }

        long max = a[0];
        for (int i = 1; i < size; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }

        return max;
    }

    public void removeMax() {
        if (isEmpty()) {
            return;
        }

        delete(getMax());
    }

    @Override
    public int find(long value) {
        for (int i = 0; i < size; i++) {
            if (a[i] == value) {
                return i;
            }
        }

        return size - 1;
    }

    @Override
    public void insert(long value) {
        a[size++] = value;
    }

    @Override
    public boolean delete(long value) {
        int i;
        for (i = 0; i < size; i++) {
            if (a[i] == value) {
                break;
            }
        }

        if (i == size) {
            return false;
        }

        for (int k = i; k < size - 1; k++) {
            a[k] = a[k + 1];
        }
        size--;

        return true;
    }

    public void noDups() {
        int nDeleted = 0;
        for (int i = 0; i < size; i++) {
            long value = a[i];
            for (int j = i + 1; j < size; j++) {
                if (value == a[j]) {
                    nDeleted++;
                    continue;
                }
                if (nDeleted > 0) {
                    a[j - nDeleted] = a[j];
                }
            }
            size -= nDeleted;
            nDeleted = 0;
        }
    }
}
