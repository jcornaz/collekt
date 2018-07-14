package com.github.jcornaz.collekt.api

/**
 * Factory responsible for the creation of [PersistentCollection]
 */
public interface PersistentCollectionFactory {

    /** Returns an empty [PersistentCollection] */
    fun <E> empty(): PersistentCollection<E>

    /** Returns a [PersistentCollection] containing [elements]*/
    fun <E> from(elements: Iterable<E>): PersistentCollection<E>
}

/**
 * Factory responsible for the creation of [PersistentList]
 */
public interface PersistentListFactory : PersistentCollectionFactory {

    /** Returns an empty [PersistentList] */
    override fun <E> empty(): PersistentList<E>

    /** Returns a [PersistentList] containing [elements] */
    override fun <E> from(elements: Iterable<E>): PersistentList<E>
}

/**
 * Factory responsible for the creation of [PersistentSet]
 */
public interface PersistentSetFactory : PersistentCollectionFactory {

    /** Returns an empty [PersistentSet] */
    override fun <E> empty(): PersistentSet<E>

    /** Returns a [PersistentList] containing [elements] */
    override fun <E> from(elements: Iterable<E>): PersistentSet<E>
}

/**
 * Factory responsible for the creation of [PersistentMap]
 */
public interface PersistentMapFactory {

    /** Returns an empty [PersistentMap] */
    fun <K, V> empty(): PersistentMap<K, V>

    /** Returns a [PersistentMap] containing all entries in [map] */
    fun <K, V> from(entries: Map<K, V>): PersistentMap<K, V>
}

/** Returns a [PersistentCollection] containing [elements] */
public fun <E> PersistentCollectionFactory.of(vararg elements: E): PersistentCollection<E> =
        if (elements.isEmpty()) empty() else from(elements.asIterable())

/** Returns a [PersistentList] containing [elements] */
public fun <E> PersistentListFactory.of(vararg elements: E): PersistentList<E> =
        if (elements.isEmpty()) empty() else from(elements.asIterable())

/** Returns a [PersistentList] containing [entries] */
public fun <K, V> PersistentMapFactory.of(vararg entries: Pair<K, V>): PersistentMap<K, V> =
        if (entries.isEmpty()) empty() else from(entries.toMap())