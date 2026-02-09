package workshop.ch04;

import workshop.ch04.queue.PersonGroupQueue;
import workshop.ch04.queue.impl.PersonGroupQueueImpl;
import workshop.ch04.swing.AbstractFrame;
import workshop.ch04.swing.PersonGroupPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class QueueFrame extends AbstractFrame {
    protected final Button newButton = makeButton("New");
    protected final Button insButton = makeButton("Ins");
    protected final Button remButton = makeButton("Rem");
    protected final Button peekButton = makeButton("Peek");

    protected final transient PersonGroupQueue pg;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QueueFrame::new);
    }

    public QueueFrame() {
        super("Queue Workshop");

        pg = new PersonGroupQueueImpl();
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
