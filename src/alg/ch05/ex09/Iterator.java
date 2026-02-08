package alg.ch05.ex09;

public interface Iterator {
    void reset();
    boolean atEnd();
    void next();
    long getCurrent();
    void insertBefore(long value);
    void insertAfter(long value);
    long remove();
}
