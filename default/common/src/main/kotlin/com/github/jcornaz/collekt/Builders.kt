package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.PersistentList

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
public fun <E> Iterable<E>.toPersistentList(): PersistentList<E> =
        this as? PersistentList<E> ?: DefaultListFactory.from(this)

/**
 * Returns a new immutable list with all elements in this sequence (in the same order).
 */
public fun <E> Sequence<E>.toPersistentList(): PersistentList<E> = DefaultListFactory.from(asIterable())
