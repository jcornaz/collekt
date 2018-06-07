package com.github.jcornaz.collekt

/**
 * An effectively immutable list providing *mutation method* which return new instances instead of modifying the instance.
 *
 * Typically a persistent collection is optimized to share as much data as possible between other instances,
 * reducing memory usage, and providing good performance of the *mutation methods*
 */
public interface PersistentList<out E> : PersistentOrderedCollection<E> {

    public override fun subList(fromIndex: Int, toIndex: Int): PersistentList<E>

    /** Returns a new list containing the same elements plus the given [element] appended at the end */
    public override fun plus(element: @UnsafeVariance E): PersistentList<E>

    /** Returns a new list containing the same elements plus the given [element] inserted at the given [index] */
    public fun plus(index: Int, element: @UnsafeVariance E): PersistentList<E>

    /** Returns a new list containing the same elements plus the given [collection] appended at the end */
    public override fun plus(collection: Traversable<@UnsafeVariance E>): PersistentList<E>

    /** Returns a new list containing the same elements plus [collection] at [index] */
    public fun plus(index: Int, collection: Traversable<@UnsafeVariance E>): PersistentList<E>

    override fun minus(element: @UnsafeVariance E): PersistentList<E>

    override fun minus(collection: Traversable<@UnsafeVariance E>): PersistentList<E>

    /**
     * Returns a new list containing the same elements except the element at given [index]
     *
     * @throws kotlin.IndexOutOfBoundsException if the index is < 0 or >= [size]
     */
    public fun minusIndex(index: Int): PersistentList<E>

}
