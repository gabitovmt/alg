package alg.ext02.impl;

import alg.ext02.Deque;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Array;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedListTest {

    @ParameterizedTest
    @MethodSource("addFirstProvider")
    @DisplayName("{1}")
    void addFirstTest(String testName, Integer[] items) {
        var list = new LinkedList<Integer>();
        Stream.of(items).forEach(list::addFirst);
        assertList(reversed(items), list);
    }

    static Stream<Arguments> addFirstProvider() {
        return Stream.of(
                Arguments.of("Пустой список", new Integer[0]),
                Arguments.of("Добавить один элемент в начало списка", new Integer[]{1}),
                Arguments.of("Добавить два элемента в начало списка", new Integer[]{1, 2})
        );
    }

    @ParameterizedTest
    @MethodSource("addLastProvider")
    @DisplayName("{1}")
    void addLastTest(String testName, Integer[] items) {
        var list = new LinkedList<Integer>();
        Stream.of(items).forEach(list::addLast);
        assertList(items, list);
    }

    @Test
    @DisplayName("Проверка метода add()")
    void addElementTest() {
        var list = new LinkedList<Integer>();
        assertTrue(list.add(1));
        assertTrue(list.add(2));
        assertTrue(list.add(3));
        assertList(new Integer[]{3, 2, 1}, list);
    }

    static Stream<Arguments> addLastProvider() {
        return Stream.of(
                Arguments.of("Добавить один элемент в конец списка", new Integer[]{1}),
                Arguments.of("Добавить два элемента в конец списка", new Integer[]{1, 2})
        );
    }

    // Проверку в обратном порядке делаю для того, чтобы проверить, что Node.prev заполнены верно
    private Integer[] descArray(LinkedList<Integer> list) {
        return toDescendingArray(list, Integer.class);
    }

    @SuppressWarnings("unchecked")
    private <E> E[] toDescendingArray(Deque<E> deque, Class<E> clazz) {
        var array = (E[]) Array.newInstance(clazz, deque.size());

        var iter = deque.descendingIterator();
        for (int i = 0; i < deque.size(); i++) {
            array[i] = iter.next();
        }

        return array;
    }

    private Integer[] reversed(Integer[] a) {
        var b = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            b[a.length - i - 1] = a[i];
        }

        return b;
    }

    private void assertList(Integer[] expected, LinkedList<Integer> list) {
        assertEquals(expected.length, list.size());
        if (expected.length == 0) {
            assertTrue(list.isEmpty());
        } else {
            assertFalse(list.isEmpty());
        }
        assertArrayEquals(expected, list.toArray(Integer.class));
        assertArrayEquals(reversed(expected), descArray(list));
    }
}
