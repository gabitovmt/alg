package workshop.ch02.array;

import workshop.ch02.AbstractFrame;
import workshop.ch02.PersonGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class ArrayFrame extends AbstractFrame {
    protected final CheckboxGroup cbGroup = new CheckboxGroup();
    protected final Checkbox dups = makeCheckbox("Dups OK", false, cbGroup);
    protected final Checkbox noDups = makeCheckbox("No dups", true, cbGroup);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ArrayFrame::new);
    }

    public ArrayFrame() {
        super("Array Workshop");
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
        panel.add(dups);
        panel.add(noDups);

        return panel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        boolean hasDuplicate = e.getSource() == dups;
        personGroup.setHasDuplicate(hasDuplicate);
        dups.setState(personGroup.hasDuplicate());
        noDups.setState(!personGroup.hasDuplicate());
    }
}
