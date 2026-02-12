package alg.ch05.home07;

public class MatrixImpl<E> implements Matrix<E> {
    private static final int WIDTH = 5;
    private final int rows;
    private final int cols;
    private final Node<Node<E>> root;

    public MatrixImpl(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        root = newMatrix(rows, cols);
    }

    @Override
    public E get(int row, int col) {
        return getNode(row, col).item;
    }

    @Override
    public void set(int row, int col, E value) {
        getNode(row, col).item = value;
    }

    private Node<E> getNode(int rowIdx, int colIdx) {
        if (rowIdx < 0 || colIdx < 0 || rowIdx >= rows || colIdx >= cols) {
            throw new IndexOutOfBoundsException();
        }

        var row = root;
        for (int i = 0; i < rowIdx; i++) {
            row = row.next;
        }
        var col = row.item;
        for (int j = 0; j < colIdx; j++) {
            col = col.next;
        }

        return col;
    }

    @SuppressWarnings("java:S106")
    @Override
    public void print() {
        for (var row = root; row != null; row = row.next) {
            for (var col = row.item; col != null; col = col.next) {
                System.out.printf("%" + WIDTH + "s", col.item);
            }
            System.out.println();
        }
    }

    private Node<Node<E>> newMatrix(int rows, int cols) {
        Node<Node<E>> col = null;
        for (int i = 0; i < rows; i++) {
            col = new Node<>(newRow(cols), col);
        }

        return col;
    }

    private Node<E> newRow(int cols) {
        Node<E> row = null;
        for (int i = 0; i < cols; i++) {
            row = new Node<>(row);
        }

        return row;
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(Node<E> next) {
            this.next = next;
        }

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
