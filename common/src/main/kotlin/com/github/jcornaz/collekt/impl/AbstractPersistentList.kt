package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.*

internal abstract class AbstractPersistentList<out E> : PersistentList<E> {

    protected abstract val factory: PersistentListFactory

    /**
     * called by [subList] if the index are valid and it is known that the result is not an empty list or this list
     */
    protected abstract fun createSubList(fromIndex: Int, toIndex: Int): PersistentList<E>

    /**
     * called by [plus] if the index is not equal to the size
     */
    protected abstract fun insert(element: @UnsafeVariance E, index: Int): PersistentList<E>

    /**
     * called by [plus] if the result is known to be a new collection (not this one)
     */
    protected abstract fun add(collection: PersistentCollection<@UnsafeVariance E>): PersistentList<E>

    /**
     * called by [plus] if the result is known to be a new collection (not this one) and the index is not at the end of the list
     */
    protected abstract fun add(collection: PersistentCollection<@UnsafeVariance E>, index: Int): PersistentList<E>

    /**
     * called by [minus] if the result is known to not be an empty list or this list
     */
    protected abstract fun remove(element: @UnsafeVariance E): PersistentList<E>

    /**
     * called by [minusIndex] if the index is valid and it is known that the result is not an empty list or this list
     */
    protected abstract fun removeIndex(index: Int): PersistentList<E>

    final override fun plus(element: @UnsafeVariance E, index: Int): PersistentList<E> =
            if (index == size) plus(element) else insert(element, index)

    final override fun plus(collection: PersistentCollection<@UnsafeVariance E>): PersistentList<E> =
            if (collection.isEmpty) this else add(collection)

    final override fun plus(collection: PersistentCollection<@UnsafeVariance E>, index: Int): PersistentList<E> = when {
        collection.isEmpty -> this
        index == size -> add(collection)
        else -> add(collection, index)
    }

    final override fun subList(fromIndex: Int, toIndex: Int): PersistentList<E> = when {
        fromIndex < 0 -> throw IndexOutOfBoundsException("fromIndex: $fromIndex")
        toIndex > size -> throw IndexOutOfBoundsException("toIndex: $toIndex, size: $size")
        toIndex < fromIndex -> throw IndexOutOfBoundsException("fromIndex: $fromIndex, toIndex: $toIndex")
        fromIndex == 0 && toIndex == size -> this
        toIndex == fromIndex -> factory.empty
        else -> createSubList(fromIndex, toIndex)
    }

    final override fun minus(element: @UnsafeVariance E): PersistentList<E> = when {
        isEmpty -> this
        size == 1 -> if (first() == element) factory.empty else this
        else -> remove(element)
    }

    final override fun minusIndex(index: Int): PersistentList<E> = when {
        isEmpty -> throw IndexOutOfBoundsException("index: $index, size: $size")
        size == 1 && index == 0 -> factory.empty
        else -> removeIndex(index)
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other === this) return true
        if (other !is PersistentList<*>) return false
        if (size != other.size) return false

        val e1 = iterator()
        val e2 = other.iterator()

        while (e1.hasNext() && e2.hasNext()) {
            val o1 = e1.next()
            val o2 = e2.next()
            if (!(if (o1 == null) o2 == null else o1 == o2))
                return false
        }

        return !(e1.hasNext() || e2.hasNext())
    }

    override fun hashCode(): Int {
        if (isEmpty) return 1

        var hash = 1

        for (element in this) {
            hash = 31 * hash + (element?.hashCode() ?: 0)
        }

        return hash
    }

    override fun toString() = joinToString(prefix = "[", separator = ", ", postfix = "]")
}
