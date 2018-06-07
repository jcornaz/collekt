package com.github.jcornaz.collekt

/**
 * An effectively immutable collection providing *mutation method* which return new instances instead of modifying the instance.
 *
 * Typically a persistent collection is optimized to share as much data as possible between other instances,
 * reducing memory usage, and providing good performance of the *mutation methods*
 */
public interface PersistentCollection<out E> : Traversable<E> {

    /** Number of element in the collection */
    public val size: Int

    /** True if, and only if the collection is empty */
    public val isEmpty: Boolean

    /** Returns a new collection containing the same elements plus the given [element] */
    public operator fun plus(element: @UnsafeVariance E): PersistentCollection<E>

    /** Returns a new collection containing the same elements plus the element of the given [collection] */
    public operator fun plus(collection: Traversable<@UnsafeVariance E>): PersistentCollection<E>

    public operator fun minus(element: @UnsafeVariance E): PersistentCollection<E>
    public operator fun minus(collection: Traversable<@UnsafeVariance E>): PersistentCollection<E>
}

/** True if, and only if the collection is not empty */
public val PersistentCollection<*>.isNotEmpty: Boolean get() = !isEmpty
