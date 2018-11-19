package com.github.jcornaz.collekt.test

import com.github.jcornaz.collekt.api.PersistentCollection
import com.github.jcornaz.collekt.api.PersistentCollectionFactory
import com.github.jcornaz.collekt.api.of
import kotlin.test.*

public abstract class PersistentCollectionTest {
    public abstract val factory: PersistentCollectionFactory

    @Test
    fun emptyFromFactoryShouldNotContainsElement() {
        assertTrue(factory.empty<Int>().isEmpty())
        assertEquals(0, factory.empty<Int>().size)
        assertFalse(factory.empty<Int>().iterator().hasNext())
    }

    @Test
    fun emptyFromInstanceShouldNotContainsElement() {
        val empty = factory.of(1, 2, 3, 4).empty()

        assertTrue(empty.isEmpty())
        assertEquals(0, empty.size)
        assertFalse(empty.iterator().hasNext())
    }

    @Test
    fun shouldContainsAllElements() {
        val collection = factory.of(1, 2, 3, 4)

        assertTrue(collection.any { it == 1 })
        assertTrue(collection.any { it == 2 })
        assertTrue(collection.any { it == 3 })
        assertTrue(collection.any { it == 4 })
    }

    @Test
    fun shouldNotContainsOtherElements() {
        val collection = factory.of(1, 2, 4, 5)

        assertFalse(collection.any { it == 0 })
        assertFalse(collection.any { it == 3 })
        assertFalse(collection.any { it == 6 })
        assertFalse(collection.any { it == -1 })
    }

    @Test
    fun sizeShouldReturnTheNumberOfElements() {
        assertEquals(4, factory.of(1, 2, 3, 4).size)
    }

    @Test
    fun isEmptyShouldReturnsFalseIfThereIsElements() {
        assertFalse(factory.of(1, 2, 3, 4).isEmpty())
    }

    @Test
    fun isEmptyShouldReturnTrueIfThereIsNoElement() {
        assertTrue(factory.empty<Int>().isEmpty())
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
    fun equivalentCollectionShouldBeEquals() {
        assertEquals(factory.empty<Int>(), factory.empty())
        assertEquals(factory.of(1), factory.of(1))
        assertEquals(factory.of(1, 2, 3, 4), factory.of(1, 2, 3, 4))
    }

    @Test
    fun collectionWithDifferentElementsShouldNotBeEquals() {
        assertNotEquals(factory.of(1, 2, 3, 4), factory.empty())
        assertNotEquals(factory.of(1, 2, 3, 4), factory.of(1, 2, 4, 5))
        assertNotEquals(factory.of(1, 2, 3, 4), factory.of(1, 2, 4))
        assertNotEquals(factory.of(0), factory.of(1))
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

    @Test
    fun plusShouldReturnCollectionWithTheElement() {
        val col1 = factory.empty<Int>()
        val col2 = col1 + 0
        val col3 = col2 + 1

        assertTrue(col1.isEmpty())
        assertEquals(factory.empty(), col1)

        assertTrue(col2.any { it == 0 })
        assertFalse(col2.any { it == 1 })
        assertEquals(factory.of(0), col2)

        assertTrue(col3.any { it == 0 })
        assertTrue(col3.any { it == 1 })
        assertEquals(factory.of(0, 1), col3)
    }

    @Test
    fun plusEmptyCollectionShouldReturnAnEqualCollection() {
        val col = factory.of(1, 2, 3)

        assertEquals(col, col + factory.empty())
    }

    @Test
    fun minusEmptyCollectionShouldReturnAnEqualCollection() {
        val col = factory.of(1, 2, 3)

        assertEquals(col, col - factory.empty())
    }

    @Test
    fun plusCollectionShouldReturnACollectionContainingBothCollections() {
        val col1 = factory.of(1, 2, 3)
        val col2 = factory.of(4, 5, 6)
        val result = col1 + col2

        assertEquals(factory.of(1, 2, 3), col1)
        assertEquals(factory.of(4, 5, 6), col2)
        assertEquals(factory.of(1, 2, 3, 4, 5, 6), result)
    }

    @Test
    fun minusElementShouldReturnACollectionWithoutTheUnderlingElement() {
        val col = factory.of(1, 2, 3)
        val result = col - 2

        assertTrue(col.any { it == 2 })
        assertFalse(result.any { it == 2 })
        assertEquals(factory.of(1, 2, 3), col)
        assertEquals(factory.of(1, 3), result)
    }

    @Test
    fun nullElementsShouldBeSupportedAtCreation() {
        val collection: PersistentCollection<Int?> = factory.of(1, 2, null, 4)

        assertEquals(4, collection.size)
        assertEquals(factory.of(1, 2, null, 4), collection)
        assertNotEquals(factory.of(1, 2, 4), collection)

        assertTrue(null in collection)
        assertTrue(4 in collection)
        assertFalse(0 in collection)

        assertEquals(setOf(1, 2, null, 4), collection.toSet())
        assertEquals(17, collection.sumBy { it ?: 10 })
    }

    @Test
    fun nullElementsShouldBeSupportedForAddition() {
        val collection: PersistentCollection<Int?> = factory.of<Int?>(1, 2, 4) + null


        assertEquals(4, collection.size)
        assertEquals(factory.of(1, 2, 4, null), collection)
        assertNotEquals(factory.of(1, 2, 4), collection)

        assertTrue(null in collection)
        assertTrue(4 in collection)
        assertFalse(0 in collection)

        assertEquals(setOf(1, 2, null, 4), collection.toSet())
        assertEquals(17, collection.sumBy { it ?: 10 })
    }

    @Test
    fun nullElementsShouldBeSupportedForRemoval() {
        val collection: PersistentCollection<Int?> = factory.of(1, 2, null, 4) - null


        assertEquals(3, collection.size)
        assertEquals(factory.of(1, 2, 4), collection)
        assertNotEquals(factory.of(1, 2, null, 4), collection)
        assertEquals(factory.of(1, 2, 4), collection)

        assertFalse(null in collection)
        assertTrue(4 in collection)
        assertFalse(0 in collection)

        assertNotEquals(setOf(1, 2, null, 4), collection.toSet())
        assertEquals(7, collection.sumBy { it ?: 10 })
    }
}
