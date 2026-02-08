package alg.ch02.home01;

public interface Array {
    int size();
    boolean isEmpty();
    long get(int index);
    void display();
    int find(long value);
    void insert(long value);
    boolean delete(long value);
}
