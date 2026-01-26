package workshop.ch04.stack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StackFrame extends JFrame implements ActionListener {
    protected static final int DEFAULT_WIDTH = 440;
    protected static final int DEFAULT_HEIGHT = 300;

    protected final Button newButton = makeButton("New");
    protected final Button pushButton = makeButton("Push");
    protected final Button popButton = makeButton("Pop");
    protected final Button peekButton = makeButton("Peek");
    protected final TextField tf = new TextField("", 4);

    private final transient personGroup personGroup;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StackFrame::new);
    }

    public StackFrame() {
        personGroup = new personGroup();
        personGroup.doFill();

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stack Workshop");

        init();
    }

    public void init() {
        setLayout(new FlowLayout());
        add(makeToolPanel());

        repaint();

        setVisible(true);
    }

    protected Button makeButton(String label) {
        var btn = new Button(label);
        btn.addActionListener(this);

        return btn;
    }

    protected Panel makeButtonPanel() {
        var panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(newButton);
        panel.add(pushButton);
        panel.add(popButton);
        panel.add(peekButton);

        return panel;
    }

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
        
        var value = getValue();
        boolean isNumber = value != null;
        int num = value != null ? value : 0;

        if (source == newButton) {
            personGroup.newStack();
        } else if (source == pushButton) {
            personGroup.push(isNumber, num);
        } else if (source == popButton) {
            tf.setText(personGroup.pop());
        } else if (source == peekButton) {
            tf.setText(personGroup.peek());
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
