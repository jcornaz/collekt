package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.*
import org.pcollections.TreePVector

class PCollectionVector<E>(private val vector: TreePVector<E>) : AbstractPersistentList<E>() {
    override val size get() = vector.size
    override val isEmpty get() = vector.isEmpty()

    companion object Factory : PersistentListFactory {
        private val empty = PCollectionVector(TreePVector.empty<Nothing>())

        override fun <E> empty(): PersistentList<E> = empty

        override fun <E> from(elements: Iterable<E>): PersistentList<E> {
            val vector = (elements as? Collection<E>)
                    ?.let { TreePVector.from(it) }
                    ?: elements.fold(TreePVector.empty<E>()) { acc, elt -> acc.plus(elt) }

            return wrap(vector)
        }

        private fun <E> wrap(vector: TreePVector<E>): PersistentList<E> =
                vector.takeUnless(TreePVector<E>::isEmpty)?.let(::PCollectionVector) ?: empty
    }

    override fun get(index: Int): E = vector[index]

    override fun iterator(index: Int): ListIterator<E> = vector.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): PersistentList<E> =
            wrap(vector.subList(fromIndex, toIndex))

    override fun split(index: Int): Pair<ImmutableList<E>, PersistentList<E>> =
            subList(0, index) to subList(index, vector.size)

    override fun plus(element: E): PersistentList<E> =
            wrap(vector.plus(element))

    override fun plus(elements: Traversable<E>): PersistentList<E> = when {
        elements.none() -> this
        elements is PCollectionVector<E> -> wrap(vector.plusAll(elements.vector))
        elements is ImmutableCollection<E> -> wrap(vector.plusAll(elements.asCollection()))
        else -> wrap(elements.fold(vector) { acc, elt -> acc + elt })
    }

    override fun plus(index: Int, element: E): PersistentList<E> =
            wrap(vector.plus(index, element))

    override fun plus(index: Int, elements: Traversable<E>): PersistentList<E> = when {
        elements.none() -> this
        elements is PCollectionVector<E> -> wrap(vector.plusAll(index, elements.vector))
        elements is ImmutableCollection<E> -> wrap(vector.plusAll(index, elements.asCollection()))
        else -> wrap(elements.fold(vector.subList(0, index)) { acc, elt -> acc + elt }.plusAll(vector.subList(index, vector.size)))
    }

    override fun minus(element: E): PersistentList<E> =
            wrap(vector.minus(element))

    override fun minus(elements: Traversable<E>): PersistentList<E> = when {
        elements.none() -> this
        elements is PCollectionVector<E> -> wrap(vector.minusAll(elements.vector))
        elements is ImmutableCollection<E> -> wrap(vector.minusAll(elements.asCollection()))
        else -> wrap(elements.fold(vector) { acc, elt -> acc.minus(elt) })
    }

    override fun minusIndex(index: Int): PersistentList<E> =
            wrap(vector.minus(index))
}
