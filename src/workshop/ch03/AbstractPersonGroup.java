package workshop.ch03;

public abstract class AbstractPersonGroup implements PersonGroup {
    protected Person[] a;

    protected int swaps;
    protected int comps;
    protected int inner;
    protected int outer;
    protected boolean doneFlag;

    @Override
    public Person person(int index) {
        return a[index];
    }

    @Override
    public int length() {
        return a.length;
    }

    @Override
    public int swaps() {
        return swaps;
    }

    @Override
    public int comps() {
        return comps;
    }

    @Override
    public int inner() {
        return inner;
    }

    @Override
    public int outer() {
        return outer;
    }

    @Override
    public boolean doneFlag() {
        return doneFlag;
    }

    @Override
    public BarMode barMode() {
        return a.length <= 10 ? BarMode.NORMAL : BarMode.NARROW;
    }

    protected void swap(int a, int b) {
        var temp = this.a[a];
        this.a[a] = this.a[b];
        this.a[b] = temp;
    }
}
