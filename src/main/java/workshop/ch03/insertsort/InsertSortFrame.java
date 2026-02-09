package workshop.ch03.insertsort;

import workshop.ch03.AbstractFrame;
import workshop.ch03.PersonGroup;

import javax.swing.*;

public class InsertSortFrame extends AbstractFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InsertSortFrame::new);
    }

    public InsertSortFrame() {
        super("InsertSort Workshop");
    }

    @Override
    protected PersonGroup newPersonGroup() {
        return new InsertSortPersonGroup(size, order);
    }
}
