package workshop.ch04;

import workshop.ch04.stack.PersonGroupStack;
import workshop.ch04.stack.impl.PersonGroupStackImpl;
import workshop.ch04.swing.PersonGroupPanel;
import workshop.ch04.swing.AbstractFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StackFrame extends AbstractFrame {
    protected final Button newButton = makeButton("New");
    protected final Button pushButton = makeButton("Push");
    protected final Button popButton = makeButton("Pop");
    protected final Button peekButton = makeButton("Peek");

    private final transient PersonGroupStack personGroup;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StackFrame::new);
    }

    public StackFrame() {
        super("Stack Workshop");

        personGroup = new PersonGroupStackImpl();
        personGroup.doFill();

        setLayout(new BorderLayout());
        add(makeToolPanel(newButton, pushButton, popButton, peekButton), BorderLayout.NORTH);
        add(new PersonGroupPanel(personGroup), BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var source = e.getSource();
        
        if (source == newButton) {
            personGroup.newStack();
        } else if (source == pushButton) {
            personGroup.push(getValue());
        } else if (source == popButton) {
            setValue(personGroup.pop());
        } else if (source == peekButton) {
            setValue(personGroup.peek());
        }

        repaint();
    }
}
