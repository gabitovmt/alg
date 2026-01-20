package workshop.ch03.bubblesort;

import workshop.ch03.GroupBS;
import workshop.ch03.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BubbleSortFrame extends JFrame implements Runnable, ActionListener {
    private static final int DEFAULT_WIDTH = 370;
    private static final int DEFAULT_HEIGHT = 320;

    private Image offscreenImage;
    private Graphics offscreenGraphics;
    private int aWidth;
    private int aHeight;
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
        personGroup = new GroupBS(groupSize, order);
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        newButton = new Button("New");
        newButton.addActionListener(this);
        add(newButton);

        sizeButton = new Button("Size");
        sizeButton.addActionListener(this);
        add(sizeButton);

        drawButton = new Button("Draw");
        drawButton.addActionListener(this);
        add(drawButton);

        runButton = new Button("Run");
        runButton.addActionListener(this);
        add(runButton);

        stepButton = new Button("Step");
        stepButton.addActionListener(this);
        add(stepButton);

        this.aWidth = this.personGroup.getAppletWidth();
        this.aHeight = this.personGroup.getAppletHeight();
        this.offscreenImage = ((Component)this).createImage(this.aWidth, this.aHeight);
//        this.offscreenGraphics = this.offscreenImage.getGraphics();
        this.personGroup.setDrawMode(2);
        this.runFlag = false;

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("BubbleSort Workshop");
        setVisible(true);
    }

    public void paint(Graphics var1) {
        this.personGroup.draw(this.offscreenGraphics);
        var1.drawImage(this.offscreenImage, 0, 0, this);
    }

    public void update(Graphics var1) {
        this.paint(var1);
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
            personGroup.setDrawMode(2);
        } else if (e.getSource() == runButton) {
            runFlag = true;
        } else if (e.getSource() == stepButton) {
            runFlag = false;
            personGroup.sortStep();
            personGroup.setDrawMode(1);
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
