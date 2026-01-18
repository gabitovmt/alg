package workshop.ch02;

import java.awt.*;
import java.util.Random;

public class Utils {
    private static final Random rand = new Random();

    private Utils() {
    }

    private static int nextColorValue() {
        return 100 + rand.nextInt(155);
    }

    public static Color nextColor() {
        return new Color(nextColorValue(), nextColorValue(), nextColorValue());
    }

    public static int nextHeight() {
        return rand.nextInt(1000);
    }
}
