package workshop.ch04.priorityq.impl;

import workshop.ch04.operation.OperationExecutor;
import workshop.ch04.operation.QueueOperationMode;
import workshop.ch04.pg.AbstractPersonGroup;
import workshop.ch04.pg.ArrowText;
import workshop.ch04.priorityq.PersonGroupPriorityQ;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class PersonGroupPriorityQImpl extends AbstractPersonGroup implements PersonGroupPriorityQ {
    protected static final int NULL_POSITION = Integer.MIN_VALUE;

    protected final OperationExecutor<QueueOperationMode> executor = new OperationExecutor<>();
    protected int front = -1;
    protected int rear = 0;
    protected int position = NULL_POSITION;

    @Override
    public int getFront() {
        return front;
    }

    @Override
    public void setFront(int front) {
        this.front = front;
    }

    @Override
    public int getRear() {
        return rear;
    }

    @Override
    public void setRear(int rear) {
        this.rear = rear;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public Collection<ArrowText> getArrowTexts() {
        var arrowTexts = new ArrayList<ArrowText>();
        arrowTexts.add(new ArrowText("Rear", Color.BLUE, 3, getRear()));
        arrowTexts.add(new ArrowText("Front", Color.RED, 2, getFront()));
        if (getPosition() != NULL_POSITION) {
            arrowTexts.add(new ArrowText("", Color.BLACK, 1, getPosition()));
        }

        return arrowTexts;
    }

    @Override
    public void reset() {
        super.reset();
        front = -1;
        rear = 0;
        position = NULL_POSITION;
    }

    @Override
    public void newQueue() {
        executor.run(QueueOperationMode.NEW_QUEUE, () -> new NewQueueOperation(this));
    }

    @Override
    public void doFill() {
        executor.run(QueueOperationMode.FILL, () -> new DoFillOperation(this));
    }

    @Override
    public void insert(Integer value) {
        executor.run(QueueOperationMode.INSERT, value, () -> new InsertOperation(this));
    }

    @Override
    public Integer remove() {
        return executor.run(QueueOperationMode.REMOVE, () -> new RemoveOperation(this));
    }

    @Override
    public Integer peek() {
        return executor.run(QueueOperationMode.PEEK, () -> new PeekOperation(this));
    }
}
