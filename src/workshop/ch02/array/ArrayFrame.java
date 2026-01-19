package workshop.ch02.array;

import workshop.ch02.PersonGroup;
import workshop.ch02.PersonGroupPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ArrayFrame extends JFrame implements ActionListener, ItemListener {
    private static final int DEFAULT_PERSON_CAPACITY = 20;
    private static final int DEFAULT_PERSON_SIZE = 10;
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 320;

    private final transient PersonGroup personGroup;

    private final TextField tf;
    private final Checkbox dups;
    private final Checkbox noDups;
    private final Button newButton;
    private final Button fillButton;
    private final Button insButton;
    private final Button findButton;
    private final Button delButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ArrayFrame::new);
    }

    public ArrayFrame() {
        setLayout(new BorderLayout());

        var toolPanel = new Panel();
        toolPanel.setLayout(new FlowLayout());
        add(toolPanel, BorderLayout.NORTH);

        var btnPanel = new Panel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolPanel.add(btnPanel);

        newButton = new Button("New");
        newButton.addActionListener(this);
        btnPanel.add(newButton);

        fillButton = new Button("Fill");
        fillButton.addActionListener(this);
        btnPanel.add(fillButton);

        insButton = new Button("Ins");
        insButton.addActionListener(this);
        btnPanel.add(insButton);

        findButton = new Button("Find");
        findButton.addActionListener(this);
        btnPanel.add(findButton);

        delButton = new Button("Del");
        delButton.addActionListener(this);
        btnPanel.add(delButton);

        var chPanel = new Panel();
        chPanel.setLayout(new GridLayout(2, 1));
        toolPanel.add(chPanel);

        var chGroup = new CheckboxGroup();

        dups = new Checkbox("Dups OK", false, chGroup);
        dups.addItemListener(this);
        chPanel.add(dups);

        noDups = new Checkbox("No dups", true, chGroup);
        noDups.addItemListener(this);
        chPanel.add(noDups);

        var numPanel = new Panel();
        numPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolPanel.add(numPanel);

        numPanel.add(new Label("Number: "));

        tf = new TextField("", 4);
        numPanel.add(tf);

        personGroup = new PersonGroupImpl(DEFAULT_PERSON_CAPACITY);
        personGroup.doFill(DEFAULT_PERSON_SIZE);
        var personGroupPanel = new PersonGroupPanel(personGroup);
        add(personGroupPanel, BorderLayout.CENTER);

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Array Workshop");
        setVisible(true);
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

    private Integer getValue() {
        try {
            return Integer.valueOf(tf.getText());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        boolean hasDuplicate = e.getSource() == dups;
        personGroup.setHasDuplicate(hasDuplicate);
        dups.setState(personGroup.hasDuplicate());
        noDups.setState(!personGroup.hasDuplicate());
    }
}
