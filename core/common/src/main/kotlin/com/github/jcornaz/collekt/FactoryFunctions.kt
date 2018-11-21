package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.PersistentList
import com.github.jcornaz.collekt.api.PersistentMap
import com.github.jcornaz.collekt.api.PersistentSet

/** Returns an empty [PersistentList] */
public fun <E> emptyPersistentList(): PersistentList<E> = DefaultListFactory.empty()

/** Returns an empty [PersistentSet] */
public fun <E> emptyPersistentSet(): PersistentSet<E> = DefaultSetFactory.empty()

/** Returns an empty [PersistentMap] */
public fun <K, V> emptyPersistentMap(): PersistentMap<K, V> = DefaultMapFactory.empty()

/** Returns a new persistent list containing the given elements */
public fun <E> persistentListOf(vararg elements: E): PersistentList<E> =
        DefaultListFactory.from(elements.asIterable())

/** Returns a new persistent set containing the given elements */
public fun <E> persistentSetOf(vararg elements: E): PersistentSet<E> =
        DefaultSetFactory.from(elements.asIterable())

/** Returns a new immutable map containing the given entries */
public fun <K, V> persistentMapOf(vararg entries: Pair<K, V>): PersistentMap<K, V> =
        DefaultMapFactory.from(entries.asIterable())

/**
 * Returns an persistent list with all elements in this iterable.
 *
 * May create a defensive copy if necessary.
 */
public fun <E> Iterable<E>.toPersistentList(): PersistentList<E> =
        this as? PersistentList<E> ?: DefaultListFactory.from(this)

/**
 * Returns an persistent set with all elements in this iterable.
 *
 * May create a defensive copy if necessary.
 */
public fun <E> Iterable<E>.toPersistentSet(): PersistentSet<E> =
        this as? PersistentSet<E> ?: DefaultSetFactory.from(this)

/**
 * Returns an persistent map with all entries in this map.
 *
 * May create a defensive copy if necessary.
 */
public fun <K, V> Map<K, V>.toPersistentMap(): PersistentMap<K, V> =
        this as? PersistentMap<K, V> ?: DefaultMapFactory.from(this)

/**
 * Returns a new persistent list with all elements in this sequence (in the same order).
 */
public fun <E> Sequence<E>.toPersistentList(): PersistentList<E> = DefaultListFactory.from(asIterable())

/**
 * Returns a new persistent set with all elements in this sequence (in the same order).
 */
public fun <E> Sequence<E>.toPersistentSet(): PersistentSet<E> = DefaultSetFactory.from(asIterable())

/**
 * Returns a new persistent set with all elements in this sequence (in the same order).
 */
public fun <K, V> Sequence<Pair<K, V>>.toPersistentMap(): PersistentMap<K, V> = DefaultMapFactory.from(asIterable())
