package com.github.jcornaz.collekt.impl

import com.github.andrewoma.dexx.collection.Vector
import com.github.jcornaz.collekt.*

internal class DexxVector<E>(private val vector: Vector<E>) : AbstractPersistentList<E>() {

    companion object Factory : PersistentListFactory {
        private val empty = DexxVector(Vector.empty<Nothing>())

        override fun <E> empty(): PersistentList<E> = empty

        override fun <E> from(elements: Iterable<E>): PersistentList<E> =
                if (elements.none()) empty() else wrap(Vector.factory<E>().newBuilder().addAll(elements).build())
    }

    override val size: Int get() = vector.size()
    override val isEmpty: Boolean get() = vector.isEmpty

    override fun get(index: Int): E = vector[index]
    override fun indexOf(element: E): Int = vector.indexOf(element)
    override fun lastIndexOf(element: E): Int = vector.lastIndexOf(element)

    override fun iterator(index: Int): ListIterator<E> = RandomAccessIterator(index, vector.size(), vector::get)

    override fun subList(fromIndex: Int, toIndex: Int): PersistentList<E> =
            wrap(vector.range(fromIndex, true, toIndex, false))

    override fun split(index: Int): Pair<ImmutableList<E>, PersistentList<E>> =
            subList(0, index) to subList(index, vector.size())

    override fun plus(element: E): PersistentList<E> =
            wrap(vector.append(element))

    override fun plus(elements: Traversable<E>): PersistentList<E> =
            if (elements.none()) empty() else wrap(elements.fold(vector) { acc, elt -> acc.append(elt) })

    override fun plus(index: Int, element: E): PersistentList<E> {
        val (left, right) = vector.split(index)

        return wrap(left.append(element) + right)
    }

    override fun plus(index: Int, elements: Traversable<E>): PersistentList<E> {
        if (elements.none()) return this

        val (left, right) = vector.split(index)

        return wrap(elements.fold(left) { acc, elt -> acc.append(elt) } + right)
    }

    override fun minus(element: E): PersistentList<E> = minusIndex(vector.indexOf(element))

    override fun minus(elements: Traversable<E>): PersistentList<E> {
        if (elements.none()) return this

        return wrap(elements.fold(vector) { acc, elt ->
            acc.split(acc.indexOf(elt)).let { (left, right) ->
                left + right.range(0, false, right.size(), false)
            }
        })
    }

    override fun minusIndex(index: Int): PersistentList<E> {
        val (left, right) = vector.split(index)

        return wrap(left + right.range(0, false, right.size(), false))
    }
}

private fun <E> wrap(vector: Vector<E>): PersistentList<E> =
        vector.takeUnless(Vector<E>::isEmpty)?.let(::DexxVector) ?: DexxVector.empty()

private fun <E> Vector<E>.split(index: Int): Pair<Vector<E>, Vector<E>> =
        range(0, true, index, false) to range(index, true, size(), false)

private operator fun <E> Vector<E>.plus(elements: Vector<E>): Vector<E> =
        elements.fold(this) { acc, elt -> acc.append(elt) }
