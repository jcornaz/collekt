package com.github.jcornaz.collekt

/**
 * Returns a [Collection] adapter for this [PersistentCollection]
 */
public fun <E> PersistentCollection<E>.asCollection(): Collection<E> = object : AbstractCollection<E>() {
    override val size get() = this@asCollection.size
    override fun isEmpty() = this@asCollection.isEmpty
    override fun iterator() = this@asCollection.iterator()
}

/**
 * Returns a [List] adapter for this [PersistentCollection]
 */
public fun <E> PersistentList<E>.asList(): List<E> = object : AbstractList<E>() {
    override val size get() = this@asList.size
    override fun isEmpty() = this@asList.isEmpty
    override fun get(index: Int) = this@asList[index]
    override fun iterator() = this@asList.iterator()
}

public fun <E> Traversable<E>.asIterable(): Iterable<E> = Iterable { iterator() }
public fun <E> Traversable<E>.asSequence(): Sequence<E> = Sequence { iterator() }
