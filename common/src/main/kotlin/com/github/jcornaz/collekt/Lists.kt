package com.github.jcornaz.collekt

public interface ImmutableList<out E> : ImmutableCollection<E> {
    public operator fun get(index: Int): E
    public fun range(fromIndex: Int, toIndex: Int): ImmutableList<E>

    public fun split(index: Int): Pair<ImmutableList<E>, ImmutableList<E>>

    public fun indexOfFirst(element: @UnsafeVariance E): Int
    public fun indexOfLast(element: @UnsafeVariance E): Int

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

    public override fun range(fromIndex: Int, toIndex: Int): PersistentList<E>
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

    public override fun indexOfFirst(element: @UnsafeVariance E): Int {
        forEachIndexed { index, elt -> if (elt == element) return index }
        return -1
    }

    public override fun indexOfLast(element: @UnsafeVariance E): Int {
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
public fun <E> PersistentList<E>.asList(): List<E> = object : AbstractList<E>() {
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
