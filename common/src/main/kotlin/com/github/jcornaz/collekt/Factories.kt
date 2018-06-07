package com.github.jcornaz.collekt

internal interface PersistentCollectionFactory {
    val empty: PersistentCollection<Nothing>
    fun <E> from(iterable: Iterable<E>): PersistentCollection<E>
}

internal interface PersistentListFactory : PersistentCollectionFactory {
    override val empty: PersistentList<Nothing>
    override fun <E> from(iterable: Iterable<E>): PersistentList<E>
}

internal fun <E> PersistentCollectionFactory.empty(): PersistentCollection<E> = empty
internal fun <E> PersistentListFactory.empty(): PersistentList<E> = empty

internal fun <E> PersistentListFactory.of(vararg elements: E): PersistentList<E> =
        if (elements.isEmpty()) empty else from(elements.asIterable())

internal fun <E> PersistentCollectionFactory.of(vararg elements: E): PersistentCollection<E> =
        if (elements.isEmpty()) empty else from(elements.asIterable())
