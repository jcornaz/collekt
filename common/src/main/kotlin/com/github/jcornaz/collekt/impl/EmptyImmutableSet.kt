package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.ImmutableSet

internal object EmptyImmutableSet : ImmutableSet<Nothing> {
    override val size: Int get() = 0

    override fun contains(element: Nothing) = false
    override fun containsAll(elements: Collection<Nothing>) = elements.isEmpty()

    override fun isEmpty() = true

    override fun iterator() = EmptyIterator

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other === this) return true

        return (other as Set<*>).isEmpty()
    }

    override fun hashCode() = 1
}
