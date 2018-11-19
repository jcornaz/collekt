package com.github.jcornaz.collekt.immutable

import com.github.jcornaz.collekt.api.ImmutableList

internal object EmptyImmutableList : ImmutableList<Nothing> {
    override val size: Int get() = 0

    override fun contains(element: Nothing): Boolean = false
    override fun containsAll(elements: Collection<Nothing>): Boolean = elements.isEmpty()

    override fun isEmpty(): Boolean = true


    override fun get(index: Int): Nothing = throw IndexOutOfBoundsException("index: $index (empty list)")

    override fun indexOf(element: Nothing): Int = -1

    override fun lastIndexOf(element: Nothing): Int = -1

    override fun iterator(): Iterator<Nothing> = EmptyIterator
    override fun listIterator(): ListIterator<Nothing> = EmptyIterator
    override fun listIterator(index: Int): ListIterator<Nothing> {
        if (index != 0) throw IndexOutOfBoundsException("index: $index (empty list)")

        return EmptyIterator
    }

    override fun subList(fromIndex: Int, toIndex: Int): ImmutableList<Nothing> {
        if (fromIndex != 0 || toIndex != 0) throw IndexOutOfBoundsException("fromIndex: $fromIndex, toIndex: $toIndex")
        return this
    }

    override fun split(index: Int): Pair<ImmutableList<Nothing>, ImmutableList<Nothing>> {
        if (index != 0) throw IndexOutOfBoundsException("index: $index (empty list)")
        return this to this
    }
}
