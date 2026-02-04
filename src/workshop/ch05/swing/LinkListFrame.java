package workshop.ch05.swing;

import workshop.ch05.pg.PersonGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class LinkListFrame extends JFrame implements ActionListener, ItemListener {
    private static final int DEFAULT_WIDTH = 440;
    private static final int DEFAULT_HEIGHT = 300;
    private static final int DEFAULT_PERSON_SIZE = 13;

    private final PersonGroup personGroup;

    private final Button newButton = makeButton("New");
    private final Button insButton = makeButton("Ins");
    private final Button findButton = makeButton("Find");
    private final Button delButton = makeButton("Del");

    private final CheckboxGroup cbGroup = new CheckboxGroup();
    private final Checkbox nosort = makeCheckbox("Unsorted", true, cbGroup);
    private final Checkbox sort  = makeCheckbox("Sorted", false, cbGroup);

    private final TextField tf = new TextField("", 4);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LinkListFrame::new);
    }

    public LinkListFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("LinkList Workshop");

        setLayout(new BorderLayout());
        add(makeToolPanel(), BorderLayout.NORTH);

        personGroup = new PersonGroup();
        personGroup.doFill(DEFAULT_PERSON_SIZE);
        repaint();

        setVisible(true);
    }

    private Button makeButton(String label) {
        var btn = new Button(label);
        btn.addActionListener(this);

        return btn;
    }

    private Panel makeButtonPanel() {
        var panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        panel.add(newButton);
        panel.add(insButton);
        panel.add(findButton);
        panel.add(delButton);

        return panel;
    }

    private Checkbox makeCheckbox(String label, boolean state, CheckboxGroup group) {
        var cb = new Checkbox(label, state, group);
        cb.addItemListener(this);

        return cb;
    }

    private Panel makeCheckboxGroupPanel() {
        var panel = new Panel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(nosort);
        panel.add(sort);

        return panel;
    }

    private Panel makeNumPanel() {
        var panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(new Label("Enter number: "));
        panel.add(tf);

        return panel;
    }

    private Panel makeToolPanel() {
        var panel = new Panel();
        panel.setLayout(new FlowLayout());
        panel.add(makeButtonPanel());
        panel.add(makeCheckboxGroupPanel());
        panel.add(makeNumPanel());

        return panel;
    }

    public void paint(Graphics var1) {
        this.personGroup.draw(var1);
    }

    public void update(Graphics var1) {
        this.paint(var1);
    }

    public void actionPerformed(ActionEvent e) {
        var source = e.getSource();
        if (source == newButton) {
            personGroup.newList(getValue());
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

    public void itemStateChanged(ItemEvent e) {
        boolean notSorted = e.getSource() == this.nosort;

        boolean var3 = this.personGroup.getSortStatus();
        boolean var4 = this.personGroup.getChangeStatus();
        this.personGroup.setSortStatus(notSorted);
        if (notSorted && var4 && !var3 || !notSorted && !var4 && var3) {
            this.nosort.setState(true);
            this.sort.setState(false);
        }

        if (!notSorted && var4 && var3 || notSorted && !var4 && !var3) {
            this.nosort.setState(false);
            this.sort.setState(true);
        }
    }
}
