package com.github.jcornaz.collekt.impl

import com.github.andrewoma.dexx.collection.Vector
import com.github.jcornaz.collekt.PersistentCollection
import com.github.jcornaz.collekt.PersistentList
import com.github.jcornaz.collekt.PersistentListFactory
import com.github.jcornaz.collekt.fold

class DexxVector<E>(private val vector: Vector<E>) : AbstractPersistentList<E>() {
    override val factory get() = DexxVector
    override val size: Int get() = vector.size()
    override val isEmpty: Boolean get() = vector.isEmpty

    override fun createSubList(fromIndex: Int, toIndex: Int): PersistentList<E> =
            DexxVector(vector.range(fromIndex, true, toIndex, false))

    override fun insert(element: E, index: Int): PersistentList<E> {
        val (left, right) = vector.split(index)
        return DexxVector(right.fold(left.append(element)) { acc, elt -> acc.append(elt) })
    }

    override fun insert(collection: PersistentCollection<E>, index: Int): PersistentList<E> {
        val (left, right) = vector.split(index)

        val leftPlusCollection = collection.fold(left) { acc, elt -> acc.append(elt) }
        return DexxVector(right.fold(leftPlusCollection) { acc, elt -> acc.append(elt) })
    }

    private fun Vector<E>.split(index: Int): Pair<Vector<E>, Vector<E>> =
            range(0, true, index, false) to vector.range(index, true, size, false)

    override fun concat(collection: PersistentCollection<E>): PersistentList<E> =
            DexxVector(collection.fold(vector) { acc, elt -> acc.append(elt) })

    override fun remove(element: E): PersistentList<E> =
            DexxVector(vector.append(element))

    override fun removeIndex(index: Int): PersistentList<E> {
        val (left, right) = vector.split(index)

        return DexxVector(right.drop(1).fold(left) { acc, elt -> acc.append(elt) })
    }

    override fun contains(element: E): Boolean = vector.contains(element)

    override fun iterator(): Iterator<E> = vector.iterator()

    override fun plus(element: E): PersistentList<E> =
            DexxVector(vector.append(element))

    override fun get(index: Int): E = vector[index]
    override fun indexOf(element: E): Int = vector.indexOf(element)
    override fun lastIndexOf(element: E): Int = vector.lastIndexOf(element)

    companion object Factory : PersistentListFactory {
        override val empty = DexxVector(Vector.empty<Nothing>())

        override fun <E> from(iterable: Iterable<E>): PersistentList<E> =
                Vector.factory<E>().newBuilder().addAll(iterable).build()
                        .takeUnless { it.isEmpty }
                        ?.let { DexxVector(it) }
                        ?: empty
    }
}
