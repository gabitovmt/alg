package alg.ch06.ex12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class BackpackTest {

    @ParameterizedTest
    @MethodSource("backpackProvider")
    void backpackTest(int target, int[] items, int[] expected) {
        int[] actual = Backpack.backpack(target, items);
        Arrays.sort(actual);
        Arrays.sort(expected);

        assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> backpackProvider() {
        return Stream.of(
                Arguments.of(20, new int[]{11, 8, 7, 6, 5}, new int[]{5, 7, 8}),
                Arguments.of(21, new int[]{11, 8, 7, 6, 5}, new int[]{6, 7, 8}),
                Arguments.of(22, new int[]{11, 8, 7, 6, 5}, new int[]{5, 6, 11}),
                Arguments.of(23, new int[]{11, 8, 7, 6, 5}, new int[]{5, 7, 11}),
                Arguments.of(24, new int[]{11, 8, 7, 6, 5}, new int[]{6, 7, 11}),
                Arguments.of(25, new int[]{11, 8, 7, 6, 5}, new int[]{6, 8, 11}),
                Arguments.of(26, new int[]{11, 8, 7, 6, 5}, new int[]{7, 8, 11}),
                Arguments.of(27, new int[]{11, 8, 7, 6, 5}, new int[0]),
                Arguments.of(28, new int[]{11, 8, 7, 6, 5}, new int[0])
        );
    }
}