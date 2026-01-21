package workshop.ch03.insertsort;

import workshop.ch03.AbstractPersonGroup;
import workshop.ch03.ArrowText;
import workshop.ch03.BarMode;
import workshop.ch03.Order;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InsertSortPersonGroup extends AbstractPersonGroup {
    private int codePart;

    public InsertSortPersonGroup(int size, Order order) {
        createPeople(size, order);
    }

    @Override
    public void createPeople(int size, Order order) {
        inner = outer = 1;
        codePart = 1;

        super.createPeople(size, order);
    }

    @Override
    public void sortStep() {
        if (doneFlag) {
            return;
        }

        switch (codePart) {
            case 1:
                tempPerson = a[outer];
                a[outer] = null;
                ++copies;
                inner = outer;
                codePart = 2;
                break;
            case 2:
                ++comps;
                if (inner > 0 && a[inner - 1].height() >= tempPerson.height()) {
                    a[inner] = a[inner - 1];
                    a[inner - 1] = null;
                    ++copies;
                    --inner;
                    break;
                }
                codePart = 3;
                return;
            case 3:
                a[inner] = tempPerson;
                tempPerson = null;
                ++copies;
                ++outer;
                if (outer == a.length) {
                    doneFlag = true;
                }
                codePart = 1;
                return;
            default:
        }
    }

    @Override
    public List<String> statusTexts() {
        return List.of(
                "Copies = " + copies,
                "Comparisons = " + comps
        );
    }

    @Override
    public List<ArrowText> arrowTexts() {
        if (barMode() == BarMode.NARROW) {
            return List.of(
                    ArrowText.ofArrow(outer, 1, Color.RED),
                    ArrowText.ofArrow(inner, 2, Color.BLUE),
                    ArrowText.ofArrow(a.length + 1, 3, Color.MAGENTA)
            );
        }

        var arrowTexts = new ArrayList<ArrowText>();
        arrowTexts.add(ArrowText.of("outer", outer, 1, Color.RED));
        arrowTexts.add(ArrowText.of("inner", inner, 2, Color.BLUE));
        arrowTexts.add(ArrowText.of("temp", a.length, 3, Color.MAGENTA));

        String comment1 = switch (codePart) {
            case 1 -> doneFlag ? "Sort is complete" : "Will copy outer to temp";
            case 2 -> inner > 0 ? "Have compared inner-1 and temp" : "Now inner is 0, so";
            case 3 -> "Will copy temp to inner";
            default -> null;
        };

        String comment2 = null;
        if (codePart == 2) {
            comment2 = inner > 0 && a[inner - 1].height() >= tempPerson.height()
                    ? "Will copy inner-1 to inner"
                    : "No copy necessary";
        }

        if (comment1 != null) {
            arrowTexts.add(ArrowText.ofText(comment1, 0, 3, Color.BLACK));
        }
        if (comment2 != null) {
            arrowTexts.add(ArrowText.ofText(comment2, 0, 4, Color.BLACK));
        }

        return arrowTexts;
    }
}
