package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.PersistentList
import com.github.jcornaz.collekt.PersistentListFactory
import org.organicdesign.fp.StaticImports
import org.organicdesign.fp.collections.RrbTree

class PaguroRRBTree<E>(private val tree: RrbTree<E>) : AbstractList<E>(), PersistentList<E> {

    companion object Factory : PersistentListFactory {
        private val empty = PaguroRRBTree(StaticImports.rrb<Nothing>())
        override fun <E> empty(): PersistentList<E> = empty

        override fun <E> from(elements: Iterable<E>): PersistentList<E> =
                elements as? PaguroRRBTree<E>
                        ?: elements.fold(StaticImports.mutableRrb<E>()) { acc, elt -> acc.append(elt) }.immutable()
                                .let { if (it.isEmpty()) empty else PaguroRRBTree(it) }
    }

    override val size: Int get() = tree.size
    override fun isEmpty(): Boolean = tree.isEmpty()

    override fun get(index: Int): E = tree[index]

    override fun iterator() = tree.iterator()
    override fun listIterator(): ListIterator<E> = tree.listIterator()
    override fun listIterator(index: Int): ListIterator<E> = tree.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): PersistentList<E> =
            wrap(tree.split(toIndex)._1().split(fromIndex)._2())

    override fun split(index: Int): Pair<PersistentList<E>, PersistentList<E>> =
            tree.split(index).let { wrap(it._1()) to wrap(it._2()) }

    override fun with(index: Int, element: E): PersistentList<E> =
            wrap(tree.replace(index, element))

    override fun plus(element: E): PersistentList<E> =
            wrap(tree.append(element))

    override fun plus(elements: Iterable<E>): PersistentList<E> {
        val newTree = when (elements) {
            is PaguroRRBTree<E> -> tree.join(elements.tree)
            is RrbTree<E> -> tree.join(elements)
            else -> elements.fold(tree) { acc, elt -> acc.append(elt) }
        }

        return wrap(newTree)
    }

    override fun plus(index: Int, element: E): PersistentList<E> =
            wrap(tree.insert(index, element))

    override fun plus(index: Int, elements: Iterable<E>): PersistentList<E> {
        val split = tree.split(index)

        val newTree = when (elements) {
            is PaguroRRBTree<E> -> split._1().join(elements.tree).join(split._2())
            is RrbTree<E> -> split._1().join(elements).join(split._2())
            else -> elements.fold(split._1()) { acc, elt -> acc.append(elt) }.join(split._2())
        }

        return wrap(newTree)
    }

    override fun minus(element: E): PersistentList<E> =
            tree.indexOf(element).let { if (it < 0) this else minusIndex(it) }

    override fun minus(elements: Iterable<E>): PersistentList<E> {
        val newTree = elements.fold(tree) { acc, elt ->
            val index = acc.indexOf(elt)
            if (index < 0) acc else acc.without(index)
        }

        return wrap(newTree)
    }

    override fun minusIndex(index: Int): PersistentList<E> =
            wrap(tree.without(index))

    private fun wrap(newTree: RrbTree<E>): PersistentList<E> = when {
        newTree === tree -> this
        newTree.isEmpty() -> empty
        else -> PaguroRRBTree(newTree)
    }
}
