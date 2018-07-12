package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.ImmutableCollection
import com.github.jcornaz.collekt.api.PersistentList

/**
 * Returns a persistent list containing only elements matching the given [predicate].
 */
public fun <T> ImmutableCollection<T>.filter(predicate: (T) -> Boolean): PersistentList<T> =
        asSequence().filter(predicate).toPersistentList()

/**
 * Returns a persistent list containing all elements that are not null.
 */
public fun <T : Any> ImmutableCollection<T?>.filterNotNull(): PersistentList<T> =
        asSequence().filterNotNull().toPersistentList()

/**
 * Returns a persistent list containing all elements that are instances of specified type parameter R.
 */
public inline fun <T, reified R> ImmutableCollection<T>.filterIsInstance(): PersistentList<R> =
        asSequence().filterIsInstance<R>().toPersistentList()

/**
 * Returns a persistent list containing the results of applying the given transform function to each element in the original collection.
 */
public fun <T, R> ImmutableCollection<T>.map(transform: (T) -> R): PersistentList<R> =
        asSequence().map(transform).toPersistentList()

/**
 * Returns a persistent list containing only the non-null results of applying the given transform function to each element in the original collection.
 */
public fun <T, R : Any> ImmutableCollection<T>.mapNotNull(transform: (T) -> R?): PersistentList<R> =
        asSequence().mapNotNull(transform).toPersistentList()

/**
 * Returns a single persistent list of all elements from results of transform function being invoked on each element of original collection.
 */
public fun <T, R> ImmutableCollection<T>.flatMap(transform: (T) -> Iterable<R>): PersistentList<R> =
        asSequence().flatMap { transform(it).asSequence() }.toPersistentList()
