package com.github.jcornaz.collekt

import kotlin.test.*

abstract class ListTest : CollectionTest() {
    abstract override fun create(v1: Int, v2: Int, v3: Int, v4: Int): ImmutableList<Int>
    abstract override fun createEmpty(): ImmutableList<Int>

    @Test
    fun shouldSupportDuplicateElements() {
        assertEquals(4, create(1, 2, 2, 3).size)
    }

    @Test
    fun shouldBeAccessibleByIndex() {
        val list = create(1, 2, 2, 3)
        assertEquals(1, list[0])
        assertEquals(2, list[1])
        assertEquals(2, list[2])
        assertEquals(3, list[3])
    }

    @Test
    fun wrongIndexShouldThrowIndexOutOfBoundException() {
        val list = create(1, 2, 2, 3)

        assertFailsWith<IndexOutOfBoundsException> { list[-1] }
        assertFailsWith<IndexOutOfBoundsException> { list[4] }
    }

    @Test
    fun indexOfShouldReturnTheFirstIndex() {
        val list = create(1, 2, 2, 3)

        assertEquals(0, list.indexOf(1))
        assertEquals(1, list.indexOf(2))
        assertEquals(3, list.indexOf(3))
        assertEquals(-1, list.indexOf(4))
    }

    @Test
    fun lastIndexOfShouldReturnTheLastIndex() {
        val list = create(1, 2, 2, 3)

        assertEquals(0, list.lastIndexOf(1))
        assertEquals(2, list.lastIndexOf(2))
        assertEquals(3, list.lastIndexOf(3))
        assertEquals(-1, list.lastIndexOf(4))
    }

    @Test
    fun iterationShouldPreserveOrder() {
        val iterator = create(1, 2, 3, 4).iterator()

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
    fun shouldSupportListIterator() {
        val iterator = create(1, 2, 3, 4).listIterator(2)

        assertTrue(iterator.hasPrevious())
        assertTrue(iterator.hasNext())
        assertEquals(3, iterator.next())

        assertTrue(iterator.hasPrevious())
        assertTrue(iterator.hasNext())
        assertEquals(4, iterator.next())

        assertTrue(iterator.hasPrevious())
        assertFalse(iterator.hasNext())
        assertEquals(4, iterator.previous())

        assertTrue(iterator.hasPrevious())
        assertTrue(iterator.hasNext())
        assertEquals(3, iterator.previous())

        assertTrue(iterator.hasPrevious())
        assertTrue(iterator.hasNext())
        assertEquals(2, iterator.previous())

        assertTrue(iterator.hasPrevious())
        assertTrue(iterator.hasNext())
        assertEquals(1, iterator.previous())

        assertFalse(iterator.hasPrevious())
        assertTrue(iterator.hasNext())
    }

    @Test
    fun listIteratorShouldThrowIndexOutOfBoundExceptionForWrongIndexes() {
        val list = create(1, 2, 3, 4)

        assertFailsWith<IndexOutOfBoundsException> { list.listIterator(-1) }
        assertFailsWith<IndexOutOfBoundsException> { list.listIterator(5) }
    }

    @Test
    fun listIteratorFromTheEndShouldBeValid() {
        val list = create(1, 2, 3, 4).listIterator(4)

        assertTrue(list.hasPrevious())
        assertFalse(list.hasNext())
    }

    @Test
    fun shouldSupportEqualsWithStdList() {
        assertEquals(create(1, 2, 3, 4), listOf(1, 2, 3, 4))
        assertEquals(createEmpty(), emptyList<Int>())
        assertNotEquals(create(1, 2, 3, 4), listOf(4, 3, 2, 1))
        assertNotEquals(create(1, 2, 3, 4), emptyList<Int>())
        assertNotEquals(create(1, 2, 3, 4), listOf(1, 2, 4))
    }

    @Test
    fun hashCodeShouldBeConsistentWithStdList() {
        assertEquals(listOf(1, 2, 3, 4).hashCode(), create(1, 2, 3, 4).hashCode())
        assertEquals(emptyList<Int>().hashCode(), createEmpty().hashCode())
    }

    @Test
    fun shouldHaveComprehensiveToString() {
        assertEquals("[1, 2, 3, 4]", create(1, 2, 3, 4).toString())
    }

    @Test
    fun subListShouldReturnElementsBetweenTheGivenIndexes() {
        val subList = create(1, 2, 3, 4).subList(1, 3)

        assertFalse(subList.isEmpty())
        assertFailsWith<IndexOutOfBoundsException> { subList[-1] }
        assertEquals(2, subList[0])
        assertEquals(3, subList[1])
        assertFailsWith<IndexOutOfBoundsException> { subList[2] }
    }

    @Test
    fun subListShouldSupportContains() {
        val subList = create(1, 2, 3, 4).subList(2, 4)

        assertTrue(subList.contains(3))
        assertTrue(subList.containsAll(listOf(3, 4)))
        assertFalse(subList.contains(2))
        assertFalse(subList.containsAll(listOf(1, 3)))
    }

    @Test
    fun emptySubListShouldReturnEmptyList() {
        val subList = create(1, 2, 3, 4).subList(1, 1)

        assertTrue(subList.isEmpty())
        assertEquals(subList, createEmpty())
        assertSame(createEmpty(), subList)
    }
}
