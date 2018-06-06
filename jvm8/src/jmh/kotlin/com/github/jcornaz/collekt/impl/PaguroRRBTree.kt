package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.PersistentCollection
import com.github.jcornaz.collekt.PersistentList
import com.github.jcornaz.collekt.PersistentListFactory
import com.github.jcornaz.collekt.fold
import io.vavr.collection.List
import org.organicdesign.fp.StaticImports
import org.organicdesign.fp.collections.RrbTree

class PaguroRRBTree<E>(private val tree: RrbTree<E>) : AbstractPersistentList<E>() {
    override val factory get() = Factory

    override val size: Int get() = tree.size
    override val isEmpty: Boolean get() = tree.isEmpty()

    init {
        List.empty<Nothing>()
    }

    override fun createSubList(fromIndex: Int, toIndex: Int): PersistentList<E> =
            PaguroRRBTree(tree.split(toIndex)._1().split(fromIndex)._2())

    override fun plus(element: E): PersistentList<E> =
            PaguroRRBTree(tree.append(element))

    override fun insert(element: E, index: Int): PersistentList<E> =
            PaguroRRBTree(tree.insert(index, element))

    override fun insert(collection: PersistentCollection<E>, index: Int): PersistentList<E> {
        val split = tree.split(index)
        if (collection is PaguroRRBTree<E>) return PaguroRRBTree(split._1().join(collection.tree).join(split._2()))

        return PaguroRRBTree(collection.fold(split._1()) { acc, elt -> acc.append(elt) }.join(split._2()))
    }

    override fun concat(collection: PersistentCollection<E>): PersistentList<E> {
        if (collection is PaguroRRBTree<E>) return PaguroRRBTree(tree.join(collection.tree))

        return PaguroRRBTree(collection.fold(tree) { acc, elt -> acc.append(elt) })
    }

    override fun remove(element: E): PersistentList<E> =
            PaguroRRBTree(tree.without(tree.indexOf(element)))

    override fun removeIndex(index: Int): PersistentList<E> =
            PaguroRRBTree(tree.without(index))

    override fun contains(element: E): Boolean = tree.contains(element)
    override fun iterator(): Iterator<E> = tree.iterator()
    override fun get(index: Int): E = tree[index]

    override fun indexOf(element: E): Int = tree.indexOf(element)
    override fun lastIndexOf(element: E): Int = tree.lastIndexOf(element)

    companion object Factory : PersistentListFactory {
        override val empty = PaguroRRBTree(StaticImports.rrb<Nothing>())

        override fun <E> from(iterable: Iterable<E>): PersistentList<E> =
                iterable.fold(StaticImports.rrb<E>()) { acc, elt -> acc.append(elt) }
                        .takeUnless { it.isEmpty() }
                        ?.let { PaguroRRBTree(it) }
                        ?: empty
    }
}
