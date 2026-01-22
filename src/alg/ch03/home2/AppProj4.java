package alg.ch03.home2;

import java.util.Random;

// Программный проект 3.3
public class AppProj4 {
    private static final Random R = new Random();

    public static void main(String[] args) {
        var array = new Array(10);
        array.insert(6);
        array.insert(5);
        array.insert(4);
        array.insert(3);
        array.insert(2);
        array.insert(1);
        array.insert(0);
        array.display();

        array.oddEvenSort();
        array.display();

        array = new Array(50);
        for (int i = 0; i < 50; i++) {
            array.insert(R.nextInt(100));
        }
        array.display();

        array.oddEvenSort();
        array.display();
    }
}
