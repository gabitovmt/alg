package alg.ext02.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SortedLinkedListTest {

    @Test
    @DisplayName("Список пустой")
    void emptyTest() {
        var list = new SortedLinkedList<Integer>();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Добавить один элемент")
    void addOneElementTest() {
        var list = new SortedLinkedList<Integer>();

        assertTrue(list.add(1));

        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new Integer[]{1}, list.toArray(Integer.class));
    }

    @Test
    @DisplayName("Добавить два элемента по возрастанию")
    void addTwoElementsAscTest() {
        var list = new SortedLinkedList<Integer>();

        assertTrue(list.add(1));
        assertTrue(list.add(2));

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new Integer[]{1, 2}, list.toArray(Integer.class));
    }

    @Test
    @DisplayName("Добавить два элемента по убыванию")
    void addTwoElementsDescTest() {
        var list = new SortedLinkedList<Integer>();

        assertTrue(list.add(2));
        assertTrue(list.add(1));

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new Integer[]{1, 2}, list.toArray(Integer.class));
    }

    @Test
    @DisplayName("Получить элемент без извлечения")
    void peekTest() {
        var list = new SortedLinkedList<Integer>();
        list.add(1);
        list.add(2);

        assertEquals(1, list.element());

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new Integer[]{1, 2}, list.toArray(Integer.class));
    }

    @Test
    @DisplayName("Получить элемент без извлечения из пустого списка")
    void peekFromEmptyTest() {
        var list = new SortedLinkedList<Integer>();
        assertThrows(NoSuchElementException.class, list::element);
    }

    @Test
    @DisplayName("Извлечь элемент")
    void pollTest() {
        var list = new SortedLinkedList<Integer>();
        list.add(1);
        list.add(2);

        assertEquals(1, list.remove());

        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertArrayEquals(new Integer[]{2}, list.toArray(Integer.class));
    }

    @Test
    @DisplayName("Извлечь элемент из пустого списка")
    void pollFromEmptyTest() {
        var list = new SortedLinkedList<Integer>();
        assertThrows(NoSuchElementException.class, list::remove);
    }

    @Test
    @DisplayName("Удаление элемента в пустом списке")
    void removeFromEmptyTest() {
        var list = new SortedLinkedList<Integer>();

        assertFalse(list.remove(1));
    }

    @Test
    @DisplayName("Удаление первого элемента")
    void removeFirstTest() {
        var list = new SortedLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.remove(1));
        assertEquals(2, list.size());
        assertArrayEquals(new Integer[]{2, 3}, list.toArray(Integer.class));
    }

    @Test
    @DisplayName("Удаление последнего элемента")
    void removeLastTest() {
        var list = new SortedLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.remove(3));
        assertEquals(2, list.size());
        assertArrayEquals(new Integer[]{1, 2}, list.toArray(Integer.class));
    }

    @Test
    @DisplayName("Удаление несуществующего элемента")
    void removeNotExistTest() {
        var list = new SortedLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertFalse(list.remove(4));
        assertEquals(3, list.size());
        assertArrayEquals(new Integer[]{1, 2, 3}, list.toArray(Integer.class));
    }

    @Test
    @DisplayName("Итератор пустого списка")
    void iteratorFromEmptyTest() {
        var list = new SortedLinkedList<Integer>();
        var iter = list.iterator();

        assertFalse(iter.hasNext());
        assertThrows(NoSuchElementException.class, iter::next);
    }

    @Test
    @DisplayName("Итератор не пустого списка")
    void iteratorTest() {
        var list = new SortedLinkedList<Integer>();
        list.add(1);
        list.add(2);
        var iter = list.iterator();

        assertTrue(iter.hasNext());
        assertEquals(1, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(2, iter.next());
        assertFalse(iter.hasNext());
        assertThrows(NoSuchElementException.class, iter::next);
    }

    @Test
    @DisplayName("Метод toString() пустого списка")
    void toStringFromEmptyTest() {
        var list = new SortedLinkedList<Integer>();
        assertEquals("[]", list.toString());
    }

    @Test
    @DisplayName("Метод toString() не пустого списка")
    void toStringTest() {
        var list = new SortedLinkedList<Integer>();
        list.add(1);
        list.add(2);
        assertEquals("[1, 2]", list.toString());
    }
}