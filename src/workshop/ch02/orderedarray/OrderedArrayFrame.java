package workshop.ch02.orderedarray;

import workshop.ch02.AbstractFrame;
import workshop.ch02.PersonGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class OrderedArrayFrame extends AbstractFrame {
    protected final CheckboxGroup cbGroup = new CheckboxGroup();
    protected final Checkbox lin = makeCheckbox("Linear", true, cbGroup);
    protected final Checkbox bin = makeCheckbox("Binary", false, cbGroup);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OrderedArrayFrame::new);
    }

    public OrderedArrayFrame() {
        super("Ordered Workshop");
        init();
    }

    @Override
    protected PersonGroup makePersonGroup(int maxSize) {
        return new PersonGroupImpl(maxSize);
    }

    @Override
    protected Panel makeCheckboxGroupPanel() {
        var panel = new Panel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(lin);
        panel.add(bin);

        return panel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        boolean isLinear = e.getSource() == lin;
        personGroup.setLinearSearch(isLinear);
        lin.setState(personGroup.isLinearSearch());
        bin.setState(!personGroup.isLinearSearch());
    }
}
