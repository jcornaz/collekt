package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.AbstractPersistentList
import io.vavr.collection.List

class VavrList<E>(private val list: List<E>) : AbstractPersistentList<E>() {
    override val factory get() = Factory

    override val size get() = list.size()
    override val isEmpty get() = list.isEmpty

    override fun createSubList(fromIndex: Int, toIndex: Int): PersistentList<E> =
            VavrList(list.subSequence(fromIndex, toIndex))

    override fun insert(element: E, index: Int): PersistentList<E> =
            VavrList(list.insert(index, element))

    override fun insert(collection: PersistentCollection<E>, index: Int): PersistentList<E> =
            VavrList(list.insertAll(index, (collection as? VavrList<E>)?.list
                    ?: collection.asCollection()))

    override fun concat(collection: PersistentCollection<E>): PersistentList<E> =
            VavrList(list.appendAll((collection as? VavrList<E>)?.list
                    ?: collection.asCollection()))

    override fun remove(element: E): PersistentList<E> =
            VavrList(list.remove(element))

    override fun removeIndex(index: Int): PersistentList<E> =
            VavrList(list.removeAt(index))

    override fun contains(element: E): Boolean = list.contains(element)

    override fun iterator(): Iterator<E> = list.iterator()

    override fun get(index: Int): E = list[index]

    override fun indexOf(element: E): Int = list.indexOf(element)

    override fun lastIndexOf(element: E): Int = list.lastIndexOf(element)

    companion object Factory : PersistentListFactory {
        override val empty = VavrList(List.empty<Nothing>())

        override fun <E> from(iterable: Iterable<E>): PersistentList<E> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}
