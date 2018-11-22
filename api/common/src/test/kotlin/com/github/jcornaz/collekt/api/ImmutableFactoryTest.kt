package com.github.jcornaz.collekt.api

import kotlin.test.*

class ImmutableFactoryTest {

    @Test
    fun emptyImmutableListShouldBeEmpty() {
        assertTrue(emptyImmutableList<String>().isEmpty())
        assertEquals(0, emptyImmutableList<String>().size)
        assertFailsWith<IndexOutOfBoundsException> { emptyImmutableList<String>()[0] }
        emptyImmutableList<String>().forEach { _ -> fail() }
        assertEquals(emptyList<String>(), emptyImmutableList())
    }

    @Test
    fun emptyImmutableSetShouldBeEmpty() {
        assertTrue(emptyImmutableSet<String>().isEmpty())
        assertEquals(0, emptyImmutableSet<String>().size)
        assertFalse("" in emptyImmutableSet<String>())
        assertFailsWith<NoSuchElementException> { emptyImmutableSet<String>().first() }
        emptyImmutableSet<String>().forEach { _ -> fail() }
        assertEquals(emptySet<String>(), emptyImmutableSet())
    }

    @Test
    fun emptyImmutableMapShouldBeEmpty() {
        assertTrue(emptyImmutableMap<Int, String>().isEmpty())
        assertEquals(0, emptyImmutableMap<Int, String>().size)
        assertNull(emptyImmutableMap<Int, String>().get(0))
        assertFalse(0 in emptyImmutableMap<Int, String>())
        assertFailsWith<NoSuchElementException> { emptyImmutableMap<Int, String>().entries.first() }
        emptyImmutableMap<Int, String>().forEach { _ -> fail() }
        assertEquals(emptyMap<Int, String>(), emptyImmutableMap())
    }

    @Test
    fun immutableListOfShouldContainsAllElements() {
        val list = immutableListOf(1, 2, 10, 12)
        assertEquals(4, list.size)

        assertEquals(1, list[0])
        assertEquals(2, list[1])
        assertEquals(10, list[2])
        assertEquals(12, list[3])

        assertEquals(listOf(1, 2, 10, 12), list)

        assertEquals(2, list.indexOf(10))
        assertTrue(2 in list)
        assertFalse(3 in list)
    }

    @Test
    fun immutableSetOfShouldContainsAllElements() {
        val set = immutableSetOf(1, 2, 10, 12)
        assertEquals(4, set.size)

        assertTrue(1 in set)
        assertTrue(2 in set)
        assertTrue(10 in set)
        assertTrue(12 in set)

        assertEquals(setOf(1, 2, 10, 12), set)
    }

    @Test
    fun immutableMapOfShouldContainsAllEntries() {
        val map = immutableMapOf(1 to "un", 2 to "deux", 10 to "dix", 12 to "douze")
        assertEquals(4, map.size)

        assertEquals("un", map[1])
        assertEquals("deux", map[2])
        assertEquals("dix", map[10])
        assertEquals("douze", map[12])

        assertEquals(mapOf(1 to "un", 2 to "deux", 10 to "dix", 12 to "douze"), map)

        assertTrue(10 in map)
        assertFalse(3 in map)

        assertNull(map[3])
    }
}
