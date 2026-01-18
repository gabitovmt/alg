package workshop.ch02.orderedarray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.EventObject;

public class OrderedArrayFrame extends JFrame implements ActionListener, ItemListener {
    private PersonGroup thePersonGroup;
    private int GPNumber = -1;
    private boolean isNumber = false;
    private TextField tf = new TextField("", 4);
    private Checkbox lin;
    private Checkbox bin;
    private Button newButton;
    private Button fillButton;
    private Button insButton;
    private Button findButton;
    private Button delButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OrderedArrayFrame::new);
    }

    public OrderedArrayFrame() {
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
        this.fillButton = new Button("Fill");
        ((Container)var2).add(this.fillButton);
        this.fillButton.addActionListener(this);
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
        this.lin = new Checkbox("Linear", var4, true);
        this.lin.addItemListener(this);
        this.bin = new Checkbox("Binary", var4, false);
        this.bin.addItemListener(this);
        ((Container)var3).add(this.lin);
        ((Container)var3).add(this.bin);
        Panel var5 = new Panel();
        ((Container)var1).add(var5);
        ((Container)var5).setLayout(new FlowLayout(2));
        ((Container)var5).add(new Label("Number: "));
        ((Container)var5).add(this.tf);
        this.thePersonGroup = new PersonGroup(20);
        this.thePersonGroup.doFill(10);
        this.thePersonGroup.setDrawMode(2);
        ((Component)this).repaint();

        setSize(440, 320);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
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
            this.thePersonGroup.newArray(this.isNumber, this.GPNumber);
        } else if (((EventObject)var1).getSource() == this.fillButton) {
            this.thePersonGroup.fill(this.isNumber, this.GPNumber);
        } else if (((EventObject)var1).getSource() == this.insButton) {
            this.thePersonGroup.insert(this.isNumber, this.GPNumber);
        } else if (((EventObject)var1).getSource() == this.findButton) {
            this.thePersonGroup.find(this.isNumber, this.GPNumber);
        } else if (((EventObject)var1).getSource() == this.delButton) {
            this.thePersonGroup.delete(this.isNumber, this.GPNumber);
        }

        ((Component)this).repaint();
    }

    public void itemStateChanged(ItemEvent var1) {
        boolean var2 = ((EventObject)var1).getSource() == this.lin;
        boolean var3 = this.thePersonGroup.getSearchType();
        boolean var4 = this.thePersonGroup.getChangeStatus();
        this.thePersonGroup.setSearchType(var2);
        if (var2 && var4 && !var3 || !var2 && !var4 && var3) {
            this.lin.setState(true);
            this.bin.setState(false);
        }

        if (!var2 && var4 && var3 || var2 && !var4 && !var3) {
            this.lin.setState(false);
            this.bin.setState(true);
        }

    }
}
