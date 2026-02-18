package workshop.ch06.towers;

import workshop.ch06.towers.gg.GameImpl;
import workshop.ch06.towers.swing.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TowersFrame extends JFrame implements ActionListener {
    private static final int DEFAULT_WIDTH = 460;
    private static final int DEFAULT_HEIGHT = 350;
    private static final int DEFAULT_DISKS = 4;

    private final transient ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private transient ScheduledFuture<?> stepTask;

    private final transient GameImpl game;

    private final Button newButton = makeButton("New");
    private final Button stepButton = makeButton("Step");
    private final Button runButton = makeButton("Run");

    private final TextField tf = new TextField("", 4);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TowersFrame::new);
    }

    public TowersFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("LinkList Workshop");

        game = new GameImpl(DEFAULT_DISKS);

        setLayout(new BorderLayout());
        add(makeToolPanel(), BorderLayout.NORTH);
        var gamePanel = new GamePanel(game);
        add(gamePanel, BorderLayout.CENTER);
        gamePanel.addMouseListener(new MouseListenerImpl());

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newButton) {
            game.newGame(getValue());
        }

        if (e.getSource() == stepButton) {
            game.step();
        }

        if (e.getSource() == runButton) {
            if (stepTask == null) {
                runStepTask();
            } else {
                stopStepTask();
            }
        }

        repaint();
    }

    private void runStepTask() {
        if (stepTask == null) {
            int delay = 100;
            stepTask = executor.scheduleAtFixedRate(this::stepTask, delay, delay, TimeUnit.MILLISECONDS);
        }
    }

    private void stopStepTask() {
        if (stepTask != null) {
            stepTask.cancel(false);
            stepTask = null;
        }
    }

    private void stepTask() {
        if (game.isDone() || !game.canRunning()) {
            stopStepTask();
            return;
        }

        game.step();
        repaint();
    }

    private Integer getValue() {
        try {
            return Integer.valueOf(tf.getText());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private class MouseListenerImpl extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
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
