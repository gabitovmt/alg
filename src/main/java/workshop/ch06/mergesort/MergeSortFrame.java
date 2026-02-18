package workshop.ch06.mergesort;

import workshop.ch06.mergesort.pg.PersonGroup;
import workshop.ch06.mergesort.pg.PersonGroupImpl;
import workshop.ch06.mergesort.swing.BarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MergeSortFrame extends JFrame implements ActionListener {
    private static final int DEFAULT_WIDTH = 360;
    private static final int DEFAULT_HEIGHT = 360;

    private final PersonGroup pg;

    private final Button newButton = makeButton("New");
    private final Button orderButton = makeButton("Order");
    private final Button sizeButton = makeButton("Size");
    private final Button stepButton = makeButton("Step");
    private final Button runButton = makeButton("Run");

    private Image offscreenImage;
    private Graphics offscreenGraphics;
    private int aWidth;
    private int aHeight;
    private Thread runner;
    private int groupSize = 12;
//    private personGroup thePersonGroup;
    private boolean runFlag;
    private int order = 1;

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
            //
        } else if (e.getSource() == runButton) {
            //
        }

        repaint();
    }

/*    public void actionPerformed(ActionEvent var1) {
        if (((EventObject)var1).getSource() == this.newButton) {
            this.runFlag = false;
            this.order = this.order == 1 ? 2 : 1;
            this.thePersonGroup = new personGroup(this.groupSize, this.order);
        } else if (((EventObject)var1).getSource() == this.sizeButton) {
            this.runFlag = false;
            this.groupSize = this.groupSize == 12 ? 100 : 12;
            this.thePersonGroup = new personGroup(this.groupSize, this.order);
        } else if (((EventObject)var1).getSource() == this.drawButton) {
            this.thePersonGroup.setDrawMode(2);
        } else if (((EventObject)var1).getSource() == this.runButton) {
            this.thePersonGroup.setDrawMode(1);
            this.runFlag = true;
        } else if (((EventObject)var1).getSource() == this.stepButton && !this.thePersonGroup.getDone()) {
            this.thePersonGroup.setDrawMode(1);
            this.runFlag = false;
            this.thePersonGroup.sortStep();
            this.thePersonGroup.setDrawMode(1);
        }

        ((Component)this).repaint();
    }*/

/*    public void start() {
        if (this.runner == null) {
            this.runner = new Thread(this);
            this.runner.start();
        }

    }

    public void stop() {
        this.runner = null;
    }

    public void run() {
        Thread var1 = Thread.currentThread();

        while(this.runner == var1) {
            if (this.runFlag && !this.thePersonGroup.getDone()) {
                this.thePersonGroup.sortStep();
                ((Component)this).repaint();
                this.thePersonGroup.setDrawMode(1);
                int var2 = this.groupSize == 12 ? 250 : 75;

                try {
                    Thread.sleep((long)var2);
                } catch (InterruptedException var3) {
                }
            }
        }

    }*/
}
