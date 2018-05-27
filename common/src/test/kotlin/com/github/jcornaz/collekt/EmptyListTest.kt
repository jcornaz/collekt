package com.github.jcornaz.collekt

import kotlin.test.*


class EmptyListTest {

    @Test
    fun manyInvocationOfEmptyImmutableListShouldReturnTheSameInstance() {
        assertSame<ImmutableList<Any>>(emptyImmutableList<Int>(), emptyImmutableList<String>())
    }

    @Test
    fun emptyListShouldBeEmpty() {
        assertTrue(emptyImmutableList<Int>().isEmpty())
    }

    @Test
    fun emptyListSizeShouldBeZero() {
        assertEquals(0, emptyImmutableList<Int>().size)
    }

    @Test
    fun emptyListShouldContainsNothing() {
        assertFalse(emptyImmutableList<Int>().contains(0))
        assertFalse(emptyImmutableList<Int>().containsAll(listOf(0, 1, 2)))
    }

    @Test
    fun emptyListShouldContainsEmptyList() {
        assertTrue(emptyImmutableList<Int>().containsAll(emptyImmutableList()))
    }

    @Test
    fun getShouldThrowIndexOutOfBoundException() {
        assertFailsWith<IndexOutOfBoundsException> { emptyImmutableList<Int>()[0] }
    }

    @Test
    fun iteratorShouldReturnFalseForHasNextAndHasPrevious() {
        val list = emptyImmutableList<Int>()

        assertFalse(list.iterator().hasNext())
        assertFalse(list.listIterator().hasNext())
        assertFalse(list.listIterator().hasPrevious())
        assertFalse(list.listIterator(0).hasNext())
        assertFalse(list.listIterator(0).hasPrevious())
    }

    @Test
    fun listIteratorAtIndexShouldThrowForIndexDifferentThanZero() {
        assertFailsWith<IndexOutOfBoundsException> { emptyImmutableList<Int>().listIterator(1) }
        assertFailsWith<IndexOutOfBoundsException> { emptyImmutableList<Int>().listIterator(-1) }
    }

    @Test
    fun subListShouldReturnSameInstance() {
        assertSame(emptyImmutableList(), emptyImmutableList<Int>().subList(0, 0))
    }

    @Test
    fun subListShouldThrowIndexOutOfBoundExceptionForBadIndexes() {
        assertFailsWith<IndexOutOfBoundsException> { emptyImmutableList<Int>().subList(0, 2) }
        assertFailsWith<IndexOutOfBoundsException> { emptyImmutableList<Int>().subList(2, 2) }
        assertFailsWith<IndexOutOfBoundsException> { emptyImmutableList<Int>().subList(-1, 0) }
        assertFailsWith<IndexOutOfBoundsException> { emptyImmutableList<Int>().subList(-1, 1) }
    }

    @Test
    fun emptyImmutableListShouldEqualAnyOtherEmptyList() {
        assertEquals<List<Int>>(emptyList(), emptyImmutableList())
        assertEquals<List<Int>>(ArrayList(), emptyImmutableList())
    }
}
