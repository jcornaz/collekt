package com.github.jcornaz.collekt.api

import kotlin.test.*

class ImmutableFactoryTest {

    @Test
    fun emptyImmutableListShouldBeEmpty() {
        assertTrue(emptyImmutableList<String>().isEmpty())
        assertEquals(0, emptyImmutableList<String>().size)
        assertFailsWith<IndexOutOfBoundsException> { emptyImmutableList<String>()[0] }
        emptyImmutableList<String>().forEach { _ -> fail() }
    }

    @Test
    fun emptyImmutableSetShouldBeEmpty() {
        assertTrue(emptyImmutableSet<String>().isEmpty())
        assertEquals(0, emptyImmutableSet<String>().size)
        assertFalse("" in emptyImmutableSet<String>())
        assertFailsWith<NoSuchElementException> { emptyImmutableSet<String>().first() }
        emptyImmutableSet<String>().forEach { _ -> fail() }
    }

    @Test
    fun emptyImmutableMapShouldBeEmpty() {
        assertTrue(emptyImmutableMap<Int, String>().isEmpty())
        assertEquals(0, emptyImmutableMap<Int, String>().size)
        assertNull(emptyImmutableMap<Int, String>().get(0))
        assertFalse(0 in emptyImmutableMap<Int, String>())
        assertFailsWith<NoSuchElementException> { emptyImmutableMap<Int, String>().entries.first() }
        emptyImmutableMap<Int, String>().forEach { _ -> fail() }
    }
}
