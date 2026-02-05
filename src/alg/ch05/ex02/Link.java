package alg.ch05.ex02;

public class Link {
    private final int iData;
    private final double dData;
    Link next;

    public Link(int iData, double dData) {
        this.iData = iData;
        this.dData = dData;
    }

    public int getIData() {
        return iData;
    }

    public double getDData() {
        return dData;
    }

    @SuppressWarnings("java:S106")
    public void displayLink() {
        System.out.printf("{%d, %.2f} ", iData, dData);
    }
}
