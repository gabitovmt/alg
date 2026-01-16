package workshop.ch02.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public abstract class BaseOperation implements Operation {
    private final OperationMode mode;
    private int codePart = 1;
    private final Map<Integer, Consumer<Integer>> actions = new HashMap<>();

    protected BaseOperation(OperationMode mode) {
        this.mode = mode;
    }

    @Override
    public OperationMode getMode() {
        return mode;
    }

    @Override
    public boolean atStart() {
        return codePart == 1;
    }

    @Override
    public void run(Integer value) {
        Objects.requireNonNull(actions.get(codePart)).accept(value);
    }

    protected void setCodePart(int codePart) {
        this.codePart = codePart;
    }

    protected void addAction(int codePart, Consumer<Integer> action) {
        this.actions.put(codePart, action);
    }
}
