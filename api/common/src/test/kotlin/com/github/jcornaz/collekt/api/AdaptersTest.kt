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
        assertEquals(emptyList<String>().toString(), list.toString())
    }

    @Test
    fun emptyImmutableCollectionShouldBeEmpty() {
        val col: ImmutableCollection<String> = emptySet<String>().asImmutableCollection()

        assertTrue(col.isEmpty())
        assertEquals(0, col.size)
        assertFalse("" in col)
        assertFailsWith<NoSuchElementException> { col.first() }
        col.forEach { _ -> fail() }
        assertEquals(emptySet<String>().toString(), col.toString())
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
        assertEquals(emptySet<String>().toString(), set.toString())
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
        assertEquals(emptyMap<Int, String>().toString(), map.toString())
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
        assertEquals(input.toString(), result.toString())
    }

    @Test
    fun toImmutableCollectionShouldReturnEquivalentImmutableList() {
        val input = setOf(1, 2, 42, 24)
        val result: ImmutableCollection<Int> = input.asImmutableCollection()

        assertEquals(input.size, result.size)
        assertTrue(result.all { it in input })
        assertTrue(input.all { it in result })
        assertEquals(input.toString(), result.toString())
    }

    @Test
    fun toImmutableSetShouldReturnEquivalentImmutableList() {
        val input = setOf(1, 2, 42, 24)
        val result: ImmutableSet<Int> = input.asImmutableSet()

        assertEquals(input, result)
        assertEquals(input.size, result.size)
        assertTrue(result.all { it in input })
        assertTrue(input.all { it in result })
        assertEquals(input.toString(), result.toString())
    }

    @Test
    fun toImmutableMapShouldReturnEquivalentImmutableList() {
        val input = mapOf(1 to "un", 2 to "deux", 10 to "dix", 12 to "douze")
        val result: ImmutableMap<Int, String> = input.asImmutableMap()

        assertEquals(input, result)
        assertEquals(input.size, result.size)
        assertTrue(result.all { it.key in input })
        assertTrue(input.all { it.key in result })
        assertEquals(input.toString(), result.toString())
    }
}
