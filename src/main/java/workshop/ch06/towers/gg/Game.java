package workshop.ch06.towers.gg;

public interface Game {
    String note();
    Tower[] towers();
    Tower tower(int index);
    boolean isDone();
    int disksCount();
}
