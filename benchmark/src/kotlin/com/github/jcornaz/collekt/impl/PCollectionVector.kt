package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.PersistentList
import com.github.jcornaz.collekt.PersistentListFactory
import org.pcollections.PVector
import org.pcollections.TreePVector

class PCollectionVector<E>(private val vector: PVector<E>) : AbstractList<E>(), PersistentList<E> {
    companion object Factory : PersistentListFactory {
        private val empty = PCollectionVector(TreePVector.empty<Nothing>())

        override fun <E> empty(): PersistentList<E> = empty

        override fun <E> from(elements: Iterable<E>): PersistentList<E> {
            val vector = (elements as? Collection<E>)
                    ?.let { TreePVector.from(it) }
                    ?: elements.fold(TreePVector.empty<E>()) { acc, elt -> acc.plus(elt) }

            return if (vector.isEmpty()) empty else PCollectionVector(vector)
        }
    }

    override val size get() = vector.size
    override fun isEmpty(): Boolean = vector.isEmpty()

    override fun get(index: Int): E = vector[index]

    override fun iterator(): Iterator<E> = vector.iterator()
    override fun listIterator(): ListIterator<E> = vector.listIterator()
    override fun listIterator(index: Int): ListIterator<E> = vector.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): PersistentList<E> =
            wrap(vector.subList(fromIndex, toIndex))

    override fun split(index: Int): Pair<PersistentList<E>, PersistentList<E>> =
            subList(0, index) to subList(index, vector.size)

    override fun with(index: Int, element: E): PersistentList<E> =
            wrap(vector.with(index, element))

    override fun plus(element: E): PersistentList<E> =
            wrap(vector.plus(element))

    override fun plus(elements: Iterable<E>): PersistentList<E> {
        val newVector = when (elements) {
            is PCollectionVector<E> -> vector.plusAll(elements.vector)
            is Collection<E> -> vector.plusAll(elements)
            else -> elements.fold(vector) { acc, elt -> acc + elt }
        }

        return wrap(newVector)
    }

    override fun plus(index: Int, element: E): PersistentList<E> =
            wrap(vector.plus(index, element))

    override fun plus(index: Int, elements: Iterable<E>): PersistentList<E> {
        val newVector = when (elements) {
            is PCollectionVector<E> -> vector.plusAll(index, elements.vector)
            is Collection<E> -> vector.plusAll(index, elements)
            else -> elements
                    .fold(vector.subList(0, index)) { acc, elt -> acc + elt }
                    .plusAll(vector.subList(index, vector.size))
        }

        return wrap(newVector)
    }

    override fun minus(element: E): PersistentList<E> =
            wrap(vector.minus(element))

    override fun minus(elements: Iterable<E>): PersistentList<E> {
        val newVector = when (elements) {
            is PCollectionVector<E> -> vector.minusAll(elements.vector)
            is Collection<E> -> vector.minusAll(elements)
            else -> elements.fold(vector) { acc, elt -> acc - elt }
        }

        return wrap(newVector)
    }

    override fun minusIndex(index: Int): PersistentList<E> =
            wrap(vector.minus(index))

    private fun wrap(newVector: PVector<E>): PersistentList<E> = when {
        newVector === vector -> this
        newVector.isEmpty() -> empty
        else -> PCollectionVector(newVector)
    }
}
