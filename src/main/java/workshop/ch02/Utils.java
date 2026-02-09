package workshop.ch02;

import java.awt.*;
import java.util.Random;

public class Utils {
    private static final Random RANDOM = new Random();

    private Utils() {
    }

    private static int nextColorValue() {
        return 100 + RANDOM.nextInt(155);
    }

    public static Color nextColor() {
        return new Color(nextColorValue(), nextColorValue(), nextColorValue());
    }

    public static int nextHeight() {
        return RANDOM.nextInt(1000);
    }
}
