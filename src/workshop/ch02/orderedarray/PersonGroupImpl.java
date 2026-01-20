package workshop.ch02.orderedarray;

import workshop.ch02.AbstractPersonGroup;
import workshop.ch02.Operation;

public class PersonGroupImpl extends AbstractPersonGroup {
    public PersonGroupImpl(int size) {
        super(size);
    }

    @Override
    protected Operation newArrayOperation(AbstractPersonGroup group) {
        return new NewArrayOperation(group);
    }

    @Override
    protected Operation fillOperation(AbstractPersonGroup group) {
        return new FillOperation(group);
    }

    @Override
    protected Operation insertOperation(AbstractPersonGroup group) {
        return new InsertOperation(group);
    }

    @Override
    protected Operation findOperation(AbstractPersonGroup group) {
        return new FindOperation(group);
    }

    @Override
    protected Operation deleteOperation(AbstractPersonGroup group) {
        return new DeleteOperation(group);
    }
}
