package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.PersistentCollection
import com.github.jcornaz.collekt.PersistentList
import com.github.jcornaz.collekt.PersistentListFactory
import com.github.jcornaz.collekt.asCollection
import org.pcollections.TreePVector

class PCollectionVector<E>(private val vector: TreePVector<E>) : AbstractPersistentList<E>() {
    override val factory get() = PCollectionVector
    override val size get() = vector.size
    override val isEmpty get() = vector.isEmpty()

    override fun createSubList(fromIndex: Int, toIndex: Int): PersistentList<E> =
            PCollectionVector(vector.subList(fromIndex, toIndex))

    override fun insert(element: E, index: Int): PersistentList<E> =
            PCollectionVector(vector.plus(index, element))

    override fun insert(collection: PersistentCollection<E>, index: Int): PersistentList<E> =
            PCollectionVector(vector.plusAll(index, (collection as? PCollectionVector<E>)?.vector
                    ?: collection.asCollection()))

    override fun concat(collection: PersistentCollection<E>): PersistentList<E> =
            PCollectionVector(vector.plusAll((collection as? PCollectionVector<E>)?.vector
                    ?: collection.asCollection()))

    override fun remove(element: E): PersistentList<E> =
            PCollectionVector(vector.minus(element))

    override fun removeIndex(index: Int): PersistentList<E> =
            PCollectionVector(vector.minus(index))

    override fun contains(element: E): Boolean = vector.contains(element)

    override fun iterator(): Iterator<E> = vector.iterator()

    override fun plus(element: E): PersistentList<E> =
            PCollectionVector(vector.plus(element))

    override fun get(index: Int): E = vector[index]

    override fun indexOf(element: E): Int = vector.indexOf(element)

    override fun lastIndexOf(element: E): Int = vector.lastIndexOf(element)

    companion object Factory : PersistentListFactory {
        override val empty = PCollectionVector(TreePVector.empty<Nothing>())

        override fun <E> from(iterable: Iterable<E>): PersistentList<E> {
            val vector = (iterable as? Collection<E>)
                    ?.let { TreePVector.from(it) }
                    ?: iterable.fold(TreePVector.empty<E>()) { acc, elt -> acc.plus(elt) }

            return vector.takeUnless { it.isEmpty() }
                    ?.let { PCollectionVector(it) }
                    ?: empty
        }
    }
}
