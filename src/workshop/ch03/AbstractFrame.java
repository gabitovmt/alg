package workshop.ch03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class AbstractFrame extends JFrame implements ActionListener {
    protected static final int DEFAULT_WIDTH = 400;
    protected static final int DEFAULT_HEIGHT = 370;
    protected static final int NORMAL_SIZE = 10;
    protected static final int LARGE_SIZE = 100;
    protected static final int NORMAL_DELAY = 250;
    protected static final int FAST_DELAY = 75;

    protected final transient ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    protected transient ScheduledFuture<?> scheduledSort;

    protected final transient PersonGroup personGroup;

    protected Order order = Order.RANDOM;
    protected int size = NORMAL_SIZE;

    protected final Button newButton;
    protected final Button sizeButton;
    protected final Button runButton;
    protected final Button stepButton;

    protected AbstractFrame(String title) {
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

        personGroup = newPersonGroup();
        var barPanel = new BarPanel(personGroup);
        add(barPanel, BorderLayout.CENTER);

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(title);
        setVisible(true);
    }

    protected abstract PersonGroup newPersonGroup();

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

    protected void changeOrder() {
        order = order == Order.RANDOM ? Order.DESC : Order.RANDOM;
    }

    protected void changeSize() {
        size = size == NORMAL_SIZE ? LARGE_SIZE : NORMAL_SIZE;
    }

    protected void stopSort() {
        if (scheduledSort != null) {
            scheduledSort.cancel(false);
            scheduledSort = null;
        }
    }

    protected void runSort() {
        if (personGroup.doneFlag()) {
            stopSort();
            return;
        }

        personGroup.sortStep();
        repaint();
    }
}
