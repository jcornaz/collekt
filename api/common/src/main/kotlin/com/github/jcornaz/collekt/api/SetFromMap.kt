package com.github.jcornaz.collekt.api

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
