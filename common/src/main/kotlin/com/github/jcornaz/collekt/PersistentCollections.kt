package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.EmptyPersistentList
import com.github.jcornaz.collekt.impl.EmptyPersistentSet

internal expect val DefaultListFactory: PersistentListFactory
internal expect val DefaultSetFactory: PersistentSetFactory

/**
 * Returns an empty [PersistentList]
 */
public fun <E> emptyPersistentList(): PersistentList<E> = EmptyPersistentList

/**
 * Returns an empty [PersistentSet]
 */
public fun <E> emptyPersistentSet(): PersistentSet<E> = EmptyPersistentSet

/**
 * Returns a new immutable list containing the given elements
 */
public fun <E> persistentListOf(vararg values: E): PersistentList<E> =
        if (values.isEmpty()) emptyPersistentList() else DefaultListFactory.from(values.asIterable())

/**
 * Returns a new immutable list all elements in this iterable.
 *
 * May create a defensive copy if necessary
 */
public fun <E> Iterable<E>.toPersistentList(): PersistentList<E> = when {
    none() -> emptyPersistentList()
    else -> DefaultListFactory.from(this)
}

/**
 * Returns a new immutable list all elements in this sequence.
 */
public fun <E> Sequence<E>.toPersistentList(): PersistentList<E> = DefaultListFactory.from(asIterable())

public fun <E> PersistentCollection<E>.asCollection(): Collection<E> = object : AbstractCollection<E>() {
    override val size get() = this@asCollection.size
    override fun iterator() = this@asCollection.iterator()
}

public fun <E> PersistentList<E>.asList(): List<E> = object : AbstractList<E>() {
    override val size get() = this@asList.size
    override fun get(index: Int) = this@asList[index]
}

public fun <E> PersistentSet<E>.asSet(): Set<E> = object : AbstractSet<E>() {
    override val size get() = this@asSet.size
    override fun iterator() = this@asSet.iterator()
}
