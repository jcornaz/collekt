package com.github.jcornaz.collekt

/**
 * An immutable list. Users of this interfaces can safely assumes that the content will never change.
 *
 * It is for instance never needed to create a "defensive copy".
 */
public interface ImmutableList<out E> : ImmutableCollection<E> {

    /**
     * Returns the element at [index]
     *
     * @throws kotlin.IndexOutOfBoundsException if index < 0 || index >= [size]
     */
    public operator fun get(index: Int): E

    /**
     * Returns the sublist from [fromIndex] (inclusive) to [toIndex] (exclusive)
     *
     * @throws kotlin.IndexOutOfBoundsException if [fromIndex] < 0 || [toIndex] > [size] || [toIndex] < [fromIndex]
     */
    public fun subList(fromIndex: Int, toIndex: Int): ImmutableList<E>

    /**
     * Returns two sublists. The first from 0 (inclusive) and [index] (exclusive). The second from [index] (inclusive) and [size] (exclusive)
     */
    public fun split(index: Int): Pair<ImmutableList<E>, ImmutableList<E>>

    /**
     * Returns the first index of [element] in the list.
     *
     * Returns -1 if the index is not present in the list
     */
    public fun indexOf(element: @UnsafeVariance E): Int

    /**
     * Returns the last index of [element] in the list.
     *
     * Returns -1 if the index is not present in the list
     */
    public fun lastIndexOf(element: @UnsafeVariance E): Int

    /**
     * Return a [ListIterator] starting at the given index.
     */
    public fun iterator(index: Int): ListIterator<E>

    public override fun iterator(): ListIterator<E> = iterator(0)
}

/**
 * An effectively immutable list providing *mutation method* which return new instances instead of modifying the instance.
 *
 * Typically a persistent collection is optimized to share as much data as possible between other instances,
 * reducing memory usage, and providing good performance of the *mutation methods*
 */
public interface PersistentList<out E> : PersistentCollection<E>, ImmutableList<E> {

    public override fun subList(fromIndex: Int, toIndex: Int): PersistentList<E>
    public override fun split(index: Int): Pair<ImmutableList<E>, PersistentList<E>>

    /** Returns a new list containing the same elements plus the given [element] appended at the end */
    public override fun plus(element: @UnsafeVariance E): PersistentList<E>

    /** Returns a new list containing the same elements plus the given [element] inserted at the given [index] */
    public fun plus(index: Int, element: @UnsafeVariance E): PersistentList<E>

    /** Returns a new list containing the same elements plus the given [elements] appended at the end */
    public override fun plus(elements: Traversable<@UnsafeVariance E>): PersistentList<E>

    /** Returns a new list containing the same elements plus [elements] at [index] */
    public fun plus(index: Int, elements: Traversable<@UnsafeVariance E>): PersistentList<E>

    public override fun minus(element: @UnsafeVariance E): PersistentList<E>

    public override fun minus(elements: Traversable<@UnsafeVariance E>): PersistentList<E>

    /**
     * Returns a new list containing the same elements except the element at given [index]
     *
     * @throws kotlin.IndexOutOfBoundsException if the index is < 0 or >= [size]
     */
    public fun minusIndex(index: Int): PersistentList<E>

    public override fun indexOf(element: @UnsafeVariance E): Int {
        forEachIndexed { index, elt -> if (elt == element) return index }
        return -1
    }

    public override fun lastIndexOf(element: @UnsafeVariance E): Int {
        val iterator = iterator(size)
        var index = size - 1

        while (iterator.hasPrevious()) {
            if (iterator.previous() == element) return index
            --index
        }

        return -1
    }
}

/**
 * Returns a [List] adapter for this [PersistentCollection]
 */
public fun <E> ImmutableList<E>.asList(): List<E> = object : AbstractList<E>() {
    override val size get() = this@asList.size
    override fun isEmpty() = this@asList.isEmpty
    override fun get(index: Int) = this@asList[index]
    override fun iterator() = this@asList.iterator()
}

public inline fun <E> ImmutableList<E>.forEachIndexed(action: (index: Int, element: E) -> Unit) {
    var index = 0
    forEach {
        action(index, it)
        ++index
    }
}
