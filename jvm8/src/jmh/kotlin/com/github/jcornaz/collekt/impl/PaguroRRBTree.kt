package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.*
import org.organicdesign.fp.StaticImports
import org.organicdesign.fp.collections.RrbTree

class PaguroRRBTree<E>(private val tree: RrbTree<E>) : AbstractPersistentList<E>() {

    companion object Factory : PersistentListFactory {
        private val empty = PaguroRRBTree(StaticImports.rrb<Nothing>())
        override fun <E> empty(): PersistentList<E> = empty

        override fun <E> from(elements: Iterable<E>): PersistentList<E> =
                wrap(elements.fold(StaticImports.rrb<E>()) { acc, elt -> acc.append(elt) })

        private fun <E> wrap(tree: RrbTree<E>): PersistentList<E> =
                tree.takeUnless(RrbTree<E>::isEmpty)?.let(::PaguroRRBTree) ?: empty()
    }

    override val size: Int get() = tree.size
    override val isEmpty: Boolean get() = tree.isEmpty()

    override fun get(index: Int): E = tree[index]

    override fun iterator(index: Int): ListIterator<E> = tree.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): PersistentList<E> =
            wrap(tree.split(toIndex)._1().split(fromIndex)._2())

    override fun split(index: Int): Pair<ImmutableList<E>, PersistentList<E>> =
            tree.split(index).let { wrap(it._1()) to wrap(it._2()) }

    override fun plus(element: E): PersistentList<E> =
            wrap(tree.append(element))

    override fun plus(elements: Traversable<E>): PersistentList<E> {
        if (elements.none()) return this
        if (elements is PaguroRRBTree<E>) return wrap(tree.join(elements.tree))

        return wrap(elements.fold(tree) { acc, elt -> acc.append(elt) })
    }

    override fun plus(index: Int, element: E): PersistentList<E> =
            wrap(tree.insert(index, element))

    override fun plus(index: Int, elements: Traversable<E>): PersistentList<E> {
        if (elements.none()) return this

        val split = tree.split(index)

        if (elements is PaguroRRBTree<E>) return wrap(split._1().join(elements.tree).join(split._2()))

        return wrap(elements.fold(split._1()) { acc, elt -> acc.append(elt) }.join(split._2()))
    }

    override fun minus(element: E): PersistentList<E> =
            tree.indexOf(element).let { if (it < 0) this else minusIndex(it) }

    override fun minus(elements: Traversable<E>): PersistentList<E> =
            wrap(elements.fold(tree) { acc, elt ->
                val index = acc.indexOf(elt)
                if (index < 0) acc else acc.without(index)
            })

    override fun minusIndex(index: Int): PersistentList<E> =
            wrap(tree.without(index))
}
