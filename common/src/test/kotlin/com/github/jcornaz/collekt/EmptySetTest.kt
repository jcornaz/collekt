package com.github.jcornaz.collekt

import kotlin.test.*


class EmptySetTest {

    @Test
    fun manyInvocationOfEmptyImmutableSetShouldReturnTheSameInstance() {
        assertSame<PersistentSet<Any>>(emptyPersistentSet<Int>(), emptyPersistentSet<String>())
    }

    @Test
    fun emptySetShouldBeEmpty() {
        assertTrue(emptyPersistentSet<Int>().isEmpty())
    }

    @Test
    fun emptySetSizeShouldBeZero() {
        assertEquals(0, emptyPersistentSet<Int>().size)
    }

    @Test
    fun emptySetShouldContainsNothing() {
        assertFalse(emptyPersistentSet<Int>().contains(0))
        assertFalse(emptyPersistentSet<Int>().containsAll(listOf(0, 1, 2)))
    }

    @Test
    fun emptySetShouldContainsEmptyList() {
        assertTrue(emptyPersistentSet<Int>().containsAll(emptyPersistentSet()))
    }

    @Test
    fun iteratorShouldReturnFalseForHasNextAndHasPrevious() {
        assertFalse(emptyPersistentSet<Int>().iterator().hasNext())
    }

    @Test
    fun emptyImmutableSetShouldEqualAnyOtherEmptyList() {
        assertEquals<Set<Int>>(emptySet(), emptyPersistentSet())
        assertEquals<Set<Int>>(HashSet(), emptyPersistentSet())
    }

    @Test
    fun hashCodeShouldBeConsistentWithStdList() {
        assertEquals(emptySet<Int>().hashCode(), emptyPersistentSet<Int>().hashCode())
    }

    @Test
    fun shouldReturnComprehensiveString() {
        assertEquals(emptySet<Int>().toString(), emptyPersistentSet<Int>().toString())
    }
}
