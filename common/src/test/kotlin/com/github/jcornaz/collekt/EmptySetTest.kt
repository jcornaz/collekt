package com.github.jcornaz.collekt

import kotlin.test.*


class EmptySetTest {

    @Test
    fun manyInvocationOfEmptyImmutableSetShouldReturnTheSameInstance() {
        assertSame<ImmutableSet<Any>>(emptyImmutableSet<Int>(), emptyImmutableSet<String>())
    }

    @Test
    fun emptySetShouldBeEmpty() {
        assertTrue(emptyImmutableSet<Int>().isEmpty())
    }

    @Test
    fun emptySetSizeShouldBeZero() {
        assertEquals(0, emptyImmutableSet<Int>().size)
    }

    @Test
    fun emptySetShouldContainsNothing() {
        assertFalse(emptyImmutableSet<Int>().contains(0))
        assertFalse(emptyImmutableSet<Int>().containsAll(listOf(0, 1, 2)))
    }

    @Test
    fun emptySetShouldContainsEmptyList() {
        assertTrue(emptyImmutableSet<Int>().containsAll(emptyImmutableSet()))
    }

    @Test
    fun iteratorShouldReturnFalseForHasNextAndHasPrevious() {
        assertFalse(emptyImmutableSet<Int>().iterator().hasNext())
    }

    @Test
    fun emptyImmutableSetShouldEqualAnyOtherEmptyList() {
        assertEquals<Set<Int>>(emptySet(), emptyImmutableSet())
        assertEquals<Set<Int>>(HashSet(), emptyImmutableSet())
    }

    @Test
    fun hashCodeShouldBeConsistentWithStdList() {
        assertEquals(emptySet<Int>().hashCode(), emptyImmutableSet<Int>().hashCode())
    }

    @Test
    fun shouldReturnComprehensiveString() {
        assertEquals(emptySet<Int>().toString(), emptyImmutableSet<Int>().toString())
    }
}
