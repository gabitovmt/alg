package workshop.ch02.orderedarray;

import workshop.ch02.BasePersonGroup;
import workshop.ch02.Operation;

public class PersonGroupImpl extends BasePersonGroup {
    public PersonGroupImpl(int size) {
        super(size);
    }

    @Override
    protected Operation newArrayOperation(BasePersonGroup group) {
        return new NewArrayOperation(group);
    }

    @Override
    protected Operation fillOperation(BasePersonGroup group) {
        return new FillOperation(group);
    }

    @Override
    protected Operation insertOperation(BasePersonGroup group) {
        return new InsertOperation(group);
    }

    @Override
    protected Operation findOperation(BasePersonGroup group) {
        return new FindOperation(group);
    }

    @Override
    protected Operation deleteOperation(BasePersonGroup group) {
        return new DeleteOperation(group);
    }
}
