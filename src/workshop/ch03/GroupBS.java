package workshop.ch03;

import java.awt.*;

public class GroupBS implements PersonGroup {
    private Person[] array;
    private int length;

    private int swaps;
    private int comps;
    private int inner;
    private int outer;
    private boolean doneFlag;

    public GroupBS(int var1, Order initOrder) {
        length = var1;
        array = new Person[length];

        outer = length - 1;
        inner = 0;
        comps = 0;
        swaps = 0;
        doneFlag = false;

        if (initOrder == Order.RANDOM) {
            for (int i = 0; i < length; ++i) {
                array[i] = new Person(Utils.nextHeight(), Utils.nextColor());
            }
        } else {
            for (int i = 0; i < length; ++i) {
                int height = 199 - 199 * i / length;
                var color = new Color(255 - height, 85 * (i % 3), height);
                array[i] = new Person(height, color);
            }
        }
    }

    @Override
    public Person person(int index) {
        return array[index];
    }

    @Override
    public int length() {
        return length;
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
        return length <= 10 ? BarMode.NORMAL : BarMode.NARROW;
    }

    public void sortStep() {
        if (!doneFlag) {
            ++comps;
            if (array[inner].getHeight() > array[inner + 1].getHeight()) {
                swap(inner, inner + 1);
                ++swaps;
            }

            inner++;
            if (inner > outer - 1) {
                inner = 0;
                --outer;
                if (outer == 0) {
                    doneFlag = true;
                }
            }
        }
    }

    private void swap(int a, int b) {
        var temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
