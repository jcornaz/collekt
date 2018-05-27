package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.EmptyImmutableList
import com.github.jcornaz.collekt.impl.EmptyImmutableSet

/**
 * Return an empty [ImmutableList]
 */
fun <E> emptyImmutableList(): ImmutableList<E> = EmptyImmutableList

/**
 * Return an empty [ImmutableSet]
 */
fun <E> emptyImmutableSet(): ImmutableSet<E> = EmptyImmutableSet
