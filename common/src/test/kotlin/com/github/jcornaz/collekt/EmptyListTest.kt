package com.github.jcornaz.collekt

import kotlin.test.*


class EmptyListTest {

    @Test
    fun manyInvocationOfEmptyImmutableListShouldReturnTheSameInstance() {
        assertSame<PersistentList<Any>>(emptyPersistentList<Int>(), emptyPersistentList<String>())
    }

    @Test
    fun emptyListShouldBeEmpty() {
        assertTrue(emptyPersistentList<Int>().isEmpty())
    }

    @Test
    fun emptyListSizeShouldBeZero() {
        assertEquals(0, emptyPersistentList<Int>().size)
    }

    @Test
    fun emptyListShouldContainsNothing() {
        assertFalse(emptyPersistentList<Int>().contains(0))
        assertFalse(emptyPersistentList<Int>().containsAll(listOf(0, 1, 2)))
    }

    @Test
    fun emptyListShouldContainsEmptyList() {
        assertTrue(emptyPersistentList<Int>().containsAll(emptyPersistentList()))
    }

    @Test
    fun getShouldThrowIndexOutOfBoundException() {
        assertFailsWith<IndexOutOfBoundsException> { emptyPersistentList<Int>()[0] }
    }

    @Test
    fun iteratorShouldReturnFalseForHasNextAndHasPrevious() {
        val list = emptyPersistentList<Int>()

        assertFalse(list.iterator().hasNext())
        assertFalse(list.listIterator().hasNext())
        assertFalse(list.listIterator().hasPrevious())
        assertFalse(list.listIterator(0).hasNext())
        assertFalse(list.listIterator(0).hasPrevious())
    }

    @Test
    fun listIteratorAtIndexShouldThrowForIndexDifferentThanZero() {
        assertFailsWith<IndexOutOfBoundsException> { emptyPersistentList<Int>().listIterator(1) }
        assertFailsWith<IndexOutOfBoundsException> { emptyPersistentList<Int>().listIterator(-1) }
    }

    @Test
    fun subListShouldReturnSameInstance() {
        assertSame(emptyPersistentList(), emptyPersistentList<Int>().subList(0, 0))
    }

    @Test
    fun subListShouldThrowIndexOutOfBoundExceptionForBadIndexes() {
        assertFailsWith<IndexOutOfBoundsException> { emptyPersistentList<Int>().subList(0, 2) }
        assertFailsWith<IndexOutOfBoundsException> { emptyPersistentList<Int>().subList(2, 2) }
        assertFailsWith<IndexOutOfBoundsException> { emptyPersistentList<Int>().subList(-1, 0) }
        assertFailsWith<IndexOutOfBoundsException> { emptyPersistentList<Int>().subList(-1, 1) }
    }

    @Test
    fun emptyImmutableListShouldEqualAnyOtherEmptyList() {
        assertEquals<List<Int>>(emptyList(), emptyPersistentList())
        assertEquals<List<Int>>(ArrayList(), emptyPersistentList())
    }

    @Test
    fun hashCodeShouldBeConsistentWithStdList() {
        assertEquals(emptyList<Int>().hashCode(), emptyPersistentList<Int>().hashCode())
    }

    @Test
    fun shouldReturnComprehensiveString() {
        assertEquals("[]", emptyPersistentList<Int>().toString())
        assertEquals(emptyList<Int>().toString(), emptyPersistentList<Int>().toString())
    }
}
