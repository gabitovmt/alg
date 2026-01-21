package workshop.ch03;

import java.awt.*;
import java.util.List;

public class PersonGroupImpl extends AbstractPersonGroup {

    public PersonGroupImpl(int size, Order order) {
        createPeople(size, order);
    }

    @Override
    public void createPeople(int size, Order order) {
        a = new Person[size];

        outer = a.length - 1;
        inner = 0;
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

    @Override
    public void sortStep() {
        if (doneFlag) {
            return;
        }

        ++comps;
        if (a[inner].getHeight() > a[inner + 1].getHeight()) {
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

    @Override
    public List<ArrowText> arrowTexts() {
        if (barMode() == BarMode.NARROW) {
            return List.of(
                    ArrowText.ofArrow(outer, 3, Color.RED),
                    ArrowText.ofArrow(inner, 1, Color.BLUE),
                    ArrowText.ofArrow(inner + 1, 1, Color.BLUE)
            );
        }

        ArrowText comment;
        if (doneFlag) {
            comment = ArrowText.ofText("Sort is complete", inner, 2, Color.BLACK);
        } else if (person(inner).getHeight() > person(inner + 1).getHeight()) {
            comment = ArrowText.ofText("Will be swapped", inner, 2, Color.BLUE);
        } else {
            comment = ArrowText.ofText("Will not be swapped", inner, 2, Color.BLUE);
        }

        return List.of(
                ArrowText.of("outer", outer, 3, Color.RED),
                ArrowText.of("inner", inner, 1, Color.BLUE),
                ArrowText.of("inner+1", inner + 1, 1, Color.BLUE),
                comment
        );
    }
}
