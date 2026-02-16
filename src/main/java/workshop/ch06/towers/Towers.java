package workshop.ch06.towers;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;

public class Towers extends Applet implements Runnable, ActionListener, MouseListener {
    private Thread runner;
    private GameGroup theGameGroup;
    private boolean wasClearPressed = false;
    private int GPNumber = -1;
    private boolean isNumber = false;
    private int initialDisks = 4;
    private TextField tf = new TextField("", 4);
    private boolean runFlag = false;
    private Button newButton;
    private Button stepButton;
    private Button runButton;

    public void init() {
        ((Component)this).addMouseListener(this);
        ((Container)this).setLayout(new FlowLayout());
        Panel var1 = new Panel();
        ((Container)this).add(var1);
        ((Container)var1).setLayout(new FlowLayout());
        Panel var2 = new Panel();
        ((Container)var1).add(var2);
        ((Container)var2).setLayout(new FlowLayout(0));
        this.newButton = new Button("New");
        ((Container)var2).add(this.newButton);
        this.newButton.addActionListener(this);
        this.stepButton = new Button("Step");
        ((Container)var2).add(this.stepButton);
        this.stepButton.addActionListener(this);
        this.runButton = new Button("Run");
        ((Container)var2).add(this.runButton);
        this.runButton.addActionListener(this);
        Panel var3 = new Panel();
        ((Container)var1).add(var3);
        ((Container)var3).setLayout(new FlowLayout(2));
        ((Container)var3).add(new Label("Enter number: "));
        ((Container)var3).add(this.tf);
        this.theGameGroup = new GameGroup(this.initialDisks);
        ((Component)this).repaint();
    }

    public void start() {
        if (this.runner == null) {
            this.runner = new Thread(this);
            this.runner.start();
        }

    }

    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }

    }

    public void paint(Graphics var1) {
        this.theGameGroup.draw(var1);
    }

    public void update(Graphics var1) {
        this.paint(var1);
    }

    public void actionPerformed(ActionEvent var1) {
        if (((EventObject)var1).getSource() == this.newButton) {
            this.runFlag = false;
            if (this.wasClearPressed) {
                this.wasClearPressed = false;
                String var2 = this.tf.getText();
                this.isNumber = true;

                try {
                    this.GPNumber = Integer.parseInt(var2);
                } catch (NumberFormatException var4) {
                    this.isNumber = false;
                }

                if (this.isNumber && this.GPNumber <= 10 && this.GPNumber >= 1) {
                    this.theGameGroup = new GameGroup(this.GPNumber);
                } else {
                    this.theGameGroup.creationError();
                }
            } else {
                this.wasClearPressed = true;
                this.theGameGroup.warningNew();
            }
        } else if (((EventObject)var1).getSource() == this.stepButton) {
            this.runFlag = false;
            this.wasClearPressed = false;
            this.theGameGroup.step();
        } else if (((EventObject)var1).getSource() == this.runButton) {
            this.runFlag = true;
            this.wasClearPressed = false;
            this.theGameGroup.setDone(false);
        }

        ((Component)this).repaint();

        try {
            Thread.sleep(10L);
        } catch (InterruptedException var3) {
        }
    }

    public void run() {
        while(true) {
            if (this.runFlag) {
                this.theGameGroup.step();
                ((Component)this).repaint();
                byte var1 = 100;

                try {
                    Thread.sleep((long)var1);
                } catch (InterruptedException var2) {
                }

                if (this.theGameGroup.isDone()) {
                    this.runFlag = false;
                }
            }
        }
    }

    public void mousePressed(MouseEvent var1) {
        int var2 = var1.getX();
        int var3 = var1.getY();
        this.wasClearPressed = false;
        if (var1.getClickCount() == 1) {
            this.theGameGroup.startDrag(var2, var3);
        }

    }

    public void mouseReleased(MouseEvent var1) {
        int var2 = var1.getX();
        int var3 = var1.getY();
        this.theGameGroup.endDrag(var2, var3);
        ((Component)this).repaint();
    }

    public void mouseEntered(MouseEvent var1) {
    }

    public void mouseExited(MouseEvent var1) {
    }

    public void mouseClicked(MouseEvent var1) {
    }
}
