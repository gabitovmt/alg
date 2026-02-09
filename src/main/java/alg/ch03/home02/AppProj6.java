package alg.ch03.home02;

import java.util.Random;

// Программный проект 3.6
public class AppProj6 {
    private static final Random R = new Random();

    public static void main(String[] args) {
        var array = new Array(10);
        array.insert(3);
        array.insert(4);
        array.insert(4);
        array.insert(2);
        array.insert(1);
        array.insert(1);
        array.insert(6);
        array.insert(6);
        array.display();

        array.insertionSortAndNoDups();
        array.display();

        array = new Array(100);
        for (int i = 0; i < 100; i++) {
            array.insert(R.nextInt(100));
        }
        array.display();

        array.insertionSortAndNoDups();
        array.display();
    }
}
