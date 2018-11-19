package com.github.jcornaz.collekt.immutable

internal object EmptyIterator : Iterator<Nothing>, ListIterator<Nothing> {
    override fun hasNext(): Boolean = false
    override fun hasPrevious(): Boolean = false

    override fun nextIndex(): Int = 0
    override fun previousIndex(): Int = -1

    override fun next(): Nothing = throw NoSuchElementException()
    override fun previous(): Nothing = throw NoSuchElementException()
}
