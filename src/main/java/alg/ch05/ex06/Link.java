package alg.ch05.ex06;

public class Link {
    private final long dData;
    Link next;

    public Link(long dData) {
        this.dData = dData;
    }

    public long getDData() {
        return dData;
    }

    @SuppressWarnings("java:S106")
    public void displayLink() {
        System.out.print(dData + " ");
    }
}
