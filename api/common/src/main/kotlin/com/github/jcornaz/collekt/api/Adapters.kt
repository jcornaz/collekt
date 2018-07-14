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

private class ImmutableCollectionAdapter<out E>(private val actualCollection: Collection<E>) : AbstractCollection<E>(), ImmutableCollection<E>, Collection<E> by actualCollection {
    override fun contains(element: @UnsafeVariance E): Boolean = actualCollection.contains(element)
    override fun isEmpty(): Boolean = actualCollection.isEmpty()
    override fun containsAll(elements: Collection<@UnsafeVariance E>): Boolean = actualCollection.containsAll(elements)
}

private class ImmutableListAdapter<out E>(private val actualList: List<E>) : AbstractList<E>(), ImmutableList<E>, List<E> by actualList {

    override fun subList(fromIndex: Int, toIndex: Int): ImmutableList<E> =
            ImmutableListAdapter(actualList.subList(fromIndex, toIndex))

    override fun split(index: Int): Pair<ImmutableList<E>, ImmutableList<E>> =
            subList(0, index) to subList(index, actualList.size)

    override fun contains(element: @UnsafeVariance E): Boolean {
        return actualList.contains(element)
    }

    override fun containsAll(elements: Collection<@UnsafeVariance E>): Boolean {
        return actualList.containsAll(elements)
    }

    override fun indexOf(element: @UnsafeVariance E): Int {
        return actualList.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return actualList.isEmpty()
    }

    override fun iterator(): Iterator<E> {
        return actualList.iterator()
    }

    override fun lastIndexOf(element: @UnsafeVariance E): Int {
        return actualList.lastIndexOf(element)
    }

    override fun listIterator(): ListIterator<E> {
        return actualList.listIterator()
    }

    override fun listIterator(index: Int): ListIterator<E> {
        return actualList.listIterator(index)
    }
}

private class ImmutableSetAdapter<out E>(private val actualSet: Set<E>) : AbstractSet<E>(), ImmutableSet<E>, Set<E> by actualSet {
    override fun contains(element: @UnsafeVariance E): Boolean {
        return actualSet.contains(element)
    }

    override fun containsAll(elements: Collection<@UnsafeVariance E>): Boolean {
        return actualSet.containsAll(elements)
    }

    override fun isEmpty(): Boolean {
        return actualSet.isEmpty()
    }
}