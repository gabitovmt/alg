package workshop.ch03.selectsort;

import workshop.ch03.AbstractPersonGroup;
import workshop.ch03.ArrowText;
import workshop.ch03.BarMode;
import workshop.ch03.Order;

import java.awt.*;
import java.util.List;

public class SelectSortPersonGroup extends AbstractPersonGroup {
    protected int min;
    protected boolean searchFlag;

    public SelectSortPersonGroup(int size, Order order) {
        createPeople(size, order);
    }

    @Override
    public void createPeople(int size, Order order) {
        super.createPeople(size, order);

        inner = 1;
        outer = 0;
        min = 0;
        searchFlag = true;
    }

    public void sortStep() {
        if (doneFlag) {
            return;
        }

        if (searchFlag) {
            ++comps;
            if (a[inner].height() < a[min].height()) {
                min = inner;
            }

            ++inner;
            if (inner > a.length - 1) {
                searchFlag = false;
            }
        } else {
            if (min != outer) {
                swap(outer, min);
                ++swaps;
            }

            outer++;
            inner = outer + 1;
            min = outer;
            searchFlag = true;
            if (outer > a.length - 2) {
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
                    ArrowText.ofArrow(outer, 1, Color.RED),
                    ArrowText.ofArrow(inner, 2, Color.BLUE),
                    ArrowText.ofArrow(min, 3, Color.MAGENTA)
            );
        }

        String comment;
        if (doneFlag) {
            comment = "Sort is complete";
        } else if (searchFlag) {
            comment = "Searching for minimum";
        } else if (outer == min) {
            comment = "No swap necessary";
        } else {
            comment = "Will swap outer & min";
        }

        return List.of(
                ArrowText.of("outer", outer, 1, Color.RED),
                ArrowText.of("inner", inner, 2, Color.BLUE),
                ArrowText.of("min", min, 3, Color.MAGENTA),
                ArrowText.ofText(comment, 0, 4, Color.BLACK)
        );
    }
}
