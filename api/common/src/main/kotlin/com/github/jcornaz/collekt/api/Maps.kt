package com.github.jcornaz.collekt.api

public interface ImmutableMap<K, out V> : Map<K, V> {
    override val entries: ImmutableSet<Map.Entry<K, V>>
    override val keys: ImmutableSet<K>
    override val values: ImmutableCollection<V>
}

public interface PersistentMap<K, out V> : ImmutableMap<K, V> {
    fun plus(key: K, value: @UnsafeVariance V): PersistentMap<K, V>

    operator fun minus(key: K): PersistentMap<K, V>
}
