package com.github.jcornaz.collekt

public interface PersistentOrderedCollection<out E> : PersistentCollection<E> {

    /**
     * Returns the element at the specified [index]
     *
     * @throws kotlin.IndexOutOfBoundsException If the index is < 0 or >= [size]
     */
    public operator fun get(index: Int): E

    public fun listIterator(index: Int = 0): ListIterator<E>
    public override fun iterator(): Iterator<E> = listIterator(0)

    /**
     * Returns a list which contains the elements of this list which are between [fromIndex] (inclusive) and [toIndex] (exclusive)
     */
    public fun subList(fromIndex: Int, toIndex: Int): PersistentOrderedCollection<E>

    public override fun plus(element: @UnsafeVariance E): PersistentOrderedCollection<E>
    public override fun plus(collection: Traversable<@UnsafeVariance E>): PersistentOrderedCollection<E>

    /**
     * Returns a new list containing the same elements minus the given [element]
     *
     * If there is many occurrence of the [element], then the first one is removed in the result list
     */
    public override fun minus(element: @UnsafeVariance E): PersistentOrderedCollection<E>

    public override fun minus(collection: Traversable<@UnsafeVariance E>): PersistentOrderedCollection<E>
}

/** Returns the first index of the given [element] or `-1` if not present in the list */
public fun <E> PersistentOrderedCollection<E>.indexOf(element: E): Int {
    forEachIndexed { index, elt -> if (elt == element) return index }
    return -1
}

/** Returns the last index of the given [element] or `-1` if not present in the list */
public fun <E> PersistentOrderedCollection<E>.lastIndexOf(element: @UnsafeVariance E): Int {
    var index = size - 1
    val iterator = listIterator(size)

    while (iterator.hasPrevious()) {
        if (iterator.previous() == element) return index
        --index
    }

    return -1
}
