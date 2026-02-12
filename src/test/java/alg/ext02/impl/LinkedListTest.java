package alg.ext02.impl;

import alg.ext02.Deque;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedListTest {

    @ParameterizedTest
    @MethodSource("addFirstProvider")
    @DisplayName("Добавление элементов в начало списка")
    void addFirstTest(Integer[] args) {
        var list = new LinkedList<Integer>();
        Stream.of(args).forEach(list::addFirst);
        assertList(reversed(args), list);
    }

    static Stream<Arguments> addFirstProvider() {
        return Stream.of(
                Arguments.of((Object) ints()),
                Arguments.of((Object) ints(1)),
                Arguments.of((Object) ints(1, 2))
        );
    }

    @ParameterizedTest
    @MethodSource("addLastProvider")
    @DisplayName("Добавление элементов в конец списка")
    void addLastTest(Integer[] args) {
        var list = new LinkedList<Integer>();
        Stream.of(args).forEach(list::addLast);
        assertList(args, list);
    }

    static Stream<Arguments> addLastProvider() {
        return Stream.of(
                Arguments.of((Object) ints(1)),
                Arguments.of((Object) ints(1, 2))
        );
    }

    @Test
    @DisplayName("Проверка метода add()")
    void addElementTest() {
        var list = new LinkedList<Integer>();
        assertTrue(list.add(1));
        assertTrue(list.add(2));
        assertTrue(list.add(3));
        assertList(ints(3, 2, 1), list);
    }

    @ParameterizedTest
    @MethodSource("removeFirstProvider")
    @DisplayName("Удаление элементов в начале списка")
    void removeFirstTest(LinkedList<Integer> list, Integer[] returns, Integer[] expected) {
        for (Integer r : returns) {
            if (r == null) {
                assertThrows(NoSuchElementException.class, list::removeFirst);
            } else {
                assertEquals(r, list.removeFirst());
            }
        }
        assertList(expected, list);
    }

    @ParameterizedTest
    @MethodSource("removeFirstProvider")
    @DisplayName("Удаление элементов в начале списка. Использование метода remove")
    void removeTest(LinkedList<Integer> list, Integer[] returns, Integer[] expected) {
        for (Integer r : returns) {
            if (r == null) {
                assertThrows(NoSuchElementException.class, list::remove);
            } else {
                assertEquals(r, list.remove());
            }
        }
        assertList(expected, list);
    }

    static Stream<Arguments> removeFirstProvider() {
        return Stream.of(
                Arguments.of(newList(), ints((Integer) null), ints()),
                Arguments.of(newList(1), ints(1), ints()),
                Arguments.of(newList(1, 2), ints(1), ints(2)),
                Arguments.of(newList(1, 2), ints(1, 2), ints()),
                Arguments.of(newList(1, 2, 3), ints(1, 2), ints(3))
        );
    }

    @ParameterizedTest
    @MethodSource("removeLastProvider")
    @DisplayName("Удаление элементов в конце списка")
    void removeLastTest(LinkedList<Integer> list, Integer[] returns, Integer[] expected) {
        for (Integer r : returns) {
            if (r == null) {
                assertThrows(NoSuchElementException.class, list::removeLast);
            } else {
                assertEquals(r, list.removeLast());
            }
        }
        assertList(expected, list);
    }

    static Stream<Arguments> removeLastProvider() {
        return Stream.of(
                Arguments.of(newList(), ints((Integer) null), ints()),
                Arguments.of(newList(1), ints(1), ints()),
                Arguments.of(newList(1, 2), ints(2), ints(1)),
                Arguments.of(newList(1, 2), ints(2, 1), ints()),
                Arguments.of(newList(1, 2, 3), ints(3, 2), ints(1))
        );
    }

    @ParameterizedTest
    @MethodSource("removeByValueProvider")
    @DisplayName("Удаление элементов по значению")
    void removeByValueTest(
            LinkedList<Integer> list, Integer[] args, Boolean[] returns, Integer[] expected
    ) {
        for (int i = 0; i < args.length; i++) {
            Integer arg = args[i];
            assertEquals(returns[i], list.remove(arg));
        }
        assertList(expected, list);
    }

    static Stream<Arguments> removeByValueProvider() {
        return Stream.of(
                Arguments.of(newList(), ints( 1), booleans(false), ints()),
                Arguments.of(newList(1), ints( 1), booleans(true), ints()),
                Arguments.of(newList(1), ints( 4), booleans(false), ints(1)),
                Arguments.of(newList(1, 2, 3), ints( 4), booleans(false), ints(1, 2, 3)),
                Arguments.of(newList(1, 2, 3), ints( 1), booleans(true), ints(2, 3)),
                Arguments.of(newList(1, 2, 3), ints( 2), booleans(true), ints(1, 3)),
                Arguments.of(newList(1, 2, 3), ints( 3), booleans(true), ints(1, 2))
        );
    }

    @ParameterizedTest
    @MethodSource("getFirstProvider")
    @DisplayName("Получение элемента из начала списка")
    void getFirstTest(LinkedList<Integer> list, Integer expected) {
        if (expected == null) {
            assertThrows(NoSuchElementException.class, list::getFirst);
            assertThrows(NoSuchElementException.class, list::element);
        } else {
            assertEquals(expected, list.getFirst());
            assertEquals(expected, list.element());
        }
    }

    static Stream<Arguments> getFirstProvider() {
        return Stream.of(
                Arguments.of(newList(), null),
                Arguments.of(newList(1), 1),
                Arguments.of(newList(1, 2), 1)
        );
    }

    @ParameterizedTest
    @MethodSource("getLastProvider")
    @DisplayName("Получение элемента из конца списка")
    void getLastTest(LinkedList<Integer> list, Integer expected) {
        if (expected == null) {
            assertThrows(NoSuchElementException.class, list::getFirst);
        } else {
            assertEquals(expected, list.getLast());
        }
    }

    static Stream<Arguments> getLastProvider() {
        return Stream.of(
                Arguments.of(newList(), null),
                Arguments.of(newList(1), 1),
                Arguments.of(newList(1, 2), 2)
        );
    }

    // Проверку в обратном порядке делаю для того, чтобы проверить, что Node.prev заполнены верно
    private static Integer[] descArray(LinkedList<Integer> list) {
        return toDescendingArray(list);
    }

    @SuppressWarnings("unchecked")
    private static <E> E[] toDescendingArray(Deque<E> deque) {
        var array = (E[]) Array.newInstance(Integer.class, deque.size());

        var iter = deque.descendingIterator();
        for (int i = 0; i < deque.size(); i++) {
            array[i] = iter.next();
        }

        return array;
    }

    private static Integer[] reversed(Integer[] a) {
        var b = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            b[a.length - i - 1] = a[i];
        }

        return b;
    }

    private static void assertList(Integer[] expected, LinkedList<Integer> list) {
        assertEquals(expected.length, list.size());
        if (expected.length == 0) {
            assertTrue(list.isEmpty());
        } else {
            assertFalse(list.isEmpty());
        }
        assertArrayEquals(expected, list.toArray(Integer.class));
        assertArrayEquals(reversed(expected), descArray(list));
    }

    private static LinkedList<Integer> newList(Integer... array) {
        var list = new LinkedList<Integer>();
        Stream.of(array).forEach(list::addLast);

        return list;
    }

    private static Integer[] ints(Integer... elements) {
        return elements;
    }

    private static Boolean[] booleans(Boolean... elements) {
        return elements;
    }
}
