package workshop.ch03;

import java.awt.*;

public record ArrowText(
        String text,
        int indexX,
        int indexY,
        Color color,
        boolean showText,
        boolean showArrow
) {
    public static ArrowText of(String text, int indexX, int indexY, Color color) {
        return new ArrowText(text, indexX, indexY, color, true, true);
    }

    public static ArrowText ofArrow(int indexX, int indexY, Color color) {
        return new ArrowText(null, indexX, indexY, color, false, true);
    }

    public static ArrowText ofText(String text, int indexX, int indexY, Color color) {
        return new ArrowText(text, indexX, indexY, color, true, false);
    }
}
