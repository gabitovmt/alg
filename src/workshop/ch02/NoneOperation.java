package workshop.ch02;

public class NoneOperation extends BaseOperation {

    public NoneOperation() {
        super(OperationMode.NONE);
        addAction(1, it -> {
        });
    }
}
