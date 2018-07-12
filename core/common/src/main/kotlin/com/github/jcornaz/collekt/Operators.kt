package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.ImmutableCollection
import com.github.jcornaz.collekt.api.PersistentList

public fun <T> ImmutableCollection<T>.filter(predicate: (T) -> Boolean): PersistentList<T> =
        asSequence().filter(predicate).toPersistentList()

public fun <T : Any> ImmutableCollection<T?>.filterNotNull(): PersistentList<T> =
        asSequence().filterNotNull().toPersistentList()

public inline fun <T, reified R> ImmutableCollection<T>.filterIsInstance(): PersistentList<R> =
        asSequence().filterIsInstance<R>().toPersistentList()

public fun <T, R> ImmutableCollection<T>.map(transform: (T) -> R): PersistentList<R> =
        asSequence().map(transform).toPersistentList()

public fun <T, R : Any> ImmutableCollection<T>.mapNotNull(transform: (T) -> R?): PersistentList<R> =
        asSequence().mapNotNull(transform).toPersistentList()

public fun <T, R> ImmutableCollection<T>.flatMap(transform: (T) -> Iterable<R>): PersistentList<R> =
        asSequence().flatMap { transform(it).asSequence() }.toPersistentList()
