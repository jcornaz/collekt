package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.EmptyImmutableList
import com.github.jcornaz.collekt.impl.EmptyImmutableSet
import com.github.jcornaz.collekt.impl.ImmutableListAdapter

/**
 * Returns an empty [ImmutableList]
 */
public fun <E> emptyImmutableList(): ImmutableList<E> = EmptyImmutableList

/**
 * Returns an empty [ImmutableSet]
 */
public fun <E> emptyImmutableSet(): ImmutableSet<E> = EmptyImmutableSet

/**
 * Returns a new immutable list containing the given elements
 */
public fun <E> immutableListOf(vararg values: E): ImmutableList<E> =
        if (values.isEmpty()) emptyImmutableList() else ImmutableListAdapter(values.toList())

/**
 * Returns a new immutable list all elements in this iterable.
 *
 * May create a defensive copy if necessary
 */
public fun <E> Iterable<E>.toImmutableList(): ImmutableList<E> = when {
    this is ImmutableList<E> -> this
    none() -> emptyImmutableList()
    else -> ImmutableListAdapter(toList())
}

/**
 * Returns a new immutable list all elements in this sequence.
 */
public fun <E> Sequence<E>.toImmutableList(): ImmutableList<E> = toList().asImmutableList()

/**
 * Returns an immutable list backed by this list.
 *
 * No defensive copy will be made. By calling this function you promise to not update the source list.
 *
 * Mutating the source list after the call of this method will cause undefined behaviors.
 */
public fun <E> List<E>.asImmutableList(): ImmutableList<E> = when {
    this is ImmutableList<E> -> this
    none() -> emptyImmutableList()
    else -> ImmutableListAdapter(this)
}
