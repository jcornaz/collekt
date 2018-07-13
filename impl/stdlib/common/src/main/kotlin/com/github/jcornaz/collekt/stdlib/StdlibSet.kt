package com.github.jcornaz.collekt.stdlib

import com.github.jcornaz.collekt.api.PersistentSet
import com.github.jcornaz.collekt.api.PersistentSetFactory

class StdlibSet<E>(private val set: Set<E>) : AbstractSet<E>(), PersistentSet<E> {

    companion object Factory : PersistentSetFactory {
        private val empty = StdlibSet<Nothing>(emptySet())

        override fun <E> empty(): PersistentSet<E> = empty

        override fun <E> from(elements: Iterable<E>): PersistentSet<E> {
            val set = elements.toHashSet()

            return if (set.isEmpty()) empty else StdlibSet(set)
        }

    }

    override val size: Int get() = set.size

    override fun isEmpty(): Boolean = set.isEmpty()

    override fun contains(element: E): Boolean = set.contains(element)
    override fun containsAll(elements: Collection<E>): Boolean = set.containsAll(elements)

    override fun iterator(): Iterator<E> = set.iterator()

    override fun plus(element: E): PersistentSet<E> =
            wrap(set + element)

    override fun plus(elements: Iterable<E>): PersistentSet<E> =
            wrap(set + elements)

    override fun minus(element: E): PersistentSet<E> =
            wrap(set - element)

    override fun minus(elements: Iterable<E>): PersistentSet<E> =
            wrap(set - elements)

    private fun wrap(newSet: Set<E>): PersistentSet<E> = when {
        newSet === set -> this
        newSet.isEmpty() -> empty
        else -> StdlibSet(newSet)
    }
}