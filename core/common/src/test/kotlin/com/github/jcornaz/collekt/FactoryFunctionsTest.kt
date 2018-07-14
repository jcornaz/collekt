package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.PersistentList
import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.api.PersistentSet
import com.github.jcornaz.collekt.api.PersistentSetFactory
import com.github.jcornaz.collekt.test.PersistentListTest
import com.github.jcornaz.collekt.test.PersistentSetTest

class ListFactoryTest : PersistentListTest() {
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
                persistentListOf(*elements.map { it as Any? }.toTypedArray()) as PersistentList<E>
    }
}

class SetFactoryTest : PersistentSetTest() {
    override val factory = object : PersistentSetFactory {
        override fun <E> empty(): PersistentSet<E> = emptyPersistentSet()
        override fun <E> from(elements: Iterable<E>): PersistentSet<E> =
                elements.toPersistentSet()
    }
}

class PersistentSetOfTest : PersistentSetTest() {
    override val factory = object : PersistentSetFactory by DefaultSetFactory {

        @Suppress("UNCHECKED_CAST")
        override fun <E> from(elements: Iterable<E>): PersistentSet<E> =
                persistentSetOf(*elements.map { it as Any? }.toTypedArray()) as PersistentSet<E>
    }
}
