package alg.ch02.home01;

public class OrderedArray extends AbstractArray {

    public OrderedArray(int max) {
        super(max);
    }

    @Override
    public int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = size - 1;
        int curIn;

        while (true) {
            curIn = (lowerBound + upperBound) / 2;
            if (a[curIn] == searchKey) {
                return curIn;
            }
            if (lowerBound > upperBound) {
                return size;
            }

            if (a[curIn] < searchKey) {
                lowerBound = curIn + 1;
            } else {
                upperBound = curIn - 1;
            }
        }
    }

    @Override
    public void insert(long value) {
        int lowerBound = 0;
        int upperBound = size - 1;
        int curIn = (lowerBound + upperBound) / 2;

        while (true) {
            if (curIn >= size) {
                a[size++] = value;
                return;
            }

            if (a[curIn] == value) {
                return;
            }

            if (lowerBound > upperBound) {
                if (a[curIn] < value) {
                    curIn++;
                }
                break;
            }

            curIn = (lowerBound + upperBound) / 2;
            if (a[curIn] < value) {
                lowerBound = curIn + 1;
            } else {
                upperBound = curIn - 1;
            }
        }

        for (int k = size; k > curIn; k--) {
            a[k] = a[k - 1];
        }

        a[curIn] = value;
        size++;
    }

    @Override
    public boolean delete(long value) {
        int j = find(value);

        if (j == size) {
            return false;
        }

        for (int k = j; k < size - 1; k++) {
            a[k] = a[k + 1];
        }

        size--;

        return true;
    }

    public void merge(Array a) {
        for (int i = 0; i < a.size(); i++) {
            insert(a.get(i));
        }
    }
}
