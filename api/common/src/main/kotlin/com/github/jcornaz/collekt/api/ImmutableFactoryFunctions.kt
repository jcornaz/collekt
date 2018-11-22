package com.github.jcornaz.collekt.api

private val EmptyImmutableList: ImmutableList<Nothing> = emptyList<Nothing>().asImmutableList()
private val EmptyImmutableSet: ImmutableSet<Nothing> = emptySet<Nothing>().asImmutableSet()
private val EmptyImmutableMap: ImmutableMap<Any?, Nothing> = emptyMap<Any?, Nothing>().asImmutableMap()

/** Returns an empty [ImmutableList] */
public fun <E> emptyImmutableList(): ImmutableList<E> = EmptyImmutableList

/** Returns an empty [ImmutableSet] */
public fun <E> emptyImmutableSet(): ImmutableSet<E> = EmptyImmutableSet

/** Returns an empty [ImmutableMap] */
@Suppress("UNCHECKED_CAST")
public fun <K, V> emptyImmutableMap(): ImmutableMap<K, V> = EmptyImmutableMap as ImmutableMap<K, V>

/** Returns a new immutable list containing the given elements */
public fun <E> immutableListOf(vararg elements: E): ImmutableList<E> =
        elements.toList().asImmutableList()

/** Returns a new immutable set containing the given elements */
public fun <E> immutableSetOf(vararg elements: E): ImmutableSet<E> =
        elements.toHashSet().asImmutableSet()

/** Returns a new immutable map containing the given elements */
public fun <K, V> immutableMapOf(vararg entries: Pair<K, V>): ImmutableMap<K, V> =
        hashMapOf(*entries).asImmutableMap()

/**
 * Returns an immutable list with all elements in this iterable.
 *
 * May create a defensive copy if necessary.
 */
public fun <E> Iterable<E>.toImmutableList(): ImmutableList<E> =
        this as? ImmutableList<E>
                ?: if (this is Collection && isEmpty()) EmptyImmutableList else toList().asImmutableList()

/**
 * Returns an immutable set with all elements in this iterable.
 *
 * May create a defensive copy if necessary.
 */
public fun <E> Iterable<E>.toImmutableSet(): ImmutableSet<E> =
        this as? ImmutableSet<E>
                ?: if (this is Collection && isEmpty()) EmptyImmutableSet else toHashSet().asImmutableSet()

/**
 * Returns an immutable map with all entries in this map.
 *
 * May create a defensive copy if necessary.
 */
public fun <K, V> Map<K, V>.toImmutableMap(): ImmutableMap<K, V> =
        this as? ImmutableMap<K, V>
                ?: if (isEmpty()) emptyImmutableMap() else toMap(HashMap()).asImmutableMap()

/**
 * Returns a new immutable list with all elements in this sequence (in the same order).
 */
public fun <E> Sequence<E>.toImmutableList(): ImmutableList<E> = toList().asImmutableList()

/**
 * Returns a new immutable set with all elements in this sequence (in the same order).
 */
public fun <E> Sequence<E>.toImmutableSet(): ImmutableSet<E> = toSet().asImmutableSet()

/**
 * Returns a new immutable set with all elements in this sequence (in the same order).
 */
public fun <K, V> Sequence<Pair<K, V>>.toImmutableMap(): ImmutableMap<K, V> = toMap(HashMap()).asImmutableMap()
