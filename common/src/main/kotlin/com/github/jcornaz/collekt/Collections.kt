package com.github.jcornaz.collekt

public interface ImmutableCollection<out E> : SizedTraversable<E>

/**
 * An effectively immutable collection providing *mutation method* which return new instances instead of modifying the instance.
 *
 * Typically a persistent collection is optimized to share as much data as possible between other instances,
 * reducing memory usage, and providing good performance of the *mutation methods*
 */
public interface PersistentCollection<out E> : ImmutableCollection<E> {


    /** Returns a new collection containing the same elements plus the given [element] */
    public operator fun plus(element: @UnsafeVariance E): PersistentCollection<E>

    /** Returns a new collection containing the same elements plus the element of the given [collection] */
    public operator fun plus(collection: Traversable<@UnsafeVariance E>): PersistentCollection<E>

    public operator fun minus(element: @UnsafeVariance E): PersistentCollection<E>
    public operator fun minus(collection: Traversable<@UnsafeVariance E>): PersistentCollection<E>
}

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

