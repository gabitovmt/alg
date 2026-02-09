package workshop.ch04.operation;

import java.util.function.Supplier;

public class OperationExecutor<T extends Enum<T>> {
    private Operation<T> operation;

    public Integer run(T mode, Integer value, Supplier<Operation<T>> newOperation) {
        if (operation == null || operation.getMode() != mode) {
            operation = newOperation.get();
        }

        return operation.run(value);
    }

    public Integer run(T mode, Supplier<Operation<T>> newOperation) {
        return run(mode, null, newOperation);
    }
}
