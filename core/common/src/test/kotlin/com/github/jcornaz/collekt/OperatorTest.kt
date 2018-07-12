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

        assertFalse(2 in result)
        assertTrue(2 in result)
        assertFalse(3 in result)
        assertTrue(4 in result)

        assertEquals(2, result.size)
    }
}

class FilterNotNullTest : PersistentOperatorTest() {

    @Suppress("UNCHECKED_CAST")
    override fun <E> ImmutableCollection<E>.applyOperator(): PersistentCollection<E> =
            filterNotNull<Any>() as PersistentCollection<E>


    @Test
    fun testFilteNotNull() {
        val result: PersistentList<String> = persistentListOf("hello", null, "world", null).filterNotNull()

        assertFalse(null in (result as PersistentList<String?>))
        assertTrue("hello" in result)
        assertTrue("world" in result)
        assertEquals(2, result.size)
    }
}

class FilterIsInstance : PersistentOperatorTest() {
    override fun <E> ImmutableCollection<E>.applyOperator(): PersistentCollection<E> =
            filterIsInstance()

    @Test
    fun testFilterIsInstance() {
        val result: PersistentList<Double> = persistentListOf<Number>(1, 2, 3, 4.0, 5.0, 6.0).filterIsInstance()

        assertFalse(1 in (result as PersistentList<Number>))
        assertFalse(2 in (result as PersistentList<Number>))
        assertFalse(3 in (result as PersistentList<Number>))
        assertTrue(4.0 in result)
        assertTrue(5.0 in result)
        assertTrue(6.0 in result)

        assertEquals(3, result.size)
    }
}