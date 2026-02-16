package workshop.ch06.towers.support;

import java.awt.*;
import java.util.Random;

public class Utils {
    private static final Random RANDOM = new Random();

    private Utils() {
    }

    private static int nextColorValue() {
        return 127 + RANDOM.nextInt(128);
    }

    public static Color nextColor() {
        return new Color(nextColorValue(), nextColorValue(), nextColorValue());
    }
}
