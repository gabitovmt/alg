package workshop.ch04.pg;

import workshop.ch04.operation.Operation;
import workshop.ch04.operation.StackOperationMode;

import java.util.function.Function;

public abstract class OperationPersonGroup<T extends Enum<T>> extends AbstractPersonGroup {
    private Operation<T> operation;

    protected Integer run(
            StackOperationMode mode, Integer value,
            Function<MutablePersonGroup, Operation<T>> newOperation
    ) {
        if (operation == null || operation.getMode() != mode) {
            operation = newOperation.apply(this);
        }

        return operation.run(value);
    }

    protected Integer run(
            StackOperationMode mode, Function<MutablePersonGroup, Operation<T>> newOperation
    ) {
        return run(mode, null, newOperation);
    }
}
