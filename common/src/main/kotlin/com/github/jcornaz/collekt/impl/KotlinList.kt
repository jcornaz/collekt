package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.PersistentCollection
import com.github.jcornaz.collekt.PersistentList
import com.github.jcornaz.collekt.PersistentListFactory
import com.github.jcornaz.collekt.asCollection

internal class KotlinList<out E>(private val list: List<E>) : PersistentList<E> {
    override val size get() = list.size
    override val isEmpty get() = list.isEmpty()

    override fun get(index: Int): E = list[index]

    override fun indexOf(element: @UnsafeVariance E) = list.indexOf(element)
    override fun lastIndexOf(element: @UnsafeVariance E) = list.lastIndexOf(element)

    override fun subList(fromIndex: Int, toIndex: Int) = KotlinList(list.subList(fromIndex, toIndex))

    override fun plus(element: @UnsafeVariance E) = KotlinList(list + element)
    override fun plus(element: @UnsafeVariance E, index: Int) =
            if (index == size) plus(element) else KotlinList(list.subList(0, index) + element + list.subList(index, size))

    override fun plus(collection: PersistentCollection<@UnsafeVariance E>) =
            KotlinList(list + collection.asCollection())

    override fun plus(collection: PersistentCollection<@UnsafeVariance E>, index: Int) =
            if (index == size) plus(collection) else KotlinList(list.subList(0, index) + collection.asCollection() + list.subList(index, size))

    override fun minus(element: @UnsafeVariance E) = KotlinList(list - element)
    override fun minusIndex(index: Int) = KotlinList(list.subList(0, index) + list.subList(index + 1, size))

    override fun contains(element: @UnsafeVariance E) = element in list

    override fun iterator() = list.iterator()

    companion object : PersistentListFactory {
        override fun <E> empty() = EmptyPersistentList
        override fun <E> from(iterable: Iterable<E>) = KotlinList(iterable.toList())
    }
}
