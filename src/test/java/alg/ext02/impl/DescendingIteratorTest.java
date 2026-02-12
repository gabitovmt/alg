package alg.ext02.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DescendingIteratorTest {

    @Test
    @DisplayName("Итератор пустой")
    void emptyTest() {
        var iterator = new DescendingIterator<Integer>(null);

        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    @DisplayName("Один элемент")
    void oneElementTest() {
        var first = new Node<>(1);
        var iterator = new DescendingIterator<>(first);

        assertTrue(iterator.hasNext());
        assertEquals(first.item, iterator.next());

        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    @DisplayName("Два элемента")
    void twoElementsTest() {
        var first = new Node<>(1);
        var second = new Node<>(2);
        first.next = second;
        second.prev = first;
        var iterator = new DescendingIterator<>(second);

        assertTrue(iterator.hasNext());
        assertEquals(second.item, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(first.item, iterator.next());

        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
