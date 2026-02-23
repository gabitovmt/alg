package alg.ch06.ex12;

public class Backpack {

    private Backpack() {
    }

    public static int[] backpack(int target, int... items) {
        for (int i = 0; i < items.length; i++) {
            int item = items[i];

            if (target == item) {
                return new int[]{item};
            }
            if (target > item) {
                int[] b = backpack(target - item, subarray(items, i));
                if (b.length > 0) {
                    return union(b, item);
                }
            }
        }

        return new int[0];
    }

    private static int[] subarray(int[] items, int excludeIdx) {
        int[] result = new int[items.length - 1];
        int j = 0;
        for (int i = 0; i < result.length; i++) {
            if (i != excludeIdx) {
                result[j++] = items[i];
            }
        }

        return result;
    }

    private static int[] union(int[] items, int addItem) {
        int[] result = new int[items.length + 1];
        System.arraycopy(items, 0, result, 0, items.length);
        result[result.length - 1] = addItem;

        return result;
    }
}
