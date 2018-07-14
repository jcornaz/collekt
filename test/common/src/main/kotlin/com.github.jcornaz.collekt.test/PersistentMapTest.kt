package com.github.jcornaz.collekt.test

import com.github.jcornaz.collekt.api.*
import kotlin.test.*

abstract class PersistentMapTest : PersistentSetTest() {
    abstract val mapFactory: PersistentMapFactory

    final override val factory: PersistentSetFactory = object : PersistentSetFactory {
        private val empty by lazy { SetFromMap(mapFactory.empty<Nothing, Unit>()) }

        override fun <E> empty(): PersistentSet<E> = empty

        override fun <E> from(elements: Iterable<E>): PersistentSet<E> =
                SetFromMap(mapFactory.from(elements.associate { it to Unit }))
    }

    @Test
    fun shouldReturnsValueAssocitedWithTheKey() {
        val map = mapFactory.of(
                1 to "one",
                2 to "two",
                3 to "three"
        )

        assertEquals("one", map[1])
        assertEquals("two", map[2])
        assertEquals("three", map[3])
    }

    @Test
    fun shouldNotContiansOtherValues() {
        val map = mapFactory.of(
                1 to "one",
                2 to "two",
                3 to "three"
        )

        assertFalse(4 in map)
        assertFalse(map.containsValue("four"))
    }

    @Test
    fun shouldAddEntry() {
        val map = mapFactory.of(
                1 to "one",
                2 to "two",
                3 to "three"
        )

        val result = map + (4 to "four")

        assertEquals(4, result.size)
        assertTrue(4 in result)
        assertTrue(result.containsValue("four"))
        assertEquals("four", result[4])
    }

    @Test
    fun shouldReplaceEntry() {
        val map = mapFactory.of(
                1 to "one",
                2 to "two",
                3 to "three"
        )

        val result = map + (2 to "four")

        assertEquals(3, result.size)
        assertTrue(2 in result)
        assertTrue(result.containsValue("four"))
        assertEquals("four", result[2])
    }

    @Test
    fun shouldLatestValueShouldOverrideThePreviousInCaseOfDuplicateKeys() {
        val map = mapFactory.of(
                1 to "one",
                2 to "two",
                3 to "three",
                2 to "four"
        )

        assertEquals(3, map.size)

        assertEquals("one", map[1])
        assertEquals("four", map[2])
        assertEquals("three", map[3])
    }

    @Test
    fun shouldEqualsEquivalentMap() {
        val map1 = mapFactory.of(
                1 to "one",
                2 to "two",
                3 to "three"
        )
        val map2 = mapFactory.of(
                1 to "one",
                2 to "two",
                3 to "three"
        )
        val map3 = mapOf(
                1 to "one",
                2 to "two",
                3 to "three"
        )

        assertEquals(map1, map2)
        assertEquals(map2, map3)
        assertEquals(map1, map3)
        assertEquals(emptyMap<String, String>(), mapFactory.empty())
    }

    @Test
    fun shouldHaveConsistentHashCode() {
        val map1 = mapFactory.of(
                1 to "one",
                2 to "two",
                3 to "three"
        )
        val map2 = mapFactory.of(
                2 to "two",
                1 to "one",
                3 to "three"
        )
        val map3 = mapOf(
                2 to "two",
                3 to "three",
                1 to "one"
        )

        assertEquals(map1.hashCode(), map2.hashCode())
        assertEquals(map2.hashCode(), map3.hashCode())
        assertEquals(map1.hashCode(), map3.hashCode())
        assertEquals(emptyMap<String, String>().hashCode(), mapFactory.empty<String, String>().hashCode())
    }

    @Test
    fun shouldNotEqualsDiffrentMap() {

        val map1 = mapFactory.of(
                1 to "one",
                2 to "two",
                3 to "three"
        )
        val map2 = mapFactory.of(
                1 to "one",
                2 to "one",
                3 to "three"
        )
        val map3 = mapOf(
                1 to "one",
                3 to "three"
        )

        assertNotEquals(map1, map2)
        assertNotEquals(map1, map3)
        assertNotEquals(map2, map3)
        assertNotEquals(map1, mapFactory.empty())
        assertNotEquals(map2, mapFactory.empty())
        assertNotEquals(map3, mapFactory.empty())
    }
}
