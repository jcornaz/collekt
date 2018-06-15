package com.github.jcornaz.collekt

import immutable.List

internal class ImmutableJsList<E>(private val list: List<E>) : AbstractList<E>(), PersistentList<E> {
    companion object Factory : PersistentListFactory {
        private val empty = ImmutableJsList<Nothing>(List())

        override fun <E> empty(): PersistentList<E> = empty

        override fun <E> from(elements: Iterable<E>): PersistentList<E> {
            val result = List<E>().withMutations {
                elements.fold(it) { acc, elt -> acc.push(elt) }
            }

            return if (result.isEmpty()) empty else ImmutableJsList(result)
        }
    }

    override val size: Int get() = list.size.toInt()

    override fun isEmpty(): Boolean = list.isEmpty()

    override fun get(index: Int): E = list.get(index)

    override fun subList(fromIndex: Int, toIndex: Int): PersistentList<E> =
            wrap(list.slice(fromIndex, toIndex).toList())

    override fun split(index: Int): Pair<PersistentList<E>, PersistentList<E>> =
            subList(0, index) to subList(index, size)

    override fun with(index: Int, element: E): PersistentList<E> =
            wrap(list.set(index, element))

    override fun plus(index: Int, element: E): PersistentList<E> =
            wrap(list.insert(index, element))

    override fun plus(index: Int, elements: Iterable<E>): PersistentList<E> {
        return wrap(list.slice(0, index).toList().withMutations {
            elements.fold(it) { acc, elt -> acc.push(elt) }
        }.concat(list.slice(index, list.size)).toList())
    }

    override fun plus(element: E): PersistentList<E> =
            wrap(list.push(element))

    override fun plus(elements: Iterable<E>): PersistentList<E> =
            wrap(list.withMutations { lst -> elements.fold(lst) { acc, elt -> acc.push(elt) } })

    override fun minus(element: E): PersistentList<E> =
            list.indexOf(element).toInt().let { if (it < 0) this else minusIndex(it) }

    override fun minus(elements: Iterable<E>): PersistentList<E> {
        val newList = elements.fold(list) { acc, elt ->
            val index = acc.indexOf(elt).toInt()
            if (index < 0) acc else acc.delete(index)
        }

        return wrap(newList)
    }

    override fun minusIndex(index: Int): PersistentList<E> =
            wrap(list.remove(index))

    private fun wrap(newList: List<E>): PersistentList<E> = when {
        newList === list -> this
        newList.isEmpty() -> empty
        else -> ImmutableJsList(list)
    }
}
