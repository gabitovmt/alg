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

    protected final transient PersonGroupStack pg;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StackFrame::new);
    }

    public StackFrame() {
        super("Stack Workshop");

        pg = new PersonGroupStackImpl();
        pg.doFill();

        setLayout(new BorderLayout());
        add(makeToolPanel(newButton, pushButton, popButton, peekButton), BorderLayout.NORTH);
        add(new PersonGroupPanel(pg), BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var source = e.getSource();
        
        if (source == newButton) {
            pg.newStack();
        } else if (source == pushButton) {
            pg.push(getValue());
        } else if (source == popButton) {
            setValue(pg.pop());
        } else if (source == peekButton) {
            setValue(pg.peek());
        }

        repaint();
    }
}
