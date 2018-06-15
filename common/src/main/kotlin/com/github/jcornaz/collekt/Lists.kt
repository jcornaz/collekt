package com.github.jcornaz.collekt

/**
 * An immutable list. Users of this interfaces can safely assumes that the content will never change.
 *
 * It is for instance never needed to create a "defensive copy".
 */
public interface ImmutableList<out E> : ImmutableCollection<E>, List<E> {

    /**
     * Returns the sublist from [fromIndex] (inclusive) to [toIndex] (exclusive)
     *
     * @throws kotlin.IndexOutOfBoundsException if [fromIndex] < 0 || [toIndex] > [size] || [toIndex] < [fromIndex]
     */
    public override fun subList(fromIndex: Int, toIndex: Int): ImmutableList<E>

    /**
     * Returns two sublists. The first from 0 (inclusive) and [index] (exclusive). The second from [index] (inclusive) and [size] (exclusive)
     */
    public fun split(index: Int): Pair<ImmutableList<E>, ImmutableList<E>>
}

/**
 * An effectively immutable list providing *mutation method* which return new instances instead of modifying the instance.
 *
 * Typically a persistent collection is optimized to share as much data as possible between other instances,
 * reducing memory usage, and providing good performance of the *mutation methods*
 */
public interface PersistentList<out E> : PersistentCollection<E>, ImmutableList<E> {

    /** Returns a new list containing the same elements plus the given [element] inserted at the given [index] */
    public fun plus(index: Int, element: @UnsafeVariance E): PersistentList<E>

    /** Returns a new list containing the same elements plus [elements] at [index] */
    public fun plus(index: Int, elements: Iterable<@UnsafeVariance E>): PersistentList<E>

    /**
     * Returns a new list containing the same elements, replacing the element at [index] by [element]
     *
     * @throws kotlin.IndexOutOfBoundsException if the index is < 0 or >= [size]
     */
    public fun with(index: Int, element: @UnsafeVariance E): PersistentList<E>

    /**
     * Returns a new list containing the same elements except the element at given [index]
     *
     * @throws kotlin.IndexOutOfBoundsException if the index is < 0 or >= [size]
     */
    public fun minusIndex(index: Int): PersistentList<E>

    public override fun subList(fromIndex: Int, toIndex: Int): PersistentList<E>
    public override fun split(index: Int): Pair<PersistentList<E>, PersistentList<E>>

    public override fun plus(element: @UnsafeVariance E): PersistentList<E>
    public override fun plus(elements: Iterable<@UnsafeVariance E>): PersistentList<E>

    public override fun minus(element: @UnsafeVariance E): PersistentList<E>
    public override fun minus(elements: Iterable<@UnsafeVariance E>): PersistentList<E>
}
