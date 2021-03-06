package com.github.jcornaz.collekt.test

import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.api.of
import kotlin.test.*

public abstract class PersistentListTest : PersistentCollectionTest() {
    public abstract override val factory: PersistentListFactory

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
    fun indexOfFirstShouldReturnTheFirstIndex() {
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
    fun reverseIterationShouldReturnElementsFromTheLastToTheFirst() {
        val iterator = factory.of(1, 2, 3, 4).listIterator(4)

        assertTrue(iterator.hasPrevious())
        assertEquals(4, iterator.previous())

        assertTrue(iterator.hasPrevious())
        assertEquals(3, iterator.previous())

        assertTrue(iterator.hasPrevious())
        assertEquals(2, iterator.previous())

        assertTrue(iterator.hasPrevious())
        assertEquals(1, iterator.previous())

        assertFalse(iterator.hasPrevious())
    }

    @Test
    fun elementsInDifferentOrderShouldNotBeEquals() {
        assertNotEquals(factory.of(1, 2, 3, 4), factory.of(1, 4, 3, 2))
        assertNotEquals(factory.of(1, 2, 3, 4), factory.of(4, 3, 2, 1))
    }

    @Test
    fun differentOccurrenceCountOfElementShouldNotBeEquals() {
        assertNotEquals(factory.of(1, 2, 3, 3), factory.of(1, 2, 3))
        assertNotEquals(factory.of(1, 2, 3, 3), factory.of(1, 2, 2, 3))
    }

    @Test
    fun equivalentListShouldBeEqualsStdList() {
        assertEquals(factory.of(1, 2, 3, 4), listOf(1, 2, 3, 4))
        assertEquals(factory.of(1), listOf(1))
        assertEquals(factory.of(), listOf<Int>())
        assertEquals(factory.empty(), emptyList<Int>())
    }

    @Test
    fun differentListShouldNotEqualsStdList() {
        assertNotEquals(factory.of(1, 2, 3, 4), listOf(4, 3, 2, 1))
        assertNotEquals(factory.of(1, 2, 2, 3), listOf(1, 2, 3))
        assertNotEquals(factory.of(1, 2, 3), listOf(1, 2, 3, 3))
        assertNotEquals(factory.of(1, 2, 3, 4), listOf(1, 2, 4))
        assertNotEquals(factory.of(1, 2, 3, 4), emptyList<Int>())
        assertNotEquals(factory.empty(), listOf(1))
    }

    @Test
    fun hashCodeShouldBeConsistentWithStdList() {
        assertEquals(listOf(1, 2, 3, 4).hashCode(), factory.of(1, 2, 3, 4).hashCode())
        assertEquals(emptyList<Int>().hashCode(), factory.empty<Int>().hashCode())
    }

    @Test
    fun subListShouldReturnElementsBetweenTheGivenIndexes() {
        val subList = factory.of(1, 2, 3, 4).subList(1, 3)

        assertEquals(factory.of(2, 3), subList)
        assertFalse(subList.isEmpty())
        assertFailsWith<IndexOutOfBoundsException> { subList[-1] }
        assertEquals(2, subList[0])
        assertEquals(3, subList[1])
        assertFailsWith<IndexOutOfBoundsException> { subList[2] }
    }

    @Test
    fun subListShouldReturnEmptyListWhenToIndexAndFromIndexAreEquals() {
        assertEquals(factory.empty(), factory.empty<Int>().subList(0, 0))
        assertEquals(factory.empty(), factory.of(1, 2, 3).subList(1, 1))
    }

    @Test
    fun subListShouldReturnEquivalentListForIndexBetween0AndSize() {
        assertEquals(factory.of(1, 2, 3), factory.of(1, 2, 3).subList(0, 3))
    }

    @Test
    fun subListShouldThrowIndexOutOfBoundExceptionForWrongIndexes() {
        assertFailsWith<IndexOutOfBoundsException> { factory.empty<Int>().subList(-1, 0) }
        assertFailsWith<IndexOutOfBoundsException> { factory.empty<Int>().subList(0, 1) }
        assertFailsWith<IndexOutOfBoundsException> { factory.of(1, 2, 3).subList(-1, 2) }
        assertFailsWith<IndexOutOfBoundsException> { factory.of(1, 2, 3).subList(2, 4) }
        assertFailsWith<IndexOutOfBoundsException> { factory.of(1, 2, 3).subList(2, 1) }
    }

    @Test
    fun splitShouldReturnTwoLists() {
        val source = factory.of(1, 2, 3, 4)

        assertEquals(factory.of(1, 2) to factory.of(3, 4), source.split(2))
        assertEquals(factory.empty<Int>() to factory.of(1, 2, 3, 4), source.split(0))
        assertEquals(factory.of(1, 2, 3, 4) to factory.empty(), source.split(4))
    }

    @Test
    fun splitShouldReturnTwoEmptyListsIfTheSourceIsEmpty() {
        assertEquals(factory.empty<Int>() to factory.empty(), factory.empty<Int>().split(0))
    }

    @Test
    fun splitShouldThrowIndexOutOfBoundExceptionForWrongIndexes() {
        assertFailsWith<IndexOutOfBoundsException> { factory.empty<Int>().split(-1) }
        assertFailsWith<IndexOutOfBoundsException> { factory.of(1, 2, 3).split(-1) }
        assertFailsWith<IndexOutOfBoundsException> { factory.of(1, 2, 3).split(4) }
    }

    @Test
    fun emptySubListShouldReturnEmptyList() {
        val subList = factory.of(1, 2, 3, 4).subList(1, 1)

        assertTrue(subList.isEmpty())
        assertEquals(subList, factory.empty())
    }

    @Test
    fun withShouldReturnCollectionWithTheElementReplaced() {
        val source = factory.of(1, 2, 3)
        val result = source.with(1, 4)

        assertEquals(4, result[1])
        assertEquals(result, factory.of(1, 4, 3))

        assertEquals(2, source[1])
        assertEquals(source, factory.of(1, 2, 3))
    }

    @Test
    fun withShouldThrowIndexOutOfBoundIfIndexIsOutOfBound() {
        assertFailsWith<IndexOutOfBoundsException> {
            factory.empty<Int>().with(0, 1)
        }

        assertFailsWith<IndexOutOfBoundsException> {
            factory.of(1, 2, 3).with(-1, 0)
        }

        assertFailsWith<IndexOutOfBoundsException> {
            factory.of(1, 2, 3).with(3, 0)
        }
    }

    @Test
    fun plusAtIndexShouldReturnCollectionWithTheElement() {
        val list1 = factory.empty<Int>()
        val list2 = list1.plus(index = 0, element = 0)
        val list3 = list2.plus(index = 0, element = 1)
        val list4 = list3.plus(index = 1, element = 2)

        assertTrue(list1.isEmpty())

        assertEquals(0, list2[0])
        assertEquals(0, list3[1])
        assertEquals(0, list4[2])
        assertEquals(1, list3[0])
        assertEquals(1, list4[0])
        assertEquals(2, list4[1])

        assertEquals(factory.empty(), list1)
        assertEquals(factory.of(0), list2)
        assertEquals(factory.of(1, 0), list3)
        assertEquals(factory.of(1, 2, 0), list4)
    }

    @Test
    fun plusCollectionAtIndexShouldReturnACollectionWithTheGivenCollectionInsertedAtIndex() {
        val col1 = factory.of(1, 2, 3)
        val col2 = factory.of(4, 5, 6)
        val result = col1.plus(1, col2)

        assertEquals(factory.of(1, 2, 3), col1)
        assertEquals(factory.of(4, 5, 6), col2)
        assertEquals(factory.of(1, 4, 5, 6, 2, 3), result)
    }

    @Test
    fun plusEmptyCollectionAtIndexShouldReturnAnEqualCollection() {
        val col = factory.of(1, 2, 3)

        assertEquals(col, col.plus(1, factory.empty()))
    }

    @Test
    fun minusIndexShouldReturnACollectionWithoutTheUnderlingElement() {
        val col = factory.of(1, 2, 3)
        val result = col.minusIndex(1)

        assertEquals(-1, result.indexOf(2))
        assertEquals(factory.of(1, 2, 3), col)
        assertEquals(factory.of(1, 3), result)
    }

    @Test
    fun minusIndexShouldThrowIndexOutOfBoundForWrongIndexes() {
        assertFailsWith<IndexOutOfBoundsException> { factory.empty<Int>().minusIndex(0) }
        assertFailsWith<IndexOutOfBoundsException> { factory.empty<Int>().minusIndex(-1) }
        assertFailsWith<IndexOutOfBoundsException> { factory.of(1, 2, 3).minusIndex(-1) }
        assertFailsWith<IndexOutOfBoundsException> { factory.of(1, 2, 3).minusIndex(3) }
    }
}
