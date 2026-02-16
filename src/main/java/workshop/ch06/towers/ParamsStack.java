package workshop.ch06.towers;

public class ParamsStack extends Stack<Params> {

    public ParamsStack(int maxSize) {
        super(Params.class, maxSize);
    }
}
