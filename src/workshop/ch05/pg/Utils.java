package workshop.ch05.pg;

import java.awt.*;
import java.util.Random;

public class Utils {
    private static final Random RANDOM = new Random();

    private Utils() {
    }

    private static int nextColorValue() {
        return 128 + RANDOM.nextInt(128);
    }

    public static Color nextColor() {
        return new Color(nextColorValue(), nextColorValue(), nextColorValue());
    }

    public static int nextHeight() {
        return RANDOM.nextInt(1000);
    }

    public static Person nextPerson() {
        return new Person(nextHeight(), nextColor());
    }
}
