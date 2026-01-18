package workshop.ch02.orderedarray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

public class OrderedArrayFrame extends JFrame implements ActionListener, ItemListener {
    private static final int DEFAULT_PERSON_CAPACITY = 20;
    private static final int DEFAULT_PERSON_SIZE = 10;
    private static final int DEFAULT_WIDTH = 440;
    private static final int DEFAULT_HEIGHT = 320;

    private final transient PersonGroupImpl personGroup;

    private final TextField tf;
    private final Checkbox lin;
    private final Checkbox bin;
    private final Button newButton;
    private final Button fillButton;
    private final Button insButton;
    private final Button findButton;
    private final Button delButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OrderedArrayFrame::new);
    }

    public OrderedArrayFrame() {
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

        lin = new Checkbox("Linear", true, chGroup);
        lin.addItemListener(this);
        chPanel.add(lin);

        bin = new Checkbox("Binary", false, chGroup);
        bin.addItemListener(this);
        chPanel.add(bin);

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
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        int value = Objects.requireNonNullElse(getValue(), 0);
        boolean isNumber = value != 0;

        if (e.getSource() == newButton) {
            personGroup.newArray(isNumber, value);
        } else if (e.getSource() == fillButton) {
            personGroup.fill(isNumber, value);
        } else if (e.getSource() == insButton) {
            personGroup.insert(isNumber, value);
        } else if (e.getSource() == findButton) {
            personGroup.find(isNumber, value);
        } else if (e.getSource() == delButton) {
            personGroup.delete(isNumber, value);
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

    public void itemStateChanged(ItemEvent e) {
        boolean isLinear = e.getSource() == lin;
        boolean var3 = personGroup.getSearchType();
        boolean var4 = personGroup.getChangeStatus();
        personGroup.setSearchType(isLinear);
        if (isLinear && var4 && !var3 || !isLinear && !var4 && var3) {
            lin.setState(true);
            bin.setState(false);
        }

        if (!isLinear && var4 && var3 || isLinear && !var4 && !var3) {
            lin.setState(false);
            bin.setState(true);
        }
    }
}
