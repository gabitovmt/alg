package alg.ch04.home5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShopDemo extends JFrame implements ActionListener {
    private static final int DEFAULT_CASHBOX_COUNT = 5;
    private static final int DEFAULT_QUEUE_MAX_SIZE = 50;
    private final transient Shop shop;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ShopDemo::new);
    }

    public ShopDemo() {
        shop = new Shop(DEFAULT_CASHBOX_COUNT, DEFAULT_QUEUE_MAX_SIZE);

        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Shop Demo");

        setLayout(new BorderLayout());

        var newPersonButton = new Button("New Person");
        newPersonButton.addActionListener(this);
        add(newPersonButton, BorderLayout.NORTH);

        add(new ShopPanel(shop), BorderLayout.CENTER);

        setVisible(true);

        Executors.newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(this::workCashboxes, 1, 1, TimeUnit.SECONDS);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        shop.newPerson();
        repaint();
    }

    private void workCashboxes() {
        shop.workCashboxes();
        repaint();
    }
}
