package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.PersistentList
import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.test.PersistentListTest

class BuildersTest : PersistentListTest() {
    override val factory = object : PersistentListFactory {
        override fun <E> empty(): PersistentList<E> = emptyPersistentList()

        override fun <E> from(elements: Iterable<E>): PersistentList<E> =
                elements.toPersistentList()
    }
}

class PersistentListOfTest : PersistentListTest() {
    override val factory = object : PersistentListFactory by DefaultListFactory {

        @Suppress("UNCHECKED_CAST")
        override fun <E> from(elements: Iterable<E>): PersistentList<E> =
                persistentListOf(*elements.filterIsInstance<Any>().toTypedArray()) as PersistentList<E>
    }
}
