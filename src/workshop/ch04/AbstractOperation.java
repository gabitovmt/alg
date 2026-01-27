package workshop.ch04;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractOperation implements Operation {
    private final OperationMode mode;
    private int codePart = 1;
    private final Map<Integer, Consumer<Integer>> actions = new HashMap<>();

    protected final PersonGroup group;
    protected Integer arg0;
    protected Integer result;

    protected AbstractOperation(OperationMode mode, PersonGroup group) {
        this.mode = mode;
        this.group = group;

        addAction(1, it -> run1());
        addAction(2, it -> run2());
        addAction(3, it -> run3());
        addAction(4, it -> run4());
        addAction(5, it -> run5());
    }

    @Override
    public OperationMode getMode() {
        return mode;
    }

    @Override
    public Integer run(Integer value) {
        arg0 = value;
        actions.get(codePart).accept(value);

        return result;
    }

    protected void setCodePart(int codePart) {
        this.codePart = codePart;
    }

    protected void addAction(int codePart, Consumer<Integer> action) {
        actions.put(codePart, action);
    }

    protected void run1() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void run2() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void run3() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void run4() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void run5() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
