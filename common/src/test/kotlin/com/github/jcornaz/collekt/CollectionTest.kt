package com.github.jcornaz.collekt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertSame
import kotlin.test.assertTrue

abstract class CollectionTest {
    internal abstract val factory: PersistentCollectionFactory

    @Test
    fun shouldContainsAllElements() {
        val collection = factory.of(1, 2, 3, 4)

        assertTrue(1 in collection)
        assertTrue(2 in collection)
        assertTrue(3 in collection)
        assertTrue(4 in collection)
    }

    @Test
    fun shouldNotContainsOtherElements() {
        val collection = factory.of(1, 2, 4, 5)

        assertFalse(0 in collection)
        assertFalse(3 in collection)
        assertFalse(6 in collection)
        assertFalse(-1 in collection)
    }

    @Test
    fun sizeShouldReturnTheNumberOfElements() {
        assertEquals(4, factory.of(1, 2, 3, 4).size)
    }

    @Test
    fun isEmptyShouldReturnsFalseIfThereIsElements() {
        assertFalse(factory.of(1, 2, 3, 4).isEmpty)
    }

    @Test
    fun isEmptyShouldReturnTrueIfThereIsNoElement() {
        assertTrue(factory.empty<Int>().isEmpty)
    }

    @Test
    fun shouldBeIterable() {
        val collection = factory.of(1, 2, 3, 4)

        val expected = mutableSetOf(1, 2, 3, 4)

        for (element in collection) {
            assertTrue(expected.remove(element))
        }

        assertTrue(expected.isEmpty())
    }

    @Test
    fun differentEmptyCollectionShouldReturnTheSameInstance() {
        assertSame<Any>(factory.empty<Int>(), factory.empty<String>())
    }

    @Test
    fun shouldSupportEquals() {
        assertEquals(factory.of(1, 2, 3, 4), factory.of(1, 2, 3, 4))
        assertEquals(factory.empty<Int>(), factory.empty())
        assertNotEquals(factory.of(1, 2, 3, 4), factory.of(4, 3, 2, 1))
        assertNotEquals(factory.of(1, 2, 3, 4), factory.empty())
        assertNotEquals(factory.of(1, 2, 3, 4), factory.of(1, 2, 4, 5))
    }

    @Test
    fun hashCodeShouldBeConsistent() {
        assertEquals(factory.of(1, 2, 3, 4).hashCode(), factory.of(1, 2, 3, 4).hashCode())
        assertEquals(factory.empty<Int>().hashCode(), factory.empty<Int>().hashCode())
    }

    @Test
    fun sameShouldBeEquals() {
        val list = factory.of(1, 2, 3, 4)
        assertEquals(list, list)

        val empty = factory.empty<Int>()
        assertEquals(empty, empty)
    }

    @Test
    fun shouldHaveComprehensiveToString() {
        assertEquals("[1, 2, 3, 4]", factory.of(1, 2, 3, 4).toString())
    }
}
