package com.github.jcornaz.collekt

/**
 * Factory responsible for the creation of [PersistentCollection]
 */
public interface PersistentCollectionFactory {

    /** Returns an empty [PersistentCollection] */
    fun <E> empty(): PersistentCollection<E>

    /** Returns a [PersistentCollection] containing [elements]*/
    fun <E> from(elements: Iterable<E>): PersistentCollection<E>
}

/**
 * Factory responsible for the creation of [PersistentList]
 */
public interface PersistentListFactory : PersistentCollectionFactory {

    /** Returns an empty [PersistentList] */
    override fun <E> empty(): PersistentList<E>

    /** Returns a [PersistentList] containing [elements] */
    override fun <E> from(elements: Iterable<E>): PersistentList<E>
}

/** Returns a [PersistentCollection] containing [elements] */
public fun <E> PersistentCollectionFactory.of(vararg elements: E): PersistentCollection<E> =
        if (elements.isEmpty()) empty() else from(elements.asIterable())

/** Returns a [PersistentList] containing [elements] */
public fun <E> PersistentListFactory.of(vararg elements: E): PersistentList<E> =
        if (elements.isEmpty()) empty() else from(elements.asIterable())

internal expect val DefaultListFactory: PersistentListFactory

/**
 * Returns an empty [PersistentList]
 */
public fun <E> emptyPersistentList(): PersistentList<E> = DefaultListFactory.empty()

/**
 * Returns a new immutable list containing the given elements
 */
public fun <E> persistentListOf(vararg values: E): PersistentList<E> =
        if (values.isEmpty()) DefaultListFactory.empty() else DefaultListFactory.from(values.asIterable())

/**
 * Returns a new immutable list all elements in this iterable.
 *
 * May create a defensive copy if necessary
 */
public fun <E> Iterable<E>.toPersistentList(): PersistentList<E> = DefaultListFactory.from(this)

/**
 * Returns a new immutable list with all elements in this sequence (in the same order).
 */
public fun <E> Sequence<E>.toPersistentList(): PersistentList<E> = DefaultListFactory.from(asIterable())

/**
 * Returns an immutable list with all elements in this [Traversable].
 */
public fun <E> Traversable<E>.toPersistentList(): PersistentList<E> =
        this as? PersistentList<E> ?: DefaultListFactory.from(asIterable())
