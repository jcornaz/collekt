package com.github.jcornaz.collekt.impl

internal object EmptyIterator : ListIterator<Nothing> {
    override fun hasNext() = false
    override fun hasPrevious() = false

    override fun next() = throw NoSuchElementException()
    override fun previous() = throw NoSuchElementException()

    override fun nextIndex() = 0
    override fun previousIndex() = -1
}
