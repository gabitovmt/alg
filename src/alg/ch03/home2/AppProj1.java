package alg.ch03.home2;

// Программный проект 3.1
public class AppProj1 {

    public static void main(String[] args) {
        var array = new Array(10);
        array.insert(3);
        array.insert(2);
        array.insert(1);
        array.display();

        array.bubbleSort();
        array.display();
    }
}
