package alg.ch03.home2;

// Программный проект 3.2
public class AppProj2 {

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
