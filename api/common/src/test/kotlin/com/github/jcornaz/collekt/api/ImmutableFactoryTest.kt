package com.github.jcornaz.collekt.api

import kotlin.test.*

class ImmutableFactoryTest {

    @Test
    fun emptyImmutableListShouldBeEmpty() {
        val list: ImmutableList<String> = emptyImmutableList()

        assertTrue(list.isEmpty())
        assertEquals(0, list.size)
        assertFailsWith<IndexOutOfBoundsException> { list[0] }
        list.forEach { _ -> fail() }
        assertEquals(emptyList<String>(), list)
    }

    @Test
    fun emptyImmutableSetShouldBeEmpty() {
        val set: ImmutableSet<String> = emptyImmutableSet()

        assertTrue(set.isEmpty())
        assertEquals(0, set.size)
        assertFalse("" in set)
        assertFailsWith<NoSuchElementException> { set.first() }
        set.forEach { _ -> fail() }
        assertEquals(emptySet<String>(), set)
    }

    @Test
    fun emptyImmutableMapShouldBeEmpty() {
        val map: ImmutableMap<Int, String> = emptyImmutableMap()

        assertTrue(map.isEmpty())
        assertEquals(0, map.size)
        assertNull(map[0])
        assertFalse(0 in map)
        assertFailsWith<NoSuchElementException> { map.entries.first() }
        map.forEach { _ -> fail() }
        assertEquals(emptyMap<Int, String>(), map)
    }

    @Test
    fun immutableListOfShouldContainsAllElements() {
        val list: ImmutableList<Int> = immutableListOf(1, 2, 10, 12)
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
        val set: ImmutableSet<Int> = immutableSetOf(1, 2, 10, 12)
        assertEquals(4, set.size)

        assertTrue(1 in set)
        assertTrue(2 in set)
        assertTrue(10 in set)
        assertTrue(12 in set)

        assertEquals(setOf(1, 2, 10, 12), set)
    }

    @Test
    fun immutableMapOfShouldContainsAllEntries() {
        val map: ImmutableMap<Int, String> = immutableMapOf(1 to "un", 2 to "deux", 10 to "dix", 12 to "douze")
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

    @Test
    fun toImmutableListShouldReturnEquivalentImmutableList() {
        val input = listOf(1, 2, 42, 24)
        val result: ImmutableList<Int> = input.toImmutableList()

        assertEquals(input, result)
        assertEquals(input.size, result.size)
        input.indices.forEach {
            assertEquals(input[it], result[it])
        }
    }

    @Test
    fun toImmutableListFromSequenceShouldReturnEquivalentImmutableList() {
        val input = listOf(1, 2, 42, 24)
        val result: ImmutableList<Int> = input.asSequence().toImmutableList()

        assertEquals(input, result)
        assertEquals(input.size, result.size)
        input.indices.forEach {
            assertEquals(input[it], result[it])
        }
    }

    @Test
    fun toImmutableSetShouldReturnEquivalentImmutableList() {
        val input = setOf(1, 2, 42, 24)
        val result: ImmutableSet<Int> = input.toImmutableSet()

        assertEquals(input, result)
        assertEquals(input.size, result.size)
        assertTrue(result.all { it in input })
        assertTrue(input.all { it in result })
    }

    @Test
    fun toImmutableSetFromSequenceShouldReturnEquivalentImmutableList() {
        val input = setOf(1, 2, 42, 24)
        val result: ImmutableSet<Int> = input.asSequence().toImmutableSet()

        assertEquals(input, result)
        assertEquals(input.size, result.size)
        assertTrue(result.all { it in input })
        assertTrue(input.all { it in result })
    }

    @Test
    fun toImmutableMapShouldReturnEquivalentImmutableList() {
        val input = mapOf(1 to "un", 2 to "deux", 10 to "dix", 12 to "douze")
        val result: ImmutableMap<Int, String> = input.toImmutableMap()

        assertEquals(input, result)
        assertEquals(input.size, result.size)
        assertTrue(result.all { it.key in input })
        assertTrue(input.all { it.key in result })
    }

    @Test
    fun toImmutableMapFromSequenceShouldReturnEquivalentImmutableList() {
        val input = mapOf(1 to "un", 2 to "deux", 10 to "dix", 12 to "douze")
        val result: ImmutableMap<Int, String> = input.asSequence().map { it.key to it.value }.toImmutableMap()

        assertEquals(input, result)
        assertEquals(input.size, result.size)
        assertTrue(result.all { it.key in input })
        assertTrue(result.all { it.value == input[it.key] })
        assertTrue(input.all { it.key in result })
        assertTrue(result.all { it.value == result[it.key] })
    }
}
