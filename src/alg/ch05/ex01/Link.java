package alg.ch05.ex01;

public class Link {
    private final int iData;    // Данные (ключ)
    private final double dData; // Данные
    Link next;      // Следующий элемент в списке

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
        System.out.printf("{%d, %f} ", iData, dData);
    }
}
