package workshop.ch03;

import java.awt.*;

public abstract class AbstractPersonGroup implements PersonGroup {
    protected Person[] a;

    protected int swaps;
    protected int comps;
    protected boolean doneFlag;

    protected int inner;
    protected int outer;

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

    @Override
    public void createPeople(int size, Order order) {
        a = new Person[size];

        comps = 0;
        swaps = 0;
        doneFlag = false;

        if (order == Order.RANDOM) {
            for (int i = 0; i < a.length; ++i) {
                a[i] = new Person(Utils.nextHeight(), Utils.nextColor());
            }
        } else {
            for (int i = 0; i < a.length; ++i) {
                int height = 199 - 199 * i / a.length;
                var color = new Color(255 - height, 85 * (i % 3), height);
                a[i] = new Person(height, color);
            }
        }
    }
}
