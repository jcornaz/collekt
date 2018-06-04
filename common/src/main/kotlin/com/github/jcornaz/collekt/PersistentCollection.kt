package com.github.jcornaz.collekt

/**
 * An immutable [Collection].
 *
 * All implementers promise that the collection won't ever change after instance creation
 */
public interface PersistentCollection<out E> {
    val size: Int
    val isEmpty: Boolean

    operator fun contains(element: @UnsafeVariance E): Boolean

    operator fun iterator(): Iterator<E>

    operator fun plus(element: @UnsafeVariance E): PersistentCollection<E>
    operator fun plus(collection: PersistentCollection<@UnsafeVariance E>): PersistentCollection<E>
    operator fun minus(element: @UnsafeVariance E): PersistentCollection<E>
}

/**
 * An immutable [List]
 *
 * All implementers promise that the list won't ever change after instance creation
 */
public interface PersistentList<out E> : PersistentCollection<E> {
    operator fun get(index: Int): E

    fun indexOf(element: @UnsafeVariance E): Int
    fun lastIndexOf(element: @UnsafeVariance E): Int

    fun subList(fromIndex: Int, toIndex: Int): PersistentList<E>

    override fun plus(element: @UnsafeVariance E): PersistentList<E> = plus(element, size)
    fun plus(element: @UnsafeVariance E, index: Int): PersistentList<E>

    override fun plus(collection: PersistentCollection<@UnsafeVariance E>): PersistentList<E> = plus(collection, size)
    fun plus(collection: PersistentCollection<@UnsafeVariance E>, index: Int): PersistentList<E>

    override fun minus(element: @UnsafeVariance E): PersistentList<E>
    fun minusIndex(index: Int): PersistentList<E>
}

interface PersistentSet<out E> : PersistentCollection<E> {
    override fun plus(element: @UnsafeVariance E): PersistentSet<E>
    override fun plus(collection: PersistentCollection<@UnsafeVariance E>): PersistentSet<E>
    override fun minus(element: @UnsafeVariance E): PersistentSet<E>
}

public interface PersistentCollectionFactory {
    fun <E> empty(): PersistentCollection<E>
    fun <E> from(iterable: Iterable<E>): PersistentCollection<E>
}

public interface PersistentListFactory : PersistentCollectionFactory {
    override fun <E> empty(): PersistentList<E>
    override fun <E> from(iterable: Iterable<E>): PersistentList<E>
}

public interface PersistentSetFactory : PersistentCollectionFactory {
    override fun <E> empty(): PersistentSet<E>
    override fun <E> from(iterable: Iterable<E>): PersistentSet<E>
}

public fun <E> PersistentCollectionFactory.of(vararg elements: E): PersistentCollection<E> =
        if (elements.isEmpty()) empty() else from(elements.asIterable())

public fun <E> PersistentListFactory.of(vararg elements: E): PersistentList<E> =
        if (elements.isEmpty()) empty() else from(elements.asIterable())

public fun <E> PersistentSetFactory.of(vararg elements: E): PersistentSet<E> =
        if (elements.isEmpty()) empty() else from(elements.asIterable())
