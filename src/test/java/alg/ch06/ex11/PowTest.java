package alg.ch06.ex11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowTest {

    @ParameterizedTest
    @MethodSource("powProvider")
    void powTest(long x, long y) {
        assertEquals(Math.pow(x, y), Pow.pow(x, y));
        System.out.println(Pow.pow(x, y));
    }

    static Stream<Arguments> powProvider() {
        var list = new ArrayList<Arguments>();
        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {
                list.add(Arguments.of(x, y));
            }
        }

        return list.stream();
    }
}