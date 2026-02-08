package alg.ch04.home05;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel {
    private static final int PADDING = 4;
    private static final int QUEUE_HEIGHT = 24;
    private static final int CASHBOX_WIDTH = QUEUE_HEIGHT * 3;
    private static final Dimension PERSON_SIZE = new Dimension(QUEUE_HEIGHT, QUEUE_HEIGHT);
    private static final Font FONT = new Font("SansSerif", Font.BOLD, 14);

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
        g.setFont(FONT);
        fillBackground(g);
        drawQueues(g);
    }

    private void fillBackground(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        var cb = g.getClipBounds();
        g.fillRect((int) cb.getX(), (int) cb.getY(), (int) cb.getWidth(), (int) cb.getHeight());
    }

    private void drawRect(Graphics g, Rectangle r, Color color, String text, boolean center) {
        g.setColor(Color.BLACK);
        g.fillRect(r.x, r.y, r.width, r.height);

        g.setColor(color);
        g.fill3DRect(r.x + 1, r.y + 1, r.width - 1, r.height - 1, true);

        if (text == null || text.isBlank()) {
            return;
        }

        var fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        g.setColor(Color.BLACK);
        int textX = center ? r.x + (r.width - textWidth) / 2 : r.x + PADDING;
        int textY = r.y + fm.getAscent() + (r.height - textHeight) / 2;
        g.drawString(text, textX, textY);
    }

    private void drawCashbox(Graphics g, int idx, Point p) {
        int no = idx + 1;
        var r = new Rectangle(p, new Dimension(CASHBOX_WIDTH, QUEUE_HEIGHT));
        drawRect(g, r, Color.LIGHT_GRAY, "Касса " + no, false);
    }

    private void drawPerson(Graphics g, Point p, Person person) {
        drawRect(g, new Rectangle(p, PERSON_SIZE), person.color(), Integer.toString(person.id()), true);
    }

    private void drawPeople(Graphics g, Point p, Person[] people) {
        for (var idx = 0; idx < people.length; idx++) {
            var personPoint = new Point(p.x + idx * PERSON_SIZE.width, p.y);
            drawPerson(g, personPoint, people[idx]);
        }
    }

    private void drawQueue(Graphics g, Point p, int idx) {
        drawCashbox(g, idx, p);

        Person[] people = shop.personQueue(idx).toArray();
        var peoplePoint = new Point(p.x + CASHBOX_WIDTH, p.y);
        drawPeople(g, peoplePoint, people);
    }

    private void drawQueues(Graphics g) {
        for (int idx = 0; idx < shop.cashboxCount(); idx++) {
            var p = new Point(0, idx * QUEUE_HEIGHT);
            drawQueue(g, p, idx);
        }
    }
}
