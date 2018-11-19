package com.github.jcornaz.collekt.immutable

import com.github.jcornaz.collekt.api.ImmutableSet

internal object EmptyImmutableSet : ImmutableSet<Nothing> {
    override val size: Int get() = 0

    override fun contains(element: Nothing): Boolean = false

    override fun containsAll(elements: Collection<Nothing>): Boolean = elements.isEmpty()

    override fun isEmpty(): Boolean = true

    override fun iterator(): Iterator<Nothing> = EmptyIterator
}
