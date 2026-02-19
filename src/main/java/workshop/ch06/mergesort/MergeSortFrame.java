package workshop.ch06.mergesort;

import workshop.ch06.mergesort.pg.PersonGroup;
import workshop.ch06.mergesort.pg.PersonGroupImpl;
import workshop.ch06.mergesort.pg.Size;
import workshop.ch06.mergesort.swing.BarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MergeSortFrame extends JFrame implements ActionListener {
    private static final int DEFAULT_WIDTH = 360;
    private static final int DEFAULT_HEIGHT = 400;

    private final transient PersonGroup pg;

    private final Button newButton = makeButton("New");
    private final Button orderButton = makeButton("Order");
    private final Button sizeButton = makeButton("Size");
    private final Button stepButton = makeButton("Step");
    private final Button runButton = makeButton("Run");

    private final transient ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private transient ScheduledFuture<?> sortingTask;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MergeSortFrame::new);
    }

    public MergeSortFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("MergeSort Workshop");

        pg = new PersonGroupImpl();

        setLayout(new BorderLayout());
        add(makeButtonPanel(), BorderLayout.NORTH);
        add(new BarPanel(pg), BorderLayout.CENTER);

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
        panel.add(orderButton);
        panel.add(sizeButton);
        panel.add(stepButton);
        panel.add(runButton);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newButton) {
            pg.reset();
        } else if (e.getSource() == orderButton) {
            pg.switchOrder();
        } else if (e.getSource() == sizeButton) {
            pg.switchSize();
        } else if (e.getSource() == stepButton) {
            pg.sortStep();
        } else if (e.getSource() == runButton) {
            startSorting();
        }

        if (e.getSource() != runButton) {
            stopSorting();
        }

        repaint();
    }

    private void startSorting() {
        if (sortingTask == null && !pg.isDone()) {
            int delay = pg.size() == Size.NORMAL ? 250 : 75;
            sortingTask = executor.scheduleAtFixedRate(this::sortStep, delay, delay, TimeUnit.MILLISECONDS);
        }
    }

    private void stopSorting() {
        if (sortingTask != null) {
            sortingTask.cancel(false);
            sortingTask = null;
        }
    }

    private void sortStep() {
        if (pg.isDone()) {
            stopSorting();
            return;
        }

        pg.sortStep();
        repaint();
    }
}
