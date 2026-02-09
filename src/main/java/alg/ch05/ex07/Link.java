package alg.ch05.ex07;

public class Link {
    private final long dData;
    Link next;

    public Link(long dData) {
        this.dData = dData;
    }

    public long getDData() {
        return dData;
    }

    @Override
    public String toString() {
        return Long.toString(dData);
    }
}
