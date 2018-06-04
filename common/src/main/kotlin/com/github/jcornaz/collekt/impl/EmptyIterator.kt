package com.github.jcornaz.collekt.impl

internal object EmptyIterator : Iterator<Nothing> {
    override fun hasNext() = false
    override fun next() = throw NoSuchElementException()
}
