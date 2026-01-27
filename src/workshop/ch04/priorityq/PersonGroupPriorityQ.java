package workshop.ch04.priorityq;

import workshop.ch04.pg.MutablePersonGroup;

public interface PersonGroupPriorityQ extends PriorityQ<Integer>, MutablePersonGroup {

    int getFront();
    void setFront(int front);

    int getRear();
    void setRear(int rear);

    int getPosition();
    void setPosition(int position);
}
