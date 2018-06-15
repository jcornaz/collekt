package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.PersistentList
import com.github.jcornaz.collekt.PersistentListFactory
import io.vavr.collection.List

class VavrList<E>(private val list: List<E>) : AbstractList<E>(), PersistentList<E> {

    companion object Factory : PersistentListFactory {
        private val empty = VavrList(List.empty<Nothing>())
        override fun <E> empty(): PersistentList<E> = empty

        override fun <E> from(elements: Iterable<E>): PersistentList<E> {
            val list = List.ofAll(elements)
            return if (list.isEmpty) empty else VavrList(list)
        }
    }

    override val size get() = list.size()
    override fun isEmpty(): Boolean = list.isEmpty

    override fun get(index: Int): E = list[index]

    override fun iterator(): Iterator<E> = list.iterator()

    override fun subList(fromIndex: Int, toIndex: Int): PersistentList<E> =
            wrap(list.slice(fromIndex, toIndex))

    override fun split(index: Int): Pair<PersistentList<E>, PersistentList<E>> =
            list.splitAt(index).let { wrap(it._1) to wrap(it._2) }

    override fun with(index: Int, element: E): PersistentList<E> =
            wrap(list.removeAt(index).insert(index, element))

    override fun plus(element: E): PersistentList<E> =
            wrap(list.append(element))

    override fun plus(elements: Iterable<E>): PersistentList<E> =
            wrap(list.appendAll((elements as? VavrList<E>)?.list ?: elements))

    override fun plus(index: Int, element: E): PersistentList<E> =
            wrap(list.insert(index, element))

    override fun plus(index: Int, elements: Iterable<E>): PersistentList<E> =
            wrap(list.insertAll(index, (elements as? VavrList<E>)?.list ?: elements))

    override fun minus(element: E): PersistentList<E> =
            wrap(list.remove(element))

    override fun minus(elements: Iterable<E>): PersistentList<E> =
            wrap(list.removeAll((elements as? VavrList<E>)?.list ?: elements))

    override fun minusIndex(index: Int): PersistentList<E> =
            wrap(list.removeAt(index))

    private fun wrap(newList: List<E>): PersistentList<E> = when {
        newList === list -> this
        newList.isEmpty -> empty
        else -> VavrList(newList)
    }
}
