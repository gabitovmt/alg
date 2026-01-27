package workshop.ch04;

public class NoneOperation extends AbstractOperation {

    public NoneOperation(PersonGroup group) {
        super(OperationMode.NONE, group);

        addAction(1, it -> {
        });
    }
}
