package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.*
import com.github.jcornaz.collekt.test.PersistentListTest
import com.github.jcornaz.collekt.test.PersistentMapTest
import com.github.jcornaz.collekt.test.PersistentSetTest

class PersistentListFactoryTest : PersistentListTest() {
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

class PersistentSetFactoryTest : PersistentSetTest() {
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

class PersistentMapFactoryTest : PersistentMapTest() {
    override val mapFactory = object : PersistentMapFactory {
        override fun <K, V> empty(): PersistentMap<K, V> = emptyPersistentMap()

        override fun <K, V> from(entries: Iterable<Pair<K, V>>): PersistentMap<K, V> =
                entries.toMap().toPersistentMap()

        override fun <K, V> from(map: Map<K, V>): PersistentMap<K, V> =
                map.toPersistentMap()
    }
}

class PersistentMapOfTest : PersistentMapTest() {
    override val mapFactory = object : PersistentMapFactory by DefaultMapFactory {

        @Suppress("UNCHECKED_CAST")
        override fun <K, V> from(entries: Iterable<Pair<K, V>>): PersistentMap<K, V> =
                persistentMapOf(*entries.toList().toTypedArray())

        override fun <K, V> from(map: Map<K, V>): PersistentMap<K, V> = from(map.entries)
    }
}
