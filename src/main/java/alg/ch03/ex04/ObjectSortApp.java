package alg.ch03.ex04;

// Сортировка объектов (с применением сортировки методом вставки)
public class ObjectSortApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var arr = new ArrayInOb(100);

        arr.insert("Evans", "Patty", 24);
        arr.insert("Smith", "Lorraine", 37);
        arr.insert("Yee", "Tom", 43);
        arr.insert("Adams", "Henry", 63);
        arr.insert("Hashimoto", "Sato", 21);
        arr.insert("Stimson", "Michail", 29);
        arr.insert("Velasquez", "Jose", 72);
        arr.insert("Lamarque", "Kenn", 54);
        arr.insert("Vang", "Minh", 22);
        arr.insert("Creswell", "Lucinda", 18);

        System.out.println("Before sorting: ");
        arr.display();

        System.out.println("After sorting: ");
        arr.insertionSort();
        arr.display();
    }
}
