package com.github.jcornaz.collekt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class StandardCollectionAdapterTest {

    @Test
    fun equivalentListShouldBeEqualsStdList() {
        assertEquals(persistentListOf(1, 2, 3, 4).asList(), listOf(1, 2, 3, 4))
        assertEquals(persistentListOf(1).asList(), listOf(1))
        assertEquals(persistentListOf<Int>().asList(), listOf())
        assertEquals(emptyPersistentList<Int>().asList(), emptyList())
    }

    @Test
    fun differentListShouldNotEqualsStdList() {
        assertNotEquals(persistentListOf(1, 2, 3, 4).asList(), listOf(4, 3, 2, 1))
        assertNotEquals(persistentListOf(1, 2, 2, 3).asList(), listOf(1, 2, 3))
        assertNotEquals(persistentListOf(1, 2, 3).asList(), listOf(1, 2, 3, 3))
        assertNotEquals(persistentListOf(1, 2, 3, 4).asList(), listOf(1, 2, 4))
        assertNotEquals(persistentListOf(1, 2, 3, 4).asList(), emptyList())
        assertNotEquals(emptyPersistentList<Int>().asList(), listOf(1))
    }

    @Test
    fun hashCodeShouldBeConsistentWithStdList() {
        assertEquals(listOf(1, 2, 3, 4).hashCode(), persistentListOf(1, 2, 3, 4).asList().hashCode())
        assertEquals(emptyList<Int>().hashCode(), emptyPersistentList<Int>().asList().hashCode())
    }
}
