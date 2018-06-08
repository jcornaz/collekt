package com.github.jcornaz.collekt

public interface ImmutableList<out E> : ImmutableCollection<E> {
    operator fun get(index: Int): E
    operator fun get(fromIndex: Int, toIndex: Int): ImmutableList<E>

    fun indexOfFirst(element: @UnsafeVariance E): Int
    fun indexOfLast(element: @UnsafeVariance E): Int

    fun iterator(index: Int): ListIterator<E>
    override fun iterator(): ListIterator<E> = iterator(0)
}

/**
 * An effectively immutable list providing *mutation method* which return new instances instead of modifying the instance.
 *
 * Typically a persistent collection is optimized to share as much data as possible between other instances,
 * reducing memory usage, and providing good performance of the *mutation methods*
 */
public interface PersistentList<out E> : PersistentCollection<E>, ImmutableList<E> {

    override fun get(fromIndex: Int, toIndex: Int): PersistentList<E>

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

    override fun indexOfFirst(element: @UnsafeVariance E): Int {
        forEachIndexed { index, elt -> if (elt == element) return index }
        return -1
    }

    override fun indexOfLast(element: @UnsafeVariance E): Int {
        val iterator = iterator(size)
        var index = size - 1

        while (iterator.hasPrevious()) {
            if (iterator.previous() == element) return index
            --index
        }

        return -1
    }
}

public inline fun <E> ImmutableList<E>.forEachIndexed(action: (index: Int, element: E) -> Unit) {
    var index = 0
    forEach {
        action(index, it)
        ++index
    }
}
