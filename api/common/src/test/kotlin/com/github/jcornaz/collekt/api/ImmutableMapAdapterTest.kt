package com.github.jcornaz.collekt.api

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ImmutableMapAdapterTest {

    @Test
    fun shouldEqualEquivalentMap() {
        val map = mapOf(1 to "un", 2 to "deux")

        assertEquals(mapOf(1 to "un", 2 to "deux"), map.asImmutableMap())
        assertEquals(map.asImmutableMap(), mapOf(1 to "un", 2 to "deux"))
        assertEquals(emptyMap<Int, String>(), emptyMap<Int, String>().asImmutableMap())
        assertEquals(emptyMap<Int, String>().asImmutableMap(), emptyMap<Int, String>())
    }

    @Test
    fun hashCodeShouldEqualEquivalentMap() {
        val map = mapOf(1 to "un", 2 to "deux")

        assertEquals(mapOf(1 to "un", 2 to "deux").hashCode(), map.asImmutableMap().hashCode())
        assertEquals(map.asImmutableMap().hashCode(), mapOf(1 to "un", 2 to "deux").hashCode())
        assertEquals(emptyMap<Int, String>().hashCode(), emptyMap<Int, String>().asImmutableMap().hashCode())
        assertEquals(emptyMap<Int, String>().asImmutableMap().hashCode(), emptyMap<Int, String>().hashCode())
    }

    @Test
    fun shouldNotEqualDifferentMap() {
        val immutable = mapOf(1 to "un", 2 to "deux").asImmutableMap()

        val maps = listOf(
                emptyMap(),
                mapOf(1 to "un", 2 to "trois"),
                mapOf(1 to "un", 3 to "deux"),
                mapOf(1 to "un")
        )

        maps.forEach {
            assertNotEquals(it, immutable)
            assertNotEquals(immutable, it)
        }
    }
}
