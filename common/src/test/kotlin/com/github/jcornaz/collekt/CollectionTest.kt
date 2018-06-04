package com.github.jcornaz.collekt

import kotlin.test.*

abstract class CollectionTest {
    abstract fun create(v1: Int, v2: Int, v3: Int, v4: Int): PersistentCollection<Int>
    abstract fun createEmpty(): PersistentList<Int>

    @Test
    fun shouldContainsAllElements() {
        val collection = create(1, 2, 3, 4)

        assertTrue(1 in collection)
        assertTrue(2 in collection)
        assertTrue(3 in collection)
        assertTrue(4 in collection)
        assertTrue(collection.containsAll(listOf(1, 2, 3)))
        assertTrue(collection.containsAll(emptyList()))
    }

    @Test
    fun shouldNotContainsOtherElements() {
        val collection = create(1, 2, 4, 5)

        assertFalse(0 in collection)
        assertFalse(3 in collection)
        assertFalse(6 in collection)
        assertFalse(-1 in collection)
        assertFalse(collection.containsAll(listOf(1, 2, 7)))
    }

    @Test
    fun sizeShouldReturnTheNumberOfElements() {
        assertEquals(4, create(1, 2, 3, 4).size)
    }

    @Test
    fun isEmptyShouldReturnsFalseIfThereIsElements() {
        assertFalse(create(1, 2, 3, 4).isEmpty())
    }

    @Test
    fun isEmptyShouldReturnTrueIfThereIsNoElement() {
        assertTrue(createEmpty().isEmpty())
    }

    @Test
    fun shouldBeIterable() {
        val collection = create(1, 2, 3, 4)

        val expected = mutableSetOf(1, 2, 3, 4)

        for (element in collection) {
            assertTrue(expected.remove(element))
        }

        assertTrue(expected.isEmpty())
    }

    @Test
    fun differentEmptyCollectionShouldReturnTheSameInstance() {
        assertSame(createEmpty(), createEmpty())
    }

    @Test
    fun shouldSupportEquals() {
        assertEquals(create(1, 2, 3, 4), create(1, 2, 3, 4))
        assertEquals(createEmpty(), createEmpty())
        assertNotEquals(create(1, 2, 3, 4), create(4, 3, 2, 1))
        assertNotEquals(create(1, 2, 3, 4), createEmpty())
        assertNotEquals(create(1, 2, 3, 4), create(1, 2, 4, 5))
    }

    @Test
    fun hashCodeShouldBeConsistent() {
        assertEquals(create(1, 2, 3, 4).hashCode(), create(1, 2, 3, 4).hashCode())
        assertEquals(createEmpty().hashCode(), createEmpty().hashCode())
    }

    @Test
    fun sameShouldBeEquals() {
        val list = create(1, 2, 3, 4)
        assertEquals(list, list)

        val empty = createEmpty()
        assertEquals(empty, empty)
    }
}
