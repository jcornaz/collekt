package com.github.jcornaz.collekt.api

/**
 * Factory responsible for the creation of [PersistentCollection]
 */
public interface PersistentCollectionFactory {

    /** Returns an empty [PersistentCollection] */
    fun <E> empty(): com.github.jcornaz.collekt.api.PersistentCollection<E>

    /** Returns a [PersistentCollection] containing [elements]*/
    fun <E> from(elements: Iterable<E>): com.github.jcornaz.collekt.api.PersistentCollection<E>
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

/** Returns a [PersistentCollection] containing [elements] */
public fun <E> PersistentCollectionFactory.of(vararg elements: E): com.github.jcornaz.collekt.api.PersistentCollection<E> =
        if (elements.isEmpty()) empty() else from(elements.asIterable())

/** Returns a [PersistentList] containing [elements] */
public fun <E> PersistentListFactory.of(vararg elements: E): PersistentList<E> =
        if (elements.isEmpty()) empty() else from(elements.asIterable())
