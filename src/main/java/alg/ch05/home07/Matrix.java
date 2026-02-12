package alg.ch05.home07;

public interface Matrix<E> {

    E get(int row, int col);

    void set(int row, int col, E value);

    void print();

    static <E> Matrix<E> matrix(int rows, int cols) {
        return new MatrixImpl<>(rows, cols);
    }
}
