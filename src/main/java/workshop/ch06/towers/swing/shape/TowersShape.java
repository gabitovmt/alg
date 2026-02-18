package workshop.ch06.towers.swing.shape;

import workshop.ch06.towers.gg.Game;
import workshop.ch06.towers.gg.Tower;

import java.awt.*;

public record TowersShape(Game game) implements Shape {
    private static final int NOTE_X = 16;
    private static final int NOTE_Y = 24;
    private static final int TOWER_HEIGHT = 220;
    private static final int TOWER_WIDTH = 140;
    private static final int TOWER_OFFSET = 10;
    private static final int TOWER_TOP = 32;
    private static final Font FONT = new Font("SansSerif", Font.BOLD, 14);

    @Override
    public void draw(Graphics g) {
        fillBackground(g);
        drawNote(g);
        drawTowers(g);
        drawRect(g);
    }

    public Tower getTowerByCoords(int x, int y) {
        Tower[] towers = game.towers();
        for (int i = 0; i < towers.length; i++) {
            TowerShape towerShape = towerShape(towers[i], i);
            Rectangle r = towerShape.getBounds();
            if (r.contains(x, y)) {
                return towers[i];
            }
        }

        return null;
    }

    private void fillBackground(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        var cb = g.getClipBounds();
        g.fillRect((int) cb.getX(), (int) cb.getY(), (int) cb.getWidth(), (int) cb.getHeight());
    }

    private void drawRect(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(TOWER_OFFSET, TOWER_TOP, 3 * TOWER_WIDTH, TOWER_HEIGHT);
    }

    private void drawNote(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString(game.note(), NOTE_X, NOTE_Y);
    }

    private void drawTowers(Graphics g) {
        var prevFont = g.getFont();
        g.setFont(FONT);

        Tower[] towers = game.towers();
        for (int i = 0; i < towers.length; ++i) {
            drawTower(g, towers[i], i);
        }

        g.setFont(prevFont);
    }

    private void drawTower(Graphics g, Tower tower, int index) {
        towerShape(tower, index).draw(g);
    }

    private TowerShape towerShape(Tower tower, int index) {
        int centerX = TOWER_OFFSET + TOWER_WIDTH / 2 + TOWER_WIDTH * index;
        return new TowerShape(tower, centerX, TOWER_TOP, TOWER_HEIGHT);
    }
}
