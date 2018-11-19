package com.github.jcornaz.collekt.stdlib

import com.github.jcornaz.collekt.api.PersistentList
import com.github.jcornaz.collekt.api.PersistentListFactory

/**
 * Naive implementation using kotlin standard lists and operators.
 *
 * This is the worse persistent implementation possible, and performances of all mutation method are expected to be really bad.
 *
 * It is only useful for comparison purposes with other persistent implementations
 */
public class StdlibList<out E> internal constructor(private val list: List<E>) : AbstractList<E>(), PersistentList<E> {

    companion object Factory : PersistentListFactory {
        private val empty = StdlibList(emptyList<Nothing>())

        override fun <E> empty(): PersistentList<E> = empty
        override fun <E> from(elements: Iterable<E>) =
                elements as? StdlibList<E>
                        ?: wrap(elements.toList())

        private fun <E> Iterable<E>.unwrap(): Iterable<E> =
                (this as? StdlibList<E>)?.list ?: this

        private fun <E> wrap(list: List<E>): PersistentList<E> =
                if (list.isEmpty()) empty else StdlibList(list)
    }

    override val size: Int get() = list.size
    override fun isEmpty() = list.isEmpty()

    override fun get(index: Int): E = list[index]

    override fun iterator(): Iterator<E> = list.iterator()
    override fun listIterator(index: Int): ListIterator<E> = list.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): PersistentList<E> {
        if (fromIndex < 0 || toIndex > list.size || fromIndex > toIndex) throw IndexOutOfBoundsException("fromIndex: $fromIndex, toIndex: $toIndex, size: ${list.size}")
        return wrap(list.subList(fromIndex, toIndex))
    }

    override fun split(index: Int): Pair<PersistentList<E>, PersistentList<E>> =
            subList(0, index) to subList(index, list.size)

    override fun with(index: Int, element: @UnsafeVariance E): PersistentList<E> =
            wrap(list.toMutableList().apply { set(index, element) })

    override fun plus(element: @UnsafeVariance E): PersistentList<E> =
            wrap(list + element)

    override fun plus(elements: Iterable<@UnsafeVariance E>): PersistentList<E> =
            wrap(list + elements.unwrap())

    override fun plus(index: Int, element: @UnsafeVariance E): PersistentList<E> =
            wrap(list.subList(0, index) + element + list.subList(index, list.size))

    override fun plus(index: Int, elements: Iterable<@UnsafeVariance E>): PersistentList<E> =
            wrap(list.subList(0, index) + elements.unwrap() + list.subList(index, list.size))

    override fun minus(element: @UnsafeVariance E): PersistentList<E> =
            wrap(list - element)

    override fun minus(elements: Iterable<@UnsafeVariance E>): PersistentList<E> =
            wrap(list - elements.unwrap())

    override fun minusIndex(index: Int): PersistentList<E> {
        if (index < 0 || index >= list.size) throw IndexOutOfBoundsException("index: $index, size: ${list.size}")

        return wrap(list.subList(0, index) + list.subList(index + 1, list.size))
    }

    override fun empty(): PersistentList<E> = empty
}
