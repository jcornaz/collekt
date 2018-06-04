package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.*

@Suppress("UNREACHABLE_CODE")
internal object EmptyPersistentSet : PersistentSet<Nothing> {
    override val size get() = 0
    override val isEmpty get() = true

    override fun contains(element: Nothing) = false

    override fun iterator() = EmptyIterator

    override fun plus(element: Nothing) = DefaultSetFactory.of(element)

    override fun plus(collection: PersistentCollection<Nothing>) =
            collection as? PersistentSet<Nothing> ?: DefaultSetFactory.from(collection.asCollection())

    override fun minus(element: Nothing) = this

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other === this) return true

        return (other as Set<*>).isEmpty()
    }

    override fun hashCode() = 0

    override fun toString() = "[]"
}
