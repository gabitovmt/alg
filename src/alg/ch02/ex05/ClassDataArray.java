package alg.ch02.ex05;

public class ClassDataArray {
    private final Person[] a;
    private int nElems;

    public ClassDataArray(int max) {
        a = new Person[max];
        nElems = 0;
    }

    public Person find(String searchName) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (a[j].getLast().equals(searchName)) {
                // Значение найдено
                break;
            }
        }

        if (j == nElems) {
            // Достигнут последний элемент. Значение не найдено
            return null;
        }

        // Значение найдено
        return a[j];
    }

    public void insert(String last, String first, int age) {
        a[nElems++] = new Person(last, first, age);
    }

    public boolean delete(String searchName) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (a[j].getLast().equals(searchName)) {
                // Значение найдено
                break;
            }
        }

        if (j == nElems) {
            // Найти не удалось
            return false;
        }

        // Сдвиг последующих элементов
        for (int k = j; k < nElems; k++) {
            a[k] = a[k + 1];
        }
        nElems--;

        return true;
    }

    public void displayA() {
        for (int j = 0; j < nElems; j++) {
            a[j].displayPerson();
        }
    }
}