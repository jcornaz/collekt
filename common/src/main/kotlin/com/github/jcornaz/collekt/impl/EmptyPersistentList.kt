package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.*

@Suppress("UNREACHABLE_CODE")
internal object EmptyPersistentList : PersistentList<Nothing> {
    override val size get() = 0
    override val isEmpty get() = true

    override fun contains(element: Nothing) = false
    override fun get(index: Int) = throw IndexOutOfBoundsException("Empty collection")

    override fun indexOf(element: Nothing) = -1
    override fun lastIndexOf(element: Nothing) = -1

    override fun iterator() = EmptyIterator

    override fun subList(fromIndex: Int, toIndex: Int): PersistentList<Nothing> {
        checkSublistRange(fromIndex, toIndex, 0)
        return this
    }

    override fun plus(element: Nothing, index: Int): PersistentList<Nothing> {
        if (index != 0) throw IndexOutOfBoundsException()
        return DefaultListFactory.of(element)
    }

    override fun plus(collection: PersistentCollection<Nothing>, index: Int): PersistentList<Nothing> {
        if (index != 0) throw IndexOutOfBoundsException()
        return collection as? PersistentList<Nothing> ?: DefaultListFactory.from(collection.asCollection())
    }

    override fun plus(element: Nothing) = DefaultListFactory.of(element)

    override fun plus(collection: PersistentCollection<Nothing>) =
            collection as? PersistentList<Nothing> ?: DefaultListFactory.from(collection.asCollection())

    override fun minus(element: Nothing) = this
    override fun minusIndex(index: Int) = this

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other === this) return true

        return (other as List<*>).isEmpty()
    }

    override fun hashCode() = 1

    override fun toString() = "[]"
}
