package alg.ch06.ex05;

public class OrdArray {
    private final long[] a;
    private int nElems;

    public OrdArray(int max) {
        a = new long[max];
        nElems = 0;
    }

    public int size() {
        return nElems;
    }

    public int find(long searchKey) {
        return recFind(searchKey, 0, nElems - 1);
    }

    private int recFind(long searchKey, int lowerBound, int upperBound) {
        int curIn = (lowerBound + upperBound) / 2;
        if (a[curIn] == searchKey) {
            return curIn;
        }
        if (lowerBound > upperBound) {
            return nElems;
        }

        return a[curIn] < searchKey
                ? recFind(searchKey, curIn + 1, upperBound)
                : recFind(searchKey, lowerBound, curIn - 1);
    }

    public void insert(long value) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn = (lowerBound + upperBound) / 2;

        while (true) {
            if (curIn >= nElems) {
                a[nElems++] = value;
                return;
            }

            if (a[curIn] == value) {
                // Дубликаты запрещены
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

        for (int k = nElems; k > curIn; k--) {
            a[k] = a[k - 1];
        }

        a[curIn] = value;
        nElems++;
    }

    // Вывод содержимого массива
    @SuppressWarnings("java:S106")
    public void display() {
        for (int j = 0; j < nElems; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println();
    }
}
