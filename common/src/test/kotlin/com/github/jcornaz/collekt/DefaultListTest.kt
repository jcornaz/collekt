package com.github.jcornaz.collekt

class DefaultListTest : ListTest() {
    override val factory = object : PersistentListFactory {
        override fun <E> empty(): PersistentList<E> = emptyPersistentList<Nothing>()
        override fun <E> from(iterable: Iterable<E>) = iterable.toPersistentList()
    }
}
