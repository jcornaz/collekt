package com.github.jcornaz.collekt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertSame
import kotlin.test.assertTrue

abstract class ListTest : CollectionTest() {
    abstract override val factory: PersistentListFactory

    @Test
    fun shouldSupportDuplicateElements() {
        assertEquals(4, factory.of(1, 2, 2, 3).size)
    }

    @Test
    fun shouldBeAccessibleByIndex() {
        val list = factory.of(1, 2, 2, 3)
        assertEquals(1, list[0])
        assertEquals(2, list[1])
        assertEquals(2, list[2])
        assertEquals(3, list[3])
    }

    @Test
    fun wrongIndexShouldThrowIndexOutOfBoundException() {
        val list = factory.of(1, 2, 2, 3)

        assertFailsWith<IndexOutOfBoundsException> { list[-1] }
        assertFailsWith<IndexOutOfBoundsException> { list[4] }
    }

    @Test
    fun indexOfShouldReturnTheFirstIndex() {
        val list = factory.of(1, 2, 2, 3)

        assertEquals(0, list.indexOf(1))
        assertEquals(1, list.indexOf(2))
        assertEquals(3, list.indexOf(3))
        assertEquals(-1, list.indexOf(4))
    }

    @Test
    fun lastIndexOfShouldReturnTheLastIndex() {
        val list = factory.of(1, 2, 2, 3)

        assertEquals(0, list.lastIndexOf(1))
        assertEquals(2, list.lastIndexOf(2))
        assertEquals(3, list.lastIndexOf(3))
        assertEquals(-1, list.lastIndexOf(4))
    }

    @Test
    fun iterationShouldPreserveOrder() {
        val iterator = factory.of(1, 2, 3, 4).iterator()

        assertTrue(iterator.hasNext())
        assertEquals(1, iterator.next())

        assertTrue(iterator.hasNext())
        assertEquals(2, iterator.next())

        assertTrue(iterator.hasNext())
        assertEquals(3, iterator.next())

        assertTrue(iterator.hasNext())
        assertEquals(4, iterator.next())

        assertFalse(iterator.hasNext())
    }

    @Test
    fun shouldSupportEqualsWithStdList() {
        assertEquals(factory.of(1, 2, 3, 4).asList(), listOf(1, 2, 3, 4))
        assertEquals(factory.empty<Int>().asList(), emptyList())
        assertNotEquals(factory.of(1, 2, 3, 4).asList(), listOf(4, 3, 2, 1))
        assertNotEquals(factory.of(1, 2, 3, 4).asList(), emptyList())
        assertNotEquals(factory.of(1, 2, 3, 4).asList(), listOf(1, 2, 4))
    }

    @Test
    fun hashCodeShouldBeConsistentWithStdList() {
        assertEquals(listOf(1, 2, 3, 4).hashCode(), factory.of(1, 2, 3, 4).asList().hashCode())
        assertEquals(emptyList<Int>().hashCode(), factory.empty<Int>().asList().hashCode())
    }

    @Test
    fun subListShouldReturnElementsBetweenTheGivenIndexes() {
        val subList = factory.of(1, 2, 3, 4).subList(1, 3)

        assertFalse(subList.isEmpty)
        assertFailsWith<IndexOutOfBoundsException> { subList[-1] }
        assertEquals(2, subList[0])
        assertEquals(3, subList[1])
        assertFailsWith<IndexOutOfBoundsException> { subList[2] }
    }

    @Test
    fun subListShouldSupportContains() {
        val subList = factory.of(1, 2, 3, 4).subList(2, 4)

        assertTrue(subList.contains(3))
        assertFalse(subList.contains(2))
    }

    @Test
    fun emptySubListShouldReturnEmptyList() {
        val subList = factory.of(1, 2, 3, 4).subList(1, 1)

        assertTrue(subList.isEmpty)
        assertEquals(subList, factory.empty())
    }
}
