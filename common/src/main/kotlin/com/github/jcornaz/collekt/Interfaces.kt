package com.github.jcornaz.collekt

/**
 * An immutable [Collection].
 *
 * All implementers promise that the collection won't ever change after instance creation
 */
interface ImmutableCollection<out E> : Collection<E>

/**
 * An immutable [List]
 *
 * All implementers promise that the list won't ever change after instance creation
 */
interface ImmutableList<out E> : ImmutableCollection<E>, List<E> {
    override fun subList(fromIndex: Int, toIndex: Int): ImmutableList<E>
}

/**
 * An immutable [Set]
 *
 * All implementers promise that the list won't ever change after instance creation
 */
interface ImmutableSet<out E> : ImmutableCollection<E>, Set<E>
