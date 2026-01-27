package workshop.ch04.stack.impl;

import workshop.ch04.operation.StackOperationMode;
import workshop.ch04.pg.ArrowText;
import workshop.ch04.pg.OperationPersonGroup;
import workshop.ch04.stack.PersonGroupStack;

import java.util.Collection;
import java.util.List;

public class PersonGroupStackImpl extends OperationPersonGroup<StackOperationMode> implements PersonGroupStack {

    @Override
    public Collection<ArrowText> getArrowTexts() {
        return List.of(new ArrowText("Top", 1, getSize() - 1));
    }

    @Override
    public void newStack() {
        run(StackOperationMode.NEW_STACK, NewStackOperation::new);
    }

    @Override
    public void doFill() {
        run(StackOperationMode.FILL, DoFillOperation::new);
    }

    @Override
    public void push(Integer value) {
        run(StackOperationMode.PUSH, value, PushOperation::new);
    }

    @Override
    public Integer pop() {
        return run(StackOperationMode.POP, PopOperation::new);
    }

    @Override
    public Integer peek() {
        return run(StackOperationMode.PEEK, PeekOperation::new);
    }
}
