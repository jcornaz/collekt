package com.github.jcornaz.collekt.dexx

import com.github.andrewoma.dexx.collection.Set
import com.github.andrewoma.dexx.collection.Sets
import com.github.jcornaz.collekt.api.PersistentSet
import com.github.jcornaz.collekt.api.PersistentSetFactory

@Deprecated("Doesn't support null elements")
public class DexxSet<E>(private val set: Set<E>) : AbstractSet<E>(), PersistentSet<E> {
    companion object Factory : PersistentSetFactory {
        private val empty = DexxSet<Nothing>(Sets.of())

        override fun <E> empty(): PersistentSet<E> = empty

        override fun <E> from(elements: Iterable<E>): PersistentSet<E> {
            if (elements is DexxSet<E>) return elements

            val set = elements as? Set<E> ?: Sets.copyOf(elements)
            return if (set.isEmpty) empty else DexxSet(set)
        }
    }

    override val size: Int get() = set.size()

    override fun isEmpty(): Boolean = set.isEmpty

    override fun contains(element: E): Boolean = set.contains(element)

    override fun iterator(): Iterator<E> = set.iterator()

    override fun plus(element: E): PersistentSet<E> =
            wrap(set.add(element))

    override fun plus(elements: Iterable<E>): PersistentSet<E> =
            wrap(elements.fold(set) { acc, elt -> acc.add(elt) })

    override fun minus(element: E): PersistentSet<E> =
            wrap(set.remove(element))

    override fun minus(elements: Iterable<E>): PersistentSet<E> =
            wrap(elements.fold(set) { acc, elt -> acc.remove(elt) })

    private fun wrap(newSet: Set<E>): PersistentSet<E> = when {
        set === newSet -> this
        newSet.isEmpty -> empty
        else -> DexxSet(newSet)
    }
}
