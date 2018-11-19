package com.github.jcornaz.collekt.paguro

import com.github.jcornaz.collekt.api.PersistentSet
import com.github.jcornaz.collekt.api.PersistentSetFactory
import org.organicdesign.fp.collections.ImSet
import org.organicdesign.fp.collections.PersistentHashSet

public class PaguroHashSet<E>(private val set: ImSet<E>) : AbstractSet<E>(), PersistentSet<E> {

    companion object Factory : PersistentSetFactory {
        private val empty = PaguroHashSet<Nothing>(PersistentHashSet.empty())

        override fun <E> empty(): PersistentSet<E> = empty

        override fun <E> from(elements: Iterable<E>): PersistentSet<E> {
            if (elements is PaguroHashSet<E>) return elements

            val set = elements as? PersistentHashSet<E> ?: PersistentHashSet.of(elements)
            return if (set.isEmpty()) empty else PaguroHashSet(set)
        }
    }

    override val size: Int get() = set.size

    override fun isEmpty(): Boolean = set.isEmpty()

    override fun contains(element: E): Boolean = set.contains(element)
    override fun containsAll(elements: Collection<E>): Boolean = set.containsAll(elements)

    override fun iterator(): Iterator<E> = set.iterator()

    override fun plus(element: E): PersistentSet<E> =
            wrap(set.put(element))

    override fun plus(elements: Iterable<E>): PersistentSet<E> =
            wrap(set.union(elements))

    override fun minus(element: E): PersistentSet<E> =
            wrap(set.without(element))

    override fun minus(elements: Iterable<E>): PersistentSet<E> =
            wrap(elements.fold(set) { acc, elt -> acc.without(elt) })

    override fun empty(): PersistentSet<E> = empty

    private fun wrap(newSet: ImSet<E>): PersistentSet<E> = when {
        set === newSet -> this
        newSet.isEmpty() -> empty
        else -> PaguroHashSet(newSet)
    }
}