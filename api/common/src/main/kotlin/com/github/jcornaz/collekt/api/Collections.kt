package com.github.jcornaz.collekt.api

/**
 * An immutable collection. Users of this interfaces can safely assumes that the content will never change.
 *
 * It is for instance never needed to create a "defensive copy".
 */
public interface ImmutableCollection<out E> : Collection<E>

/**
 * An effectively immutable collection providing *mutation method* which return new instances instead of modifying the instance.
 *
 * Typically a persistent collection is optimized to share as much data as possible between other instances,
 * reducing memory usage, and providing good performance of the *mutation methods*
 */
public interface PersistentCollection<out E> : ImmutableCollection<E> {

    /** Returns a new collection containing the same elements plus the given [element] */
    public operator fun plus(element: @UnsafeVariance E): PersistentCollection<E>

    /** Returns a new collection containing the same elements plus the element of the given [elements] */
    public operator fun plus(elements: Iterable<@UnsafeVariance E>): PersistentCollection<E>

    /** Returns a new collection containing the same elements minus the given [element] */
    public operator fun minus(element: @UnsafeVariance E): PersistentCollection<E>

    /** Returns a new collection containing the same elements minus the given [elements] */
    public operator fun minus(elements: Iterable<@UnsafeVariance E>): PersistentCollection<E>
}
