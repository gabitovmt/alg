package alg.ch02.ex04;

// Работа с классом упорядоченного массива
public class OrderedApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        int maxSize = 100;
        var arr = new OrdArray(maxSize);

        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(0);
        arr.insert(66);
        arr.insert(33);

        arr.display();

        int searchKey = 55;
        if (arr.find(searchKey) != arr.size()) {
            System.out.println("Found " + searchKey);
        } else {
            System.out.println("Can't find " + searchKey);
        }

        arr.delete(0);
        arr.delete(55);
        arr.delete(99);

        arr.display();
    }
}
