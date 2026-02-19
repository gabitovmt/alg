package alg.ch06.ex06;

// Решение головоломки "Ханойская башня"
public class TowersApp {
    private static final int N_DISK = 3;

    public static void main(String[] args) {
        doTowers(N_DISK, 'A', 'B', 'C');
    }

    @SuppressWarnings({"java:S106", "java:S2234"}) // Порядок аргументов правильный
    private static void doTowers(int topN, char from, char inter, char to) {
        if (topN == 1) {
            System.out.println("Disk 1 from " + from + " to " + to);
        } else {
            doTowers(topN - 1, from, to, inter);    // from-->inter
            System.out.println("Disk " + topN + " from " + from + " to " + to);
            doTowers(topN - 1, inter, from, to);    // inter-->to
        }
    }
}
