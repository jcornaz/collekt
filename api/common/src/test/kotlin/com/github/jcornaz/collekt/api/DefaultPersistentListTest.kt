package com.github.jcornaz.collekt.api

class DefaultPersistentListTest : PersistentListTest() {
    override val factory = object : PersistentListFactory {
        override fun <E> empty(): PersistentList<E> = emptyPersistentList<Nothing>()
        override fun <E> from(elements: Iterable<E>) = elements.toPersistentList()
    }
}
