package workshop.ch03;

import java.awt.*;

public abstract class AbstractPersonGroup implements PersonGroup {
    protected Person[] a;
    protected Person tempPerson;

    protected Integer swaps;
    protected Integer comps;
    protected Integer copies;

    protected boolean doneFlag;

    protected int inner;
    protected int outer;

    @Override
    public Person person(int index) {
        return a[index];
    }

    @Override
    public Person tempPerson() {
        return tempPerson;
    }

    @Override
    public int size() {
        return a.length;
    }

    @Override
    public boolean doneFlag() {
        return doneFlag;
    }

    @Override
    public BarMode barMode() {
        return a.length <= 12 ? BarMode.NORMAL : BarMode.NARROW;
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
        copies = 0;
        doneFlag = false;
        inner = 0;
        outer = 0;

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
