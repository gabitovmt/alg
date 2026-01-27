package workshop.ch04.queue.impl;

import workshop.ch04.operation.OperationExecutor;
import workshop.ch04.operation.QueueOperationMode;
import workshop.ch04.pg.AbstractPersonGroup;
import workshop.ch04.pg.ArrowText;
import workshop.ch04.queue.PersonGroupQueue;

import java.util.Collection;
import java.util.List;

public class PersonGroupQueueImpl extends AbstractPersonGroup implements PersonGroupQueue {
    protected final OperationExecutor<QueueOperationMode> executor = new OperationExecutor<>();
    protected int front = 0;
    protected int rear = -1;

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
    public Collection<ArrowText> getArrowTexts() {
        return List.of();
    }

    @Override
    public void reset() {
        super.reset();
        front = 0;
        rear = -1;
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
        return 0;
    }

    @Override
    public Integer peek() {
        return 0;
    }
}
