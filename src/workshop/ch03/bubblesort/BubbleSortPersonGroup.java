package workshop.ch03.bubblesort;

import workshop.ch03.AbstractPersonGroup;
import workshop.ch03.ArrowText;
import workshop.ch03.BarMode;
import workshop.ch03.Order;

import java.awt.*;
import java.util.List;

public class BubbleSortPersonGroup extends AbstractPersonGroup {

    public BubbleSortPersonGroup(int size, Order order) {
        createPeople(size, order);
    }

    @Override
    public void createPeople(int size, Order order) {
        super.createPeople(size, order);

        inner = 0;
        outer = size - 1;
    }

    @Override
    public void sortStep() {
        if (doneFlag) {
            return;
        }

        ++comps;
        if (a[inner].height() > a[inner + 1].height()) {
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
    public List<String> statusTexts() {
        return List.of(
                "Swaps = " + swaps,
                "Comparisons = " + comps
        );
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
        } else if (person(inner).height() > person(inner + 1).height()) {
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
