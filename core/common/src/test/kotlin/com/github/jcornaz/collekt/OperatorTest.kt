package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.ImmutableCollection
import com.github.jcornaz.collekt.api.PersistentCollection
import com.github.jcornaz.collekt.api.PersistentCollectionFactory
import com.github.jcornaz.collekt.api.PersistentList
import com.github.jcornaz.collekt.test.PersistentCollectionTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

abstract class PersistentOperatorTest : PersistentCollectionTest() {

    abstract fun <E> ImmutableCollection<E>.applyOperator(): PersistentCollection<E>

    final override val factory = object : PersistentCollectionFactory {
        override fun <E> empty(): PersistentCollection<E> =
                emptyPersistentList<E>().applyOperator()

        override fun <E> from(elements: Iterable<E>): PersistentCollection<E> =
                elements.toPersistentList().applyOperator()
    }

    @Test
    fun emptyCollectionShouldStayEmpty() {
        val result = emptyPersistentList<String>().applyOperator()

        assertTrue(result.isEmpty())
        assertEquals(0, result.size)
        assertFalse(result.iterator().hasNext())
    }
}

class FilterTest : PersistentOperatorTest() {
    override fun <E> ImmutableCollection<E>.applyOperator(): PersistentCollection<E> =
            filter { true }

    @Test
    fun testFilter() {
        val result: PersistentList<Int> = persistentListOf(1, 2, 3, 4).filter { it.rem(2) == 0 }

        assertEquals(listOf(2, 4), result)
    }
}

class FilterNotNullTest : PersistentOperatorTest() {

    @Suppress("UNCHECKED_CAST")
    override fun <E> ImmutableCollection<E>.applyOperator(): PersistentCollection<E> =
            filterNotNull<Any>() as PersistentCollection<E>


    @Test
    fun testFilteNotNull() {
        val result: PersistentList<String> = persistentListOf("hello", null, "world", null).filterNotNull()

        assertEquals(listOf("hello", "world"), result)
    }
}


class FilterIsInstance {

    @Test
    fun testFilterIsInstance() {
        val result: PersistentList<Double> = persistentListOf<Number>(1, 2, 3, 4.0, 5.0, 6.0).filterIsInstance()

        assertEquals(listOf(4.0, 5.0, 6.0), result)
    }
}


class MapTest : PersistentOperatorTest() {
    override fun <E> ImmutableCollection<E>.applyOperator(): PersistentCollection<E> = map { it }

    @Test
    fun testMap() {
        val result = persistentListOf(1, 2, 3).map { it * 2 }.map { it.toString() }

        assertEquals(listOf("2", "4", "6"), result)
    }
}

class MapNotNullTest : PersistentOperatorTest() {

    @Suppress("UNCHECKED_CAST")
    override fun <E> ImmutableCollection<E>.applyOperator(): PersistentCollection<E> =
            mapNotNull<E, Any> { it } as PersistentList<E>

    @Test
    fun testMapNotNull() {
        val result = persistentListOf(1, 2, 3, 4).mapNotNull {
            if (it.rem(2) == 0) null else it.toString()
        }

        assertEquals(listOf("1", "3"), result)
    }
}

class FlatMapTest : PersistentOperatorTest() {
    override fun <E> ImmutableCollection<E>.applyOperator(): PersistentCollection<E> =
            flatMap { listOf(it) }

    @Test
    fun testFlatMap() {
        val result = persistentListOf(10, 20, 30).flatMap { x -> persistentListOf(x + 1, x + 2, x + 3) }

        assertEquals(listOf(11, 12, 13, 21, 22, 23, 31, 32, 33), result)
    }
}
