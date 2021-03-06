package com.github.jcornaz.collekt.stdlib

import com.github.jcornaz.collekt.api.PersistentSet
import com.github.jcornaz.collekt.api.PersistentSetFactory

/**
 * Naive implementation using kotlin standard lists and operators.
 *
 * This is the worse persistent implementation possible, and performances of all mutation method are expected to be really bad.
 *
 * It is only useful for comparison purposes with other persistent implementations
 */
public class StdlibSet<out E> internal constructor(private val set: Set<E>) : AbstractSet<E>(), PersistentSet<E> {

    companion object Factory : PersistentSetFactory {
        private val empty = StdlibSet<Nothing>(emptySet())

        override fun <E> empty(): PersistentSet<E> = empty

        override fun <E> from(elements: Iterable<E>): PersistentSet<E> {
            if (elements is StdlibSet<E>) return elements

            val set = elements.toHashSet()

            return if (set.isEmpty()) empty else StdlibSet(set)
        }

    }

    override val size: Int get() = set.size

    override fun isEmpty(): Boolean = set.isEmpty()

    override fun contains(element: @UnsafeVariance E): Boolean = set.contains(element)
    override fun containsAll(elements: Collection<@UnsafeVariance E>): Boolean = set.containsAll(elements)

    override fun iterator(): Iterator<E> = set.iterator()

    override fun plus(element: @UnsafeVariance E): PersistentSet<E> =
            wrap(set + element)

    override fun plus(elements: Iterable<@UnsafeVariance E>): PersistentSet<E> =
            wrap(set + elements)

    override fun minus(element: @UnsafeVariance E): PersistentSet<E> =
            wrap(set - element)

    override fun minus(elements: Iterable<@UnsafeVariance E>): PersistentSet<E> =
            wrap(set - elements)

    override fun empty(): PersistentSet<E> = empty

    private fun wrap(newSet: Set<E>): PersistentSet<E> = when {
        newSet === set -> this
        newSet.isEmpty() -> empty
        else -> StdlibSet(newSet)
    }
}