package workshop.ch03.bubblesort;

import workshop.ch03.AbstractFrame;
import workshop.ch03.PersonGroup;
import workshop.ch03.PersonGroupImpl;

import javax.swing.*;

public class BubbleSortFrame extends AbstractFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BubbleSortFrame::new);
    }

    public BubbleSortFrame() {
        super("BubbleSort Workshop");
    }

    @Override
    protected PersonGroup newPersonGroup() {
        return new PersonGroupImpl(size, order);
    }
}
