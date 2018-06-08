package com.github.jcornaz.collekt

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BooleanQueryTest {

    @Test
    fun anyShouldReturnTrueIfOneElementMatches() {
        assertTrue(persistentListOf(0, 1, 2).any { it == 2 })
    }

    @Test
    fun anyShouldReturnTrueIfManyElementsMatches() {
        assertTrue(persistentListOf(1, 1, 1).any { it == 1 })
    }

    @Test
    fun anyShouldReturnFalseIfNoElementMatches() {
        assertFalse(persistentListOf(0, 1, 2).any { it == 42 })
    }

    @Test
    fun anyWithoutArgumentShouldReturnTrueForNonEmptyList() {
        assertTrue(persistentListOf(0).any())
    }

    @Test
    fun anyWithoutArgumentShouldReturnFalseForEmptyList() {
        assertFalse(emptyPersistentList<Int>().any())
    }

    @Test
    fun noneShouldReturnFalseIfOneElementMatches() {
        assertFalse(persistentListOf(0, 1, 2).none { it == 2 })
    }

    @Test
    fun noneShouldReturnFalseIfManyElementsMatches() {
        assertFalse(persistentListOf(1, 1, 1).none { it == 1 })
    }

    @Test
    fun noneShouldReturnTrueIfNoElementMatches() {
        assertTrue(persistentListOf(0, 1, 2).none { it == 42 })
    }

    @Test
    fun noneWithoutArgumentShouldReturnFalseForNonEmptyList() {
        assertFalse(persistentListOf(0).none())
    }

    @Test
    fun noneWithoutArgumentShouldReturnTrueForEmptyList() {
        assertTrue(emptyPersistentList<Int>().none())
    }

    @Test
    fun allShouldReturnFalseIfOneElementDoesNotMatch() {
        assertFalse(persistentListOf(1, 1, 1, 0, 1).all { it == 1 })
    }

    @Test
    fun allShouldReturnTrueIfAllElementsMatch() {
        assertTrue(persistentListOf(1, 1, 1, 1, 1).all { it == 1 })
    }

    @Test
    fun allShouldReturnTrueForEmptyList() {
        assertTrue(emptyPersistentList<Int>().all { false })
    }
}
