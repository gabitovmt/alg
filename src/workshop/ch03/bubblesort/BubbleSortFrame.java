package workshop.ch03.bubblesort;

import workshop.ch03.BarPanel;
import workshop.ch03.Order;
import workshop.ch03.PersonGroup;
import workshop.ch03.PersonGroupImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class BubbleSortFrame extends JFrame implements ActionListener {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 370;
    private static final int NORMAL_SIZE = 10;
    private static final int LARGE_SIZE = 100;
    private static final int NORMAL_DELAY = 250;
    private static final int FAST_DELAY = 75;

    private final transient ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private transient ScheduledFuture<?> scheduledSort;

    private final transient PersonGroup personGroup;

    private Order order = Order.RANDOM;
    private int size = NORMAL_SIZE;

    private final Button newButton;
    private final Button sizeButton;
    private final Button runButton;
    private final Button stepButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BubbleSortFrame::new);
    }

    public BubbleSortFrame() {
        setLayout(new BorderLayout());

        var btnPanel = new Panel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(btnPanel, BorderLayout.NORTH);

        newButton = new Button("New");
        newButton.addActionListener(this);
        btnPanel.add(newButton);

        sizeButton = new Button("Size");
        sizeButton.addActionListener(this);
        btnPanel.add(sizeButton);

        runButton = new Button("Run");
        runButton.addActionListener(this);
        btnPanel.add(runButton);

        stepButton = new Button("Step");
        stepButton.addActionListener(this);
        btnPanel.add(stepButton);

        personGroup = new PersonGroupImpl(size, order);
        var barPanel = new BarPanel(personGroup);
        add(barPanel, BorderLayout.CENTER);

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("BubbleSort Workshop");
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        stopSort();

        if (e.getSource() == newButton) {
            changeOrder();
            personGroup.createPeople(size, order);
        } else if (e.getSource() == sizeButton) {
            changeSize();
            personGroup.createPeople(size, order);
        } else if (e.getSource() == runButton) {
            int delay = personGroup.length() == NORMAL_SIZE ? NORMAL_DELAY : FAST_DELAY;
            scheduledSort = executor.scheduleAtFixedRate(this::runSort, delay, delay, TimeUnit.MILLISECONDS);
        } else if (e.getSource() == stepButton) {
            personGroup.sortStep();
        }

        repaint();
    }

    private void changeOrder() {
        order = order == Order.RANDOM ? Order.DESC : Order.RANDOM;
    }

    private void changeSize() {
        size = size == NORMAL_SIZE ? LARGE_SIZE : NORMAL_SIZE;
    }

    private void stopSort() {
        if (scheduledSort != null) {
            scheduledSort.cancel(false);
            scheduledSort = null;
        }
    }

    private void runSort() {
        if (personGroup.doneFlag()) {
            stopSort();
            return;
        }

        personGroup.sortStep();
        repaint();
    }
}
