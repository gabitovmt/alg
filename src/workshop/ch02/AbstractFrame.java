package workshop.ch02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public abstract class AbstractFrame extends JFrame implements ActionListener, ItemListener {
    protected static final int DEFAULT_PERSON_CAPACITY = 20;
    protected static final int DEFAULT_PERSON_SIZE = 10;
    protected static final int DEFAULT_WIDTH = 500;
    protected static final int DEFAULT_HEIGHT = 320;

    protected final Button newButton = makeButton("New");
    protected final Button fillButton = makeButton("Fill");
    protected final Button insButton = makeButton("Ins");
    protected final Button findButton = makeButton("Find");
    protected final Button delButton = makeButton("Del");
    protected final TextField tf = new TextField("", 4);

    protected final transient PersonGroup personGroup;

    protected AbstractFrame(String title) {
        personGroup = makePersonGroup(DEFAULT_PERSON_CAPACITY);
        personGroup.doFill(DEFAULT_PERSON_SIZE);

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(title);
    }

    protected abstract PersonGroup makePersonGroup(int maxSize);

    protected void init() {
        setLayout(new BorderLayout());
        add(makeToolPanel(), BorderLayout.NORTH);
        add(new PersonGroupPanel(personGroup), BorderLayout.CENTER);

        setVisible(true);
    }

    protected Button makeButton(String label) {
        var btn = new Button(label);
        btn.addActionListener(this);

        return btn;
    }

    protected Checkbox makeCheckbox(String label, boolean state, CheckboxGroup group) {
        var cb = new Checkbox(label, state, group);
        cb.addItemListener(this);

        return cb;
    }

    protected Panel makeButtonPanel() {
        var panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(newButton);
        panel.add(fillButton);
        panel.add(insButton);
        panel.add(findButton);
        panel.add(delButton);

        return panel;
    }

    protected abstract Panel makeCheckboxGroupPanel();

    protected Panel makeNumPanel() {
        var panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(new Label("Number: "));
        panel.add(tf);

        return panel;
    }

    protected Panel makeToolPanel() {
        var panel = new Panel();
        panel.setLayout(new FlowLayout());
        panel.add(makeButtonPanel());
        panel.add(makeCheckboxGroupPanel());
        panel.add(makeNumPanel());

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var source = e.getSource();
        if (source == newButton) {
            personGroup.newArray(getValue());
        } else if (source == fillButton) {
            personGroup.fill(getValue());
        } else if (source == insButton) {
            personGroup.insert(getValue());
        } else if (source == findButton) {
            personGroup.find(getValue());
        } else if (source == delButton) {
            personGroup.delete(getValue());
        }

        repaint();
    }

    protected Integer getValue() {
        try {
            return Integer.valueOf(tf.getText());
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
