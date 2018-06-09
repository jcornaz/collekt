package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.*
import io.vavr.collection.List

class VavrList<E>(private val list: List<E>) : AbstractPersistentList<E>() {

    companion object Factory : PersistentListFactory {
        private val empty = VavrList(List.empty<Nothing>())
        override fun <E> empty(): PersistentList<E> = empty

        override fun <E> from(elements: Iterable<E>): PersistentList<E> =
                wrap(List.ofAll(elements))

        private fun <E> wrap(list: List<E>): PersistentList<E> =
                if (list.isEmpty) empty else VavrList(list)
    }

    override val size get() = list.size()
    override val isEmpty get() = list.isEmpty

    override fun get(index: Int): E = list[index]

    override fun iterator(index: Int) = RandomAccessIterator(index, list.size(), list::get)

    override fun slice(fromIndex: Int, toIndex: Int): PersistentList<E> =
            wrap(list.slice(fromIndex, toIndex))

    override fun split(index: Int): Pair<PersistentList<E>, PersistentList<E>> =
            list.splitAt(index).let { wrap(it._1) to wrap(it._2) }

    override fun plus(element: E): PersistentList<E> =
            wrap(list.append(element))

    override fun plus(elements: Traversable<E>): PersistentList<E> {
        if (elements.none()) return this

        return wrap(list.appendAll((elements as? VavrList<E>)?.list ?: elements.asIterable()))
    }

    override fun plus(index: Int, element: E): PersistentList<E> =
            wrap(list.insert(index, element))

    override fun plus(index: Int, elements: Traversable<E>): PersistentList<E> {
        if (elements.none()) return this

        return wrap(list.insertAll(index, (elements as? VavrList<E>)?.list ?: elements.asIterable()))
    }

    override fun minus(element: E): PersistentList<E> =
            wrap(list.remove(element))

    override fun minus(elements: Traversable<E>): PersistentList<E> {
        if (elements.none()) return this

        return wrap(list.removeAll((elements as? VavrList<E>)?.list ?: elements.asIterable()))
    }

    override fun minusIndex(index: Int): PersistentList<E> =
            wrap(list.removeAt(index))
}
