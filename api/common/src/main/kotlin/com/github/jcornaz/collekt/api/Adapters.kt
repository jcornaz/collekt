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

/**
 * Adapter which allows to use a [PersistentMap] as a [PersistentSet]
 */
public class SetFromMap<out E>(private val map: PersistentMap<E, *>) : AbstractSet<E>(), PersistentSet<E> {

    override val size: Int get() = map.size

    override fun contains(element: @UnsafeVariance E): Boolean = element in map
    override fun isEmpty(): Boolean = map.isEmpty()

    override fun iterator(): Iterator<E> = map.keys.iterator()

    override fun plus(element: @UnsafeVariance E): PersistentSet<E> =
            SetFromMap(map.plus(element, Unit))

    override fun plus(elements: Iterable<@UnsafeVariance E>): PersistentSet<E> =
            SetFromMap(elements.fold(map) { acc, elt -> acc.plus(elt, Unit) })

    override fun minus(element: @UnsafeVariance E): PersistentSet<E> =
            if (element !in map) this else SetFromMap(map - element)

    override fun minus(elements: Iterable<@UnsafeVariance E>): PersistentSet<E> =
            SetFromMap(map - elements)
}

private class ImmutableCollectionAdapter<out E>(private val actualCollection: Collection<E>) : AbstractCollection<E>(), ImmutableCollection<E> {
    override val size: Int get() = actualCollection.size
    override fun isEmpty(): Boolean = actualCollection.isEmpty()

    override fun iterator(): Iterator<E> = actualCollection.iterator()

    override fun contains(element: @UnsafeVariance E): Boolean = actualCollection.contains(element)
    override fun containsAll(elements: Collection<@UnsafeVariance E>): Boolean = actualCollection.containsAll(elements)
}

private class ImmutableListAdapter<out E>(private val actualList: List<E>) : AbstractList<E>(), ImmutableList<E> {
    override val size: Int get() = actualList.size

    override fun isEmpty(): Boolean = actualList.isEmpty()

    override fun get(index: Int): E = actualList[index]

    override fun iterator(): Iterator<E> = actualList.iterator()
    override fun listIterator(): ListIterator<E> = actualList.listIterator()
    override fun listIterator(index: Int): ListIterator<E> = actualList.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): ImmutableList<E> =
            ImmutableListAdapter(actualList.subList(fromIndex, toIndex))

    override fun split(index: Int): Pair<ImmutableList<E>, ImmutableList<E>> =
            subList(0, index) to subList(index, actualList.size)

    override fun contains(element: @UnsafeVariance E): Boolean = actualList.contains(element)
}

private class ImmutableSetAdapter<out E>(private val actualSet: Set<E>) : AbstractSet<E>(), ImmutableSet<E> {
    override val size: Int get() = actualSet.size

    override fun isEmpty(): Boolean = actualSet.isEmpty()

    override fun contains(element: @UnsafeVariance E): Boolean = actualSet.contains(element)

    override fun iterator(): Iterator<E> = actualSet.iterator()
}
