package workshop.ch02;

public class NoneOperation extends AbstractOperation {

    public NoneOperation() {
        super(OperationMode.NONE);
        addAction(1, it -> {
        });
    }
}
