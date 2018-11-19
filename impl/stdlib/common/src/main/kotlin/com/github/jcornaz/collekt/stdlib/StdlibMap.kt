package com.github.jcornaz.collekt.stdlib

import com.github.jcornaz.collekt.api.*

public class StdlibMap<K, out V> private constructor(private val map: Map<K, V>) : AbstractPersistentMap<K, V>() {
    companion object Factory : PersistentMapFactory {
        private val empty = StdlibMap<Any?, Nothing>(emptyMap())

        @Suppress("UNCHECKED_CAST")
        override fun <K, V> empty(): PersistentMap<K, V> =
                empty as PersistentMap<K, V>

        override fun <K, V> from(entries: Iterable<Pair<K, V>>): PersistentMap<K, V> {
            val map = entries.toMap()

            return if (map.isEmpty()) empty() else StdlibMap(map)
        }

        override fun <K, V> from(map: Map<K, V>): PersistentMap<K, V> {
            if (map is StdlibMap) return map
            if (map.isEmpty()) return empty()
            if (map is ImmutableMap<K, V>) return StdlibMap(map)

            return from(map.entries)
        }
    }

    override val entries: ImmutableSet<Map.Entry<K, V>> = map.entries.asImmutableSet()
    override val keys: ImmutableSet<K> = map.keys.asImmutableSet()
    override val values: ImmutableCollection<V> = map.values.asImmutableCollection()

    override val size: Int get() = map.size

    override fun isEmpty(): Boolean = map.isEmpty()

    override fun get(key: K): V? = map[key]

    override fun containsKey(key: K): Boolean = map.containsKey(key)
    override fun containsValue(value: @UnsafeVariance V): Boolean = map.containsValue(value)

    override fun plus(key: K, value: @UnsafeVariance V): PersistentMap<K, V> = this + (key to value)

    override fun plus(entry: Pair<K, @UnsafeVariance V>): PersistentMap<K, V> =
            wrap(map + entry)

    override fun plus(map: Map<K, @UnsafeVariance V>): PersistentMap<K, V> =
            wrap(this.map + map)

    override fun minus(key: K): PersistentMap<K, V> =
            wrap(map - key)

    override fun minus(keys: Iterable<K>): PersistentMap<K, V> =
            wrap(map - keys)

    override fun empty(): PersistentMap<K, V> = StdlibMap.
            empty()

    private fun wrap(newMap: Map<K, @UnsafeVariance V>): PersistentMap<K, V> = when {
        newMap === map -> this
        newMap.isEmpty() -> empty()
        else -> StdlibMap(newMap)
    }
}