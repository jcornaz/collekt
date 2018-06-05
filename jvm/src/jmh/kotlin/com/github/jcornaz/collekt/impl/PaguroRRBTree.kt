package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.PersistentCollection
import com.github.jcornaz.collekt.PersistentList
import com.github.jcornaz.collekt.PersistentListFactory
import com.github.jcornaz.collekt.forEach
import org.organicdesign.fp.StaticImports
import org.organicdesign.fp.collections.RrbTree

class PaguroRRBTree<E>(private val tree: RrbTree<E>) : AbstractPersistentList<E>() {
    override val factory get() = Factory

    override val size: Int get() = tree.size
    override val isEmpty: Boolean get() = tree.isEmpty()

    override fun createSubList(fromIndex: Int, toIndex: Int): PersistentList<E> =
            PaguroRRBTree(tree.split(fromIndex)._2().join(tree.split(toIndex)._1()))

    override fun insert(element: E, index: Int): PersistentList<E> =
            PaguroRRBTree(tree.insert(index, element))

    override fun insert(collection: PersistentCollection<E>, index: Int): PersistentList<E> {
        val split = tree.split(index)
        if (collection is PaguroRRBTree<E>) return PaguroRRBTree(split._1().join(collection.tree).join(split._2()))

        var result = split._1()

        // FIXME fold instead
        collection.forEach { result = result.append(it) }

        return PaguroRRBTree(result.join(split._2()))
    }

    override fun concat(collection: PersistentCollection<E>): PersistentList<E> {
        if (collection is PaguroRRBTree<E>) return PaguroRRBTree(tree.join(collection.tree))

        var result = tree

        // FIXME fold instead
        collection.forEach { result = result.append(it) }

        return PaguroRRBTree(result)
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

        override fun <E> from(iterable: Iterable<E>): PersistentList<E> {
            var result = StaticImports.rrb<E>()

            iterable.forEach { result = result.append(it) }

            return if (result.isEmpty()) empty else PaguroRRBTree(result)
        }
    }
}
