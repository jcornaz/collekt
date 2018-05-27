package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.ImmutableList
import com.github.jcornaz.collekt.emptyImmutableList

internal class ImmutableArrayList<E>(private val array: Array<E>) : ImmutableList<E> {

    constructor(elements: Collection<E>) : this(elements.toTypedArray())

    override val size get() = array.size

    override fun contains(element: E) = element in array
    override fun containsAll(elements: Collection<E>) = elements.all { it in array }

    override fun isEmpty() = array.isEmpty()

    override fun iterator() = array.iterator()

    override fun get(index: Int) = array[index]

    override fun indexOf(element: E) = array.indexOf(element)

    override fun lastIndexOf(element: E) = array.lastIndexOf(element)

    override fun listIterator() = listIterator(0)

    override fun listIterator(index: Int): ListIterator<E> {
        checkRangeInclusive(index, array.size)

        return object : ListIterator<E> {
            var nextIndex = index

            override fun hasNext() = nextIndex < array.size
            override fun hasPrevious() = nextIndex > 0

            override fun next() = array[nextIndex].also { ++nextIndex }
            override fun previous() = array[--nextIndex]

            override fun nextIndex() = nextIndex
            override fun previousIndex() = nextIndex - 1
        }
    }

    override fun subList(fromIndex: Int, toIndex: Int): ImmutableList<E> {
        checkSublistRange(fromIndex, toIndex, array.size)

        if (toIndex <= fromIndex) return emptyImmutableList()

        return SubList(fromIndex, toIndex)
    }

    private inner class SubList(val fromIndex: Int, val toIndex: Int) : ImmutableList<E> {
        private val indices = (fromIndex until toIndex)
        override val size: Int = toIndex - fromIndex

        override fun isEmpty() = false

        override fun contains(element: E) = indices.any { array[it] == element }
        override fun containsAll(elements: Collection<E>) = elements.all { it in this }

        override fun iterator() = listIterator(0)

        override fun get(index: Int): E {
            checkRange(index, size)

            return array[index + fromIndex]
        }

        override fun indexOf(element: E) = indices.indexOfFirst { array[it] == element }
        override fun lastIndexOf(element: E) = indices.indexOfLast { array[it] == element }

        override fun listIterator() = listIterator(0)

        override fun listIterator(index: Int): ListIterator<E> {
            checkRangeInclusive(index, size)

            return object : ListIterator<E> {
                var nextIndex = fromIndex + index

                override fun hasNext() = nextIndex < toIndex
                override fun hasPrevious() = nextIndex > fromIndex

                override fun next() = array[nextIndex].also { ++nextIndex }
                override fun previous() = array[--nextIndex]

                override fun nextIndex() = nextIndex - index
                override fun previousIndex() = nextIndex - index - 1
            }
        }

        override fun subList(fromIndex: Int, toIndex: Int): ImmutableList<E> {
            checkSublistRange(fromIndex, toIndex, size)

            if (toIndex <= fromIndex) return emptyImmutableList()

            return SubList(this.fromIndex + fromIndex, this.fromIndex + toIndex)
        }
    }
}
