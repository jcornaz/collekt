package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.PersistentList
import com.github.jcornaz.collekt.api.PersistentSet

/** Returns an empty [PersistentList] */
public fun <E> emptyPersistentList(): PersistentList<E> = DefaultListFactory.empty()

/** Returns an empty [PersistentSet] */
public fun <E> emptyPersistentSet(): PersistentSet<E> = DefaultSetFactory.empty()

/** Returns a new immutable list containing the given elements */
public fun <E> persistentListOf(vararg values: E): PersistentList<E> =
    DefaultListFactory.from(values.asIterable())

/** Returns a new immutable set containing the given elements */
public fun <E> persistentSetOf(vararg values: E): PersistentSet<E> =
    DefaultSetFactory.from(values.asIterable())

/**
 * Returns an immutable list with all elements in this iterable.
 *
 * May create a defensive copy if necessary.
 */
public fun <E> Iterable<E>.toPersistentList(): PersistentList<E> =
    this as? PersistentList<E> ?: DefaultListFactory.from(this)

/**
 * Returns an immutable set with all elements in this iterable.
 *
 * May create a defensive copy if necessary.
 */
public fun <E> Iterable<E>.toPersistentSet(): PersistentSet<E> =
    this as? PersistentSet<E> ?: DefaultSetFactory.from(this)

/**
 * Returns a new immutable list with all elements in this sequence (in the same order).
 */
public fun <E> Sequence<E>.toPersistentList(): PersistentList<E> = DefaultListFactory.from(asIterable())

/**
 * Returns a new immutable set with all elements in this sequence (in the same order).
 */
public fun <E> Sequence<E>.toPersistentSet(): PersistentSet<E> = DefaultSetFactory.from(asIterable())
