package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.RandomAccessIterator
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RandomAccessIteratorTest {

    @Test
    fun testNormalIteration() {
        val iterator = RandomAccessIterator(0, 3) { it }

        assertTrue(iterator.hasNext())
        assertEquals(0, iterator.next())

        assertTrue(iterator.hasNext())
        assertEquals(1, iterator.next())

        assertTrue(iterator.hasNext())
        assertEquals(2, iterator.next())

        assertFalse(iterator.hasNext())
    }

    @Test
    fun testReverseIteration() {
        val iterator = RandomAccessIterator(3, 3) { it }

        assertTrue(iterator.hasPrevious())
        assertEquals(2, iterator.previous())

        assertTrue(iterator.hasPrevious())
        assertEquals(1, iterator.previous())

        assertTrue(iterator.hasPrevious())
        assertEquals(0, iterator.previous())

        assertFalse(iterator.hasPrevious())
    }

    @Test
    fun testArbitraryIteration() {
        val iterator = RandomAccessIterator(1, 3) { it }

        assertTrue(iterator.hasPrevious())
        assertTrue(iterator.hasNext())
        assertEquals(0, iterator.previousIndex())
        assertEquals(1, iterator.nextIndex())

        assertEquals(1, iterator.next())

        assertTrue(iterator.hasPrevious())
        assertTrue(iterator.hasNext())
        assertEquals(1, iterator.previousIndex())
        assertEquals(2, iterator.nextIndex())

        assertEquals(2, iterator.next())

        assertTrue(iterator.hasPrevious())
        assertFalse(iterator.hasNext())
        assertEquals(2, iterator.previousIndex())
        assertEquals(3, iterator.nextIndex())

        assertEquals(2, iterator.previous())
        assertEquals(1, iterator.previous())
        assertEquals(0, iterator.previous())

        assertFalse(iterator.hasPrevious())
        assertTrue(iterator.hasNext())
        assertEquals(-1, iterator.previousIndex())
        assertEquals(0, iterator.nextIndex())
    }
}
