package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.EmptyImmutableList
import com.github.jcornaz.collekt.impl.EmptyImmutableSet
import com.github.jcornaz.collekt.impl.ImmutableListAdapter

/**
 * Return an empty [ImmutableList]
 */
fun <E> emptyImmutableList(): ImmutableList<E> = EmptyImmutableList

/**
 * Return an empty [ImmutableSet]
 */
fun <E> emptyImmutableSet(): ImmutableSet<E> = EmptyImmutableSet

fun <E> immutableListOf(vararg values: E): ImmutableList<E> =
        if (values.isEmpty()) emptyImmutableList() else ImmutableListAdapter(values.toList())

fun <E> Iterable<E>.toImmutableList(): ImmutableList<E> = when {
    this is ImmutableList<E> -> this
    none() -> emptyImmutableList()
    else -> ImmutableListAdapter(toList())
}
