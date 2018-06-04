package com.github.jcornaz.collekt

/**
 * An effectively immutable collection providing *mutation method* which return new instances instead of modifying the instance.
 *
 * Typically a persistent collection is optimized to share as much data as possible between other instances,
 * reducing memory usage, and providing good performance of the *mutation methods*
 */
public interface PersistentCollection<out E> {

    /** Number of element in the collection */
    public val size: Int

    /** True if, and only if the collection is empty */
    public val isEmpty: Boolean

    /** True if, and only if the collection contains the given [element] */
    public operator fun contains(element: @UnsafeVariance E): Boolean

    /** Returns an iterator over the elements of the collection */
    public operator fun iterator(): Iterator<E>

    /** Returns a new collection containing the same elements plus the given [element] */
    public operator fun plus(element: @UnsafeVariance E): PersistentCollection<E>

    /** Returns a new collection containing the same elements plus the element of the given [collection] */
    public operator fun plus(collection: PersistentCollection<@UnsafeVariance E>): PersistentCollection<E>

    /** Returns a new collection containing the same elements except the given [element] */
    public operator fun minus(element: @UnsafeVariance E): PersistentCollection<E>
}

/**
 * An effectively immutable list providing *mutation method* which return new instances instead of modifying the instance.
 *
 * Typically a persistent collection is optimized to share as much data as possible between other instances,
 * reducing memory usage, and providing good performance of the *mutation methods*
 */
public interface PersistentList<out E> : PersistentCollection<E> {

    /**
     * Returns the element at the specified [index]
     *
     * @throws kotlin.IndexOutOfBoundsException If the index is < 0 or >= [size]
     */
    public operator fun get(index: Int): E

    /** Returns the first index of the given [element] or `-1` if not present in the list */
    public fun indexOf(element: @UnsafeVariance E): Int

    /** Returns the last index of the given [element] or `-1` if not present in the list */
    public fun lastIndexOf(element: @UnsafeVariance E): Int

    /**
     * Returns a list which contains the elements of this list which are between [fromIndex] (inclusive) and [toIndex] (exclusive)
     */
    public fun subList(fromIndex: Int, toIndex: Int): PersistentList<E>

    /** Returns a new list containing the same elements plus the given [element] appended at the end */
    public override fun plus(element: @UnsafeVariance E): PersistentList<E> = plus(element, size)

    /** Returns a new list containing the same elements plus the given [element] inserted at the given [index] */
    public fun plus(element: @UnsafeVariance E, index: Int): PersistentList<E>

    /** Returns a new list containing the same elements plus the given [collection] appended at the end */
    public override fun plus(collection: PersistentCollection<@UnsafeVariance E>): PersistentList<E> = plus(collection, size)

    /** Returns a new list containing the same elements plus                                                    wr*/
    public fun plus(collection: PersistentCollection<@UnsafeVariance E>, index: Int): PersistentList<E>

    /**
     * Returns a new list containing the same elements minus the given [element]
     *
     * If there is many occurrence of the [element], then the first one is removed in the result list
     */
    public override fun minus(element: @UnsafeVariance E): PersistentList<E>

    /**
     * Returns a new list containing the same elements except the element at given [index]
     *
     * @throws kotlin.IndexOutOfBoundsException if the index is < 0 or >= [size]
     */
    public fun minusIndex(index: Int): PersistentList<E>
}

/**
 * Returns a [Collection] adapter for this [PersistentCollection]
 */
public fun <E> PersistentCollection<E>.asCollection(): Collection<E> = object : AbstractCollection<E>() {
    override val size get() = this@asCollection.size
    override fun iterator() = this@asCollection.iterator()
    override fun contains(element: E) = element in this@asCollection
}

/**
 * Returns a [List] adapter for this [PersistentCollection]
 */
public fun <E> PersistentList<E>.asList(): List<E> = object : AbstractList<E>() {
    override val size get() = this@asList.size
    override fun isEmpty() = this@asList.isEmpty
    override fun get(index: Int) = this@asList[index]
    override fun contains(element: E) = element in this@asList
    override fun iterator() = this@asList.iterator()
}

internal interface PersistentCollectionFactory {
    val empty: PersistentCollection<Nothing>
    fun <E> from(iterable: Iterable<E>): PersistentCollection<E>
}

internal interface PersistentListFactory : PersistentCollectionFactory {
    override val empty: PersistentList<Nothing>
    override fun <E> from(iterable: Iterable<E>): PersistentList<E>
}

internal fun <E> PersistentCollectionFactory.empty(): PersistentCollection<E> = empty
internal fun <E> PersistentListFactory.empty(): PersistentList<E> = empty

internal fun <E> PersistentCollectionFactory.of(vararg elements: E): PersistentCollection<E> =
        if (elements.isEmpty()) empty else from(elements.asIterable())

internal fun <E> PersistentListFactory.of(vararg elements: E): PersistentList<E> =
        if (elements.isEmpty()) empty else from(elements.asIterable())
