package workshop.ch04.stack.impl;

import workshop.ch04.operation.OperationExecutor;
import workshop.ch04.operation.StackOperationMode;
import workshop.ch04.pg.AbstractPersonGroup;
import workshop.ch04.pg.ArrowText;
import workshop.ch04.stack.PersonGroupStack;

import java.util.Collection;
import java.util.List;

public class PersonGroupStackImpl extends AbstractPersonGroup implements PersonGroupStack {
    protected final OperationExecutor<StackOperationMode> executor = new OperationExecutor<>();

    @Override
    public Collection<ArrowText> getArrowTexts() {
        return List.of(new ArrowText("Top", 1, getSize() - 1));
    }

    @Override
    public void newStack() {
        executor.run(StackOperationMode.NEW_STACK, () -> new NewStackOperation(this));
    }

    @Override
    public void doFill() {
        executor.run(StackOperationMode.FILL, () -> new DoFillOperation(this));
    }

    @Override
    public void push(Integer value) {
        executor.run(StackOperationMode.PUSH, () -> new PushOperation(this));
    }

    @Override
    public Integer pop() {
        return executor.run(StackOperationMode.POP, () -> new PopOperation(this));
    }

    @Override
    public Integer peek() {
        return executor.run(StackOperationMode.PEEK, () -> new PeekOperation(this));
    }
}
