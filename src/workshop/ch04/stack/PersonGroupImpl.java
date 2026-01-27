package workshop.ch04.stack;

import workshop.ch04.NoneOperation;
import workshop.ch04.Operation;
import workshop.ch04.OperationMode;
import workshop.ch04.Person;
import workshop.ch04.PersonGroup;

import java.util.Arrays;
import java.util.function.Function;

public class PersonGroupImpl implements PersonGroup {
    private final Person[] stackArray = new Person[DEFAULT_CAPACITY];
    private int size = 0;
    private String note = DEFAULT_NOTE;

    private Operation operation = new NoneOperation(this);

    @Override
    public int getCapacity() {
        return stackArray.length;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getCurrentPosition() {
        return getSize() - 1;
    }

    @Override
    public Person getPerson(int index) {
        return stackArray[index];
    }

    @Override
    public Person getLastPerson() {
        return stackArray[size - 1];
    }

    @Override
    public void setLastPerson(Person person) {
        stackArray[size - 1] = person;
    }

    @Override
    public String getArrowText() {
        return "Top";
    }

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public void setDefaultNote() {
        note = DEFAULT_NOTE;
    }

    @Override
    public void reset() {
        Arrays.fill(stackArray, null);
        size = 0;
    }

    private Integer run(OperationMode mode, Integer value, Function<PersonGroup, Operation> newOperation) {
        if (operation.getMode() != mode) {
            operation = newOperation.apply(this);
        }

        return operation.run(value);
    }

    private Integer run(OperationMode mode, Function<PersonGroup, Operation> newOperation) {
        return run(mode, null, newOperation);
    }

    @Override
    public void newStack() {
        run(OperationMode.NEW_STACK, NewStackOperation::new);
    }

    @Override
    public void doFill() {
        run(OperationMode.FILL, DoFillOperation::new);
    }

    @Override
    public void push(Integer value) {
        run(OperationMode.PUSH, value, PushOperation::new);
    }

    @Override
    public Integer pop() {
        return run(OperationMode.POP, PopOperation::new);
    }

    @Override
    public Integer peek() {
        return run(OperationMode.PEEK, PeekOperation::new);
    }
}
