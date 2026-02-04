package workshop.ch05;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.EventObject;

public class LinkList extends JFrame implements Runnable, ActionListener, ItemListener {
    private Thread runner;
    private PersonGroup thePersonGroup;
    private int GPNumber = -1;
    private boolean isNumber = false;
    private TextField tf = new TextField("", 4);
    private Checkbox nosort;
    private Checkbox sort;
    private Button newButton;
    private Button insButton;
    private Button findButton;
    private Button delButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LinkList::new);
    }

    public LinkList() {
        init();
        setVisible(true);
    }

    public void init() {
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
        this.insButton = new Button("Ins");
        ((Container)var2).add(this.insButton);
        this.insButton.addActionListener(this);
        this.findButton = new Button("Find");
        ((Container)var2).add(this.findButton);
        this.findButton.addActionListener(this);
        this.delButton = new Button("Del");
        ((Container)var2).add(this.delButton);
        this.delButton.addActionListener(this);
        Panel var3 = new Panel();
        ((Container)var1).add(var3);
        ((Container)var3).setLayout(new GridLayout(2, 1));
        CheckboxGroup var4 = new CheckboxGroup();
        this.nosort = new Checkbox("Unsorted", true, var4);
        ((Container)var3).add(this.nosort);
        this.nosort.addItemListener(this);
        this.sort = new Checkbox("Sorted", false, var4);
        ((Container)var3).add(this.sort);
        this.sort.addItemListener(this);
        Panel var5 = new Panel();
        ((Container)var1).add(var5);
        ((Container)var5).setLayout(new FlowLayout(2));
        ((Container)var5).add(new Label("Enter number: "));
        ((Container)var5).add(this.tf);
        this.thePersonGroup = new PersonGroup();
        this.thePersonGroup.doFill(13);
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
        this.thePersonGroup.draw(var1);
    }

    public void update(Graphics var1) {
        this.paint(var1);
    }

    public void actionPerformed(ActionEvent var1) {
        this.isNumber = true;
        String var2 = this.tf.getText();

        try {
            this.GPNumber = Integer.parseInt(var2);
        } catch (NumberFormatException var4) {
            this.GPNumber = 0;
            this.isNumber = false;
        }

        if (((EventObject)var1).getSource() == this.newButton) {
            this.thePersonGroup.newList(this.isNumber, this.GPNumber);
        } else if (((EventObject)var1).getSource() == this.insButton) {
            this.thePersonGroup.insert(this.isNumber, this.GPNumber);
        } else if (((EventObject)var1).getSource() == this.findButton) {
            this.thePersonGroup.find(this.isNumber, this.GPNumber);
        } else if (((EventObject)var1).getSource() == this.delButton) {
            this.thePersonGroup.delete(this.isNumber, this.GPNumber);
        }

        ((Component)this).repaint();

        try {
            Thread.sleep(10L);
        } catch (InterruptedException var3) {
        }
    }

    public void itemStateChanged(ItemEvent var1) {
        boolean var2 = ((EventObject)var1).getSource() == this.nosort;
        boolean var3 = this.thePersonGroup.getSortStatus();
        boolean var4 = this.thePersonGroup.getChangeStatus();
        this.thePersonGroup.setSortStatus(var2);
        if (var2 && var4 && !var3 || !var2 && !var4 && var3) {
            this.nosort.setState(true);
            this.sort.setState(false);
        }

        if (!var2 && var4 && var3 || var2 && !var4 && !var3) {
            this.nosort.setState(false);
            this.sort.setState(true);
        }

    }

    public void run() {
        // $FF: Couldn't be decompiled
    }
}
