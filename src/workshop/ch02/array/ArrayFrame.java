package workshop.ch02.array;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ArrayFrame extends JFrame implements ActionListener, ItemListener {
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

        personGroup = new PersonGroup(20);
        personGroup.doFill(10);
        var personGroupPanel = new PersonGroupPanel(personGroup);
        add(personGroupPanel, BorderLayout.CENTER);

        setSize(440, 320);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Integer value;

        try {
            value = Integer.valueOf(tf.getText());
        } catch (NumberFormatException ex) {
            value = null;
        }

        var source = e.getSource();
        if (source == newButton) {
            personGroup.newArray(value);
        } else if (source == fillButton) {
            personGroup.fill(value);
        } else if (source == insButton) {
            personGroup.insert(value);
        } else if (source == findButton) {
            personGroup.find(value);
        } else if (source == delButton) {
            personGroup.delete(value);
        }

        repaint();

        try {
            Thread.sleep(10L);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        boolean isDups = e.getSource() == dups;
        personGroup.setDupsStatus(isDups);
        dups.setState(personGroup.getDupsStatus());
        noDups.setState(!personGroup.getDupsStatus());
    }
}
