package alg.ch03.ex04;

public class ArrayInOb {
    private final Person[] a;
    private int nElems;

    public ArrayInOb(int max) {
        a = new Person[max];
        nElems = 0;
    }

    public void insert(String lastName, String firstName, int age) {
        a[nElems++] = new Person(lastName, firstName, age);
    }

    @SuppressWarnings("java:S106")
    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.println("  " + a[i]);
        }
        System.out.println();
    }

    public void insertionSort() {
        for (int out = 1; out < nElems; out++) {
            var temp = a[out];
            int in = out;
            // String#compareTo(..) - лексикографическое сравнение
            while (in > 0 && a[in - 1].lastName().compareTo(temp.lastName()) > 0) {
                a[in] = a[in - 1];
                --in;
            }
            a[in] = temp;
        }
    }
}
