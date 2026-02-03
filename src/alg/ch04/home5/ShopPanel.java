package alg.ch04.home5;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel {
    public static final int PADDING = 4;
    public static final int QUEUE_HEIGHT = 24;
    public static final int CASHBOX_WIDTH = QUEUE_HEIGHT * 3;
    public static final int PERSON_WIDTH = QUEUE_HEIGHT;

    private final transient Shop shop;

    public ShopPanel(Shop shop) {
        this.shop = shop;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        fillBackground(g);
        drawQueues(g);
    }

    private void fillBackground(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        var cb = g.getClipBounds();
        g.fillRect((int) cb.getX(), (int) cb.getY(), (int) cb.getWidth(), (int) cb.getHeight());
    }

    private void drawRect(Graphics g, int x, int y, int width, int height, Color color, String text, boolean center) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);

        g.setColor(color);
        g.fill3DRect(x + 1, y + 1, width - 1, height - 1, true);

        if (text == null || text.isBlank()) {
            return;
        }

        var fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        g.setColor(Color.BLACK);
        int textX = center ? x + (width - textWidth) / 2 : x + PADDING;
        int textY = y + fm.getAscent() + (height - textHeight) / 2;
        g.drawString(text, textX, textY);
    }

    private void drawCashbox(Graphics g, int idx) {
        int no = idx + 1;
        drawRect(g, 0, QUEUE_HEIGHT * idx, CASHBOX_WIDTH, QUEUE_HEIGHT, Color.LIGHT_GRAY, "Касса " + no, false);
    }

    private void drawPerson(Graphics g, int queueIdx, int personIdx, Person person) {

    }

    private void drawQueue(Graphics g, int idx) {
        drawCashbox(g, idx);
    }

    private void drawQueues(Graphics g) {
        for (int i = 0; i < shop.cashboxCount(); i++) {
            drawQueue(g, i);
        }
    }
}
