package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.*

/**
 * Naive implementation using kotlin standard lists and operators.
 *
 * This is the worse persistent implementation possible, and performances of all mutation method are expected to be really bad.
 */
public class KotlinList<E>(private val list: List<E>) : AbstractPersistentList<E>() {

    companion object Factory : PersistentListFactory {
        private val empty = KotlinList(emptyList<Nothing>())

        override fun <E> empty(): PersistentList<E> = empty
        override fun <E> from(iterable: Iterable<E>) = KotlinList(iterable.toList())
    }

    override val size: Int get() = list.size
    override val isEmpty: Boolean get() = list.isEmpty()

    override fun get(index: Int): E = list[index]

    override fun iterator(index: Int): ListIterator<E> = list.listIterator(index)

    override fun range(fromIndex: Int, toIndex: Int): PersistentList<E> =
            wrap(list.subList(fromIndex, toIndex))

    override fun split(index: Int): Pair<ImmutableList<E>, PersistentList<E>> =
            wrap(list.subList(0, index)) to wrap(list.subList(index, list.size))

    override fun plus(element: E): PersistentList<E> =
            wrap(list + element)

    override fun plus(elements: Traversable<E>): PersistentList<E> =
            wrap(list + elements.unwrap())

    override fun plus(index: Int, element: E): PersistentList<E> =
            wrap(list.subList(0, index) + element + list.subList(index, list.size))

    override fun plus(index: Int, elements: Traversable<E>): PersistentList<E> =
            wrap(list.subList(0, index) + elements.unwrap())

    override fun minus(element: E): PersistentList<E> =
            wrap(list - element)

    override fun minus(elements: Traversable<E>): PersistentList<E> =
            wrap(list - elements.unwrap())

    override fun minusIndex(index: Int): PersistentList<E> =
            wrap(list.subList(0, index) + list.subList(index + 1, list.size))

    private fun Traversable<E>.unwrap(): Iterable<E> =
            (this as? KotlinList<E>)?.list ?: asIterable()

    private fun wrap(list: List<E>): PersistentList<E> =
            list.takeUnless(List<E>::isEmpty)?.let(::KotlinList) ?: KotlinList.empty()
}
