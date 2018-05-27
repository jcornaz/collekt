package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.ImmutableList
import com.github.jcornaz.collekt.ImmutableSet

internal object EmptyImmutableList : ImmutableList<Nothing> {
    override val size: Int get() = 0

    override fun contains(element: Nothing) = false
    override fun containsAll(elements: Collection<Nothing>) = elements.isEmpty()

    override fun get(index: Int) = throw IndexOutOfBoundsException("Empty collection")

    override fun indexOf(element: Nothing) = -1
    override fun lastIndexOf(element: Nothing) = -1

    override fun isEmpty() = true

    override fun iterator() = EmptyIterator
    override fun listIterator() = EmptyIterator
    override fun listIterator(index: Int): ListIterator<Nothing> {
        checkRangeInclusive(index, 0)
        return EmptyIterator
    }

    override fun subList(fromIndex: Int, toIndex: Int): ImmutableList<Nothing> {
        checkSublistRange(fromIndex, toIndex, 0)
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other === this) return true

        return (other as List<*>).isEmpty()
    }

    override fun hashCode() = 0

    override fun toString() = "[]"
}
