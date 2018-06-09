package com.github.jcornaz.collekt.impl

class RandomAccessIterator<out E>(initialIndex: Int, val size: Int, val get: (Int) -> E) : ListIterator<E> {
    var index = 0

    init {
        if (initialIndex < 0 || initialIndex > size) throw IndexOutOfBoundsException()
    }

    override fun hasNext() = index < size
    override fun hasPrevious() = index > 0

    override fun nextIndex() = index
    override fun previousIndex() = index - 1

    override fun next() = get(index).also { ++index }
    override fun previous() = get(index - 1).also { --index }
}
