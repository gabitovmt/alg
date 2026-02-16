package workshop.ch06.towers.gg;

import java.awt.*;

public record Disk(Color color, int num) {

    public String label() {
        return Integer.toString(num + 1);
    }
}
