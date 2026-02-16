package workshop.ch06.towers;

import workshop.ch06.towers.gg.GameGroup;
import workshop.ch06.towers.swing.GamePanel;
import workshop.ch06.towers.swing.shape.TowersShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TowersFrame extends JFrame implements Runnable, ActionListener {
    private static final int DEFAULT_WIDTH = 460;
    private static final int DEFAULT_HEIGHT = 350;
    private static final int DEFAULT_DISKS = 4;

    private transient GameGroup game;

    private final Button newButton = makeButton("New");
    private final Button stepButton = makeButton("Step");
    private final Button runButton = makeButton("Run");

    private final TextField tf = new TextField("", 4);

    private Thread runner;
    private boolean wasClearPressed = false;
    private int GPNumber = -1;
    private boolean isNumber = false;
    private boolean runFlag = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TowersFrame::new);
    }

    public TowersFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("LinkList Workshop");

        game = new GameGroup(DEFAULT_DISKS);

        setLayout(new BorderLayout());
        add(makeToolPanel(), BorderLayout.NORTH);
        add(new GamePanel(game), BorderLayout.CENTER);
        addMouseListener(new MouseListenerImpl());

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
        panel.add(stepButton);
        panel.add(runButton);

        return panel;
    }

    private Panel makeNumPanel() {
        var panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(new Label("Enter number: "));
        panel.add(tf);

        return panel;
    }

    private Panel makeToolPanel() {
        var panel = new Panel();
        panel.setLayout(new FlowLayout());
        panel.add(makeButtonPanel());
        panel.add(makeNumPanel());

        return panel;
    }

    public void start() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }

    }

    public void stop() {
        if (runner != null) {
            runner.stop();
            runner = null;
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newButton) {
            runFlag = false;
            if (wasClearPressed) {
                wasClearPressed = false;
                String var2 = tf.getText();
                isNumber = true;

                try {
                    GPNumber = Integer.parseInt(var2);
                } catch (NumberFormatException var4) {
                    isNumber = false;
                }

                if (isNumber && GPNumber <= 10 && GPNumber >= 1) {
                    game = new GameGroup(GPNumber);
                } else {
                    game.creationError();
                }
            } else {
                wasClearPressed = true;
                game.warningNew();
            }
        }

        if (e.getSource() == stepButton) {
            runFlag = false;
            wasClearPressed = false;
            game.step();
        }

        if (e.getSource() == runButton) {
            runFlag = true;
            wasClearPressed = false;
            game.setDone(false);
        }

        repaint();
    }

    public void run() {
        while (true) {
            if (runFlag) {
                game.step();
                ((Component) this).repaint();
                byte var1 = 100;

                try {
                    Thread.sleep((long) var1);
                } catch (InterruptedException var2) {
                }

                if (game.isDone()) {
                    runFlag = false;
                }
            }
        }
    }

    private class MouseListenerImpl extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            wasClearPressed = false;
            if (e.getClickCount() == 1) {
                game.startDrag(e.getX(), e.getY());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            game.endDrag(e.getX(), e.getY());
            repaint();
        }
    }
}
