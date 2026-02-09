package workshop.ch04;

import workshop.ch04.priorityq.PersonGroupPriorityQ;
import workshop.ch04.priorityq.impl.PersonGroupPriorityQImpl;
import workshop.ch04.swing.AbstractFrame;
import workshop.ch04.swing.PersonGroupPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PriorityQFrame extends AbstractFrame {
    protected final Button newButton = makeButton("New");
    protected final Button insButton = makeButton("Ins");
    protected final Button remButton = makeButton("Rem");
    protected final Button peekButton = makeButton("Peek");

    protected final transient PersonGroupPriorityQ pg;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PriorityQFrame::new);
    }

    public PriorityQFrame() {
        super("PriorityQ Workshop");

        pg = new PersonGroupPriorityQImpl();
        pg.doFill();

        setLayout(new BorderLayout());
        add(makeToolPanel(newButton, insButton, remButton, peekButton), BorderLayout.NORTH);
        add(new PersonGroupPanel(pg), BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var source = e.getSource();

        if (source == newButton) {
            pg.newQueue();
        } else if (source == insButton) {
            pg.insert(getValue());
        } else if (source == remButton) {
            setValue(pg.remove());
        } else if (source == peekButton) {
            setValue(pg.peek());
        }

        repaint();
    }
}
