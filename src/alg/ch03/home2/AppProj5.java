package alg.ch03.home2;

// Программный проект 3.5
public class AppProj5 {

    public static void main(String[] args) {
        var array = new Array(100);
        for (int i = 100; i > 0; i--) {
            array.insert(i);
        }
        array.insertionSortWithLog();
        // Comp: 5049 Copy: 5148
        // Подтверждает теоретическую сложность O(N^2)
        // 5049 + 5148 = 10197
        // 100^2 = 10000

        // Частично отсортированный массив
        array = new Array(100);
        for (int i = 0; i < 100; i++) {
            if (i == 25) {
                array.insert(100);
            } else if (i == 50) {
                array.insert(110);
            } else if (i == 75) {
                array.insert(120);
            } else {
                array.insert(i);
            }
        }
        array.insertionSortWithLog();
        // Comp: 243 Copy: 342
    }
}
