package com.github.jcornaz.collekt.api

import kotlin.test.*

class AdaptersTest {

    @Test
    fun emptyImmutableListShouldBeEmpty() {
        val list: ImmutableList<String> = emptyList<String>().asImmutableList()

        assertTrue(list.isEmpty())
        assertEquals(0, list.size)
        assertFailsWith<IndexOutOfBoundsException> { list[0] }
        list.forEach { _ -> fail() }
        assertEquals(emptyList<String>(), list)
    }

    @Test
    fun emptyImmutableCollectionShouldBeEmpty() {
        val set: ImmutableCollection<String> = emptySet<String>().asImmutableCollection()

        assertTrue(set.isEmpty())
        assertEquals(0, set.size)
        assertFalse("" in set)
        assertFailsWith<NoSuchElementException> { set.first() }
        set.forEach { _ -> fail() }
    }

    @Test
    fun emptyImmutableSetShouldBeEmpty() {
        val set: ImmutableSet<String> = emptySet<String>().asImmutableSet()

        assertTrue(set.isEmpty())
        assertEquals(0, set.size)
        assertFalse("" in set)
        assertFailsWith<NoSuchElementException> { set.first() }
        set.forEach { _ -> fail() }
        assertEquals(emptySet<String>(), set)
    }

    @Test
    fun emptyImmutableMapShouldBeEmpty() {
        val map: ImmutableMap<Int, String> = emptyMap<Int, String>().asImmutableMap()

        assertTrue(map.isEmpty())
        assertEquals(0, map.size)
        assertNull(map[0])
        assertFalse(0 in map)
        assertFailsWith<NoSuchElementException> { map.entries.first() }
        map.forEach { _ -> fail() }
        assertEquals(emptyMap<Int, String>(), map)
    }

    @Test
    fun toImmutableListShouldReturnEquivalentImmutableList() {
        val input = listOf(1, 2, 42, 24)
        val result: ImmutableList<Int> = input.asImmutableList()

        assertEquals(input, result)
        assertEquals(input.size, result.size)
        input.indices.forEach {
            assertEquals(input[it], result[it])
        }
    }

    @Test
    fun toImmutableSetShouldReturnEquivalentImmutableList() {
        val input = setOf(1, 2, 42, 24)
        val result: ImmutableSet<Int> = input.asImmutableSet()

        assertEquals(input, result)
        assertEquals(input.size, result.size)
        assertTrue(result.all { it in input })
        assertTrue(input.all { it in result })
    }

    @Test
    fun toImmutableMapShouldReturnEquivalentImmutableList() {
        val input = mapOf(1 to "un", 2 to "deux", 10 to "dix", 12 to "douze")
        val result: ImmutableMap<Int, String> = input.asImmutableMap()

        assertEquals(input, result)
        assertEquals(input.size, result.size)
        assertTrue(result.all { it.key in input })
        assertTrue(input.all { it.key in result })
    }
}
