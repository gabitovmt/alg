package workshop.ch06.towers.gg;

public interface Game {

    String note();

    void setNote(String note);

    Tower[] towers();

    Tower tower(int index);

    boolean isDone();

    boolean canRunning();

    int disksCount();

    void reset(int disksCount);

    void newGame(Integer disksCount);

    void step();

    void startDrag(int x, int y);

    void endDrag(int x, int y);
}
