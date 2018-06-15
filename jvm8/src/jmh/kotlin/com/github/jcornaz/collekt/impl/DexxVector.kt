package com.github.jcornaz.collekt.impl

import com.github.andrewoma.dexx.collection.Vector
import com.github.jcornaz.collekt.PersistentList
import com.github.jcornaz.collekt.PersistentListFactory

class DexxVector<E>(private val vector: Vector<E>) : AbstractList<E>(), PersistentList<E> {

    companion object Factory : PersistentListFactory {
        private val empty = DexxVector(Vector.empty<Nothing>())

        override fun <E> empty(): PersistentList<E> = empty

        override fun <E> from(elements: Iterable<E>): PersistentList<E> =
                elements as? DexxVector<E>
                        ?: Vector.factory<E>().newBuilder().addAll(elements).build()
                                .let { if (it.isEmpty) empty else DexxVector(it) }
    }

    override val size: Int get() = vector.size()
    override fun isEmpty(): Boolean = vector.isEmpty

    override fun get(index: Int): E = vector[index]
    override fun indexOf(element: E): Int = vector.indexOf(element)
    override fun lastIndexOf(element: E): Int = vector.lastIndexOf(element)

    override fun iterator(): Iterator<E> = vector.iterator()

    override fun subList(fromIndex: Int, toIndex: Int): PersistentList<E> =
            wrap(vector.range(fromIndex, true, toIndex, false))

    override fun split(index: Int): Pair<PersistentList<E>, PersistentList<E>> =
            subList(0, index) to subList(index, vector.size())

    override fun with(index: Int, element: E): PersistentList<E> =
            wrap(vector.set(index, element))

    override fun plus(element: E): PersistentList<E> =
            wrap(vector.append(element))

    override fun plus(elements: Iterable<E>): PersistentList<E> =
            wrap(elements.fold(vector) { acc, elt -> acc.append(elt) })

    override fun plus(index: Int, element: E): PersistentList<E> {
        val (left, right) = vector.split(index)

        return wrap(left.append(element) + right)
    }

    override fun plus(index: Int, elements: Iterable<E>): PersistentList<E> {
        val (left, right) = vector.split(index)
        return wrap(elements.fold(left) { acc, elt -> acc.append(elt) } + right)
    }

    override fun minus(element: E): PersistentList<E> =
            vector.indexOf(element).let { if (it < 0) this else minusIndex(it) }

    override fun minus(elements: Iterable<E>): PersistentList<E> {
        val newVector = elements.fold(vector) { acc, elt ->
            val index = acc.indexOf(elt)
            if (index < 0) acc else {
                acc.split(index).let { (left, right) ->
                    left + right.range(0, false, right.size(), false)
                }
            }
        }

        return wrap(newVector)
    }

    override fun minusIndex(index: Int): PersistentList<E> {
        val (left, right) = vector.split(index)

        return wrap(left + right.range(0, false, right.size(), false))
    }

    private fun wrap(newVector: Vector<E>): PersistentList<E> = when {
        newVector === vector -> this
        newVector.isEmpty -> empty
        else -> DexxVector(newVector)
    }
}

private fun <E> Vector<E>.split(index: Int): Pair<Vector<E>, Vector<E>> =
        range(0, true, index, false) to range(index, true, size(), false)

private operator fun <E> Vector<E>.plus(elements: Vector<E>): Vector<E> =
        elements.fold(this) { acc, elt -> acc.append(elt) }
