package alg.ch06.ex11;

public class Pow {
    private Pow() {
    }

    public static long pow(long x, long y) {
        if (y == 1) {
            return x;
        }

        return y % 2 == 0
                ? pow(x * x, y / 2)
                : pow(x * x, y / 2) * x;
    }
}
