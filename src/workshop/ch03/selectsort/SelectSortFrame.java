package workshop.ch03.selectsort;

import workshop.ch03.AbstractFrame;
import workshop.ch03.PersonGroup;

import javax.swing.*;

public class SelectSortFrame extends AbstractFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SelectSortFrame::new);
    }

    public SelectSortFrame() {
        super("SelectSort Workshop");
    }

    @Override
    protected PersonGroup newPersonGroup() {
        return new SelectSortPersonGroup(size, order);
    }
}
