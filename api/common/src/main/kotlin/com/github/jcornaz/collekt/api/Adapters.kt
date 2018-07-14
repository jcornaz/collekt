package com.github.jcornaz.collekt.api

/**
 * Returns an [ImmutableList] backed by this instance
 *
 * **ATTENTION:** Make sure the actual list cannot be modified in any way.
 */
public fun <E> Collection<E>.asImmutableCollection(): ImmutableCollection<E> = ImmutableCollectionAdapter(this)

/**
 * Returns an [ImmutableList] backed by this instance
 *
 * **ATTENTION:** Make sure the actual list cannot be modified in any way.
 */
public fun <E> List<E>.asImmutableList(): ImmutableList<E> =
        this as? ImmutableList ?: ImmutableListAdapter(this)

/**
 * Returns an [ImmutableSet] backed by this instance
 *
 * **ATTENTION:** Make sure the actual list cannot be modified in any way.
 */
public fun <E> Set<E>.asImmutableSet(): ImmutableSet<E> =
        this as? ImmutableSet ?: ImmutableSetAdapter(this)

private class ImmutableCollectionAdapter<out E>(private val actualCollection: Collection<E>) : ImmutableCollection<E>, Collection<E> by actualCollection

private class ImmutableListAdapter<out E>(private val actualList: List<E>) : ImmutableList<E>, List<E> by actualList {
    override fun subList(fromIndex: Int, toIndex: Int): ImmutableList<E> =
            ImmutableListAdapter(actualList.subList(fromIndex, toIndex))

    override fun split(index: Int): Pair<ImmutableList<E>, ImmutableList<E>> =
            subList(0, index) to subList(index, actualList.size)
}

private class ImmutableSetAdapter<out E>(private val actualSet: Set<E>) : ImmutableSet<E>, Set<E> by actualSet