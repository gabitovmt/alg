package workshop.ch04.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class AbstractFrame extends JFrame implements ActionListener {
    protected static final int DEFAULT_WIDTH = 440;
    protected static final int DEFAULT_HEIGHT = 300;

    protected final TextField tf = new TextField("", 4);

    protected AbstractFrame(String title) {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(title);
    }

    protected Button makeButton(String label) {
        var btn = new Button(label);
        btn.addActionListener(this);

        return btn;
    }

    protected Panel makeButtonPanel(Button... buttons) {
        var panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        for (var button : buttons) {
            panel.add(button);
        }

        return panel;
    }

    protected Panel makeNumPanel() {
        var panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(new Label("Number: "));
        panel.add(tf);

        return panel;
    }

    protected Panel makeToolPanel(Button... buttons) {
        var panel = new Panel();
        panel.setLayout(new FlowLayout());
        panel.add(makeButtonPanel(buttons));
        panel.add(makeNumPanel());

        return panel;
    }

    protected Integer getValue() {
        try {
            return Integer.valueOf(tf.getText());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    protected void setValue(Integer value) {
        tf.setText(value != null ? value.toString() : "");
    }
}
