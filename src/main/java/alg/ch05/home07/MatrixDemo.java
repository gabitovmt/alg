package alg.ch05.home07;

// Программный проект 5.7
// Матрица
public class MatrixDemo {

    public static void main(String[] args) {
        final int rows = 7;
        final int cols = 10;

        var m = Matrix.<Integer>matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m.set(i, j, i * cols + j);
            }
        }
        m.print();
    }
}
