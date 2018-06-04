package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.PersistentCollection
import com.github.jcornaz.collekt.PersistentList
import com.github.jcornaz.collekt.PersistentListFactory
import com.github.jcornaz.collekt.asCollection

/**
 * Naive implementation using kotlin standard lists and operators.
 *
 * This is the worse persistent implementation possible, and performances of all mutation method are expected to be really bad.
 */
internal class KotlinList<out E>(private val list: List<E>) : AbstractPersistentList<E>() {
    override val size get() = list.size
    override val isEmpty get() = list.isEmpty()
    override val factory get() = Factory

    override fun get(index: Int): E = list[index]

    override fun indexOf(element: @UnsafeVariance E) = list.indexOf(element)
    override fun lastIndexOf(element: @UnsafeVariance E) = list.lastIndexOf(element)

    override fun createSubList(fromIndex: Int, toIndex: Int) = KotlinList(list.subList(fromIndex, toIndex))

    override fun plus(element: @UnsafeVariance E) = KotlinList(list + element)

    override fun insert(element: @UnsafeVariance E, index: Int): PersistentList<E> =
            KotlinList(list.subList(0, index) + element + list.subList(index, size))

    override fun add(collection: PersistentCollection<@UnsafeVariance E>): PersistentList<E> =
            KotlinList(list + collection.asCollection())

    override fun add(collection: PersistentCollection<@UnsafeVariance E>, index: Int): PersistentList<E> =
            KotlinList(list.subList(0, index) + collection.asCollection() + list.subList(index, size))

    override fun remove(element: @UnsafeVariance E) = KotlinList(list - element)
    override fun removeIndex(index: Int) = KotlinList(list.subList(0, index) + list.subList(index + 1, size))

    override fun contains(element: @UnsafeVariance E) = element in list

    override fun iterator() = list.iterator()

    companion object Factory : PersistentListFactory {
        override val empty = KotlinList<Nothing>(emptyList())

        override fun <E> from(iterable: Iterable<E>) = KotlinList(iterable.toList())
    }
}
