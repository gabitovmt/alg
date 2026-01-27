package workshop.ch04.queue;

import workshop.ch04.pg.MutablePersonGroup;

public interface PersonGroupQueue extends Queue<Integer>, MutablePersonGroup {

    int getFront();
    void setFront(int front);

    int getRear();
    void setRear(int rear);
}
