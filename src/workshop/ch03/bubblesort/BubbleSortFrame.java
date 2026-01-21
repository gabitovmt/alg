package workshop.ch03.bubblesort;

import workshop.ch03.BarPanel;
import workshop.ch03.GroupBS;
import workshop.ch03.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BubbleSortFrame extends JFrame implements Runnable, ActionListener {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 370;

    private GroupBS personGroup;

    private boolean runFlag;
    private int groupSize = 10;
    private Order order = Order.RANDOM;

    private final Button newButton;
    private final Button sizeButton;
    private final Button drawButton;
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

        drawButton = new Button("Draw");
        drawButton.addActionListener(this);
        btnPanel.add(drawButton);

        runButton = new Button("Run");
        runButton.addActionListener(this);
        btnPanel.add(runButton);

        stepButton = new Button("Step");
        stepButton.addActionListener(this);
        btnPanel.add(stepButton);

        personGroup = new GroupBS(groupSize, order);
        var barPanel = new BarPanel(personGroup);
        add(barPanel, BorderLayout.CENTER);

        runFlag = false;

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("BubbleSort Workshop");
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newButton) {
            runFlag = false;
            order = order == Order.RANDOM ? Order.DESC : Order.RANDOM;
            personGroup = new GroupBS(groupSize, order);
        } else if (e.getSource() == sizeButton) {
            runFlag = false;
            groupSize = groupSize == 10 ? 100 : 10;
            personGroup = new GroupBS(groupSize, order);
        } else if (e.getSource() == drawButton) {
            runFlag = false;
        } else if (e.getSource() == runButton) {
            runFlag = true;
        } else if (e.getSource() == stepButton) {
            runFlag = false;
            personGroup.sortStep();
        }

        repaint();
    }

    public void run() {
//        Thread var1 = Thread.currentThread();
//
//        while(this.runner == var1) {
//            if (this.runFlag && !this.thePersonGroup.getDone()) {
//                this.thePersonGroup.sortStep();
//                ((Component)this).repaint();
//                this.thePersonGroup.setDrawMode(1);
//                int var2 = this.groupSize == 10 ? 250 : 75;
//
//                try {
//                    Thread.sleep((long)var2);
//                } catch (InterruptedException var3) {
//                }
//            }
//        }

    }
}
