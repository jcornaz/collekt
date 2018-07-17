package com.github.jcornaz.collekt.paguro

import com.github.jcornaz.collekt.api.*
import org.organicdesign.fp.collections.ImMap
import org.organicdesign.fp.collections.PersistentHashMap

public class PaguroHashMap<K, out V>(private val backedMap: ImMap<K, V>) : AbstractPersistentMap<K, V>() {
    companion object Factory : PersistentMapFactory {
        private val empty = PaguroHashMap<Any?, Nothing>(PersistentHashMap.empty())

        @Suppress("UNCHECKED_CAST")
        override fun <K, V> empty(): PersistentMap<K, V> =
                empty as PersistentMap<K, V>

        override fun <K, V> from(entries: Iterable<Pair<K, V>>): PersistentMap<K, V> =
                fromEntries(entries.asSequence().map { (key, value) ->
                    object : Map.Entry<K, V> {
                        override val key: K = key
                        override val value: V = value
                    }
                }.asIterable())

        private fun <K, V> fromEntries(entries: Iterable<Map.Entry<K, V>>): PersistentMap<K, V> {
            val map = PersistentHashMap.of(entries)

            return if (map.isEmpty()) empty() else PaguroHashMap(map)
        }

        override fun <K, V> from(map: Map<K, V>): PersistentMap<K, V> {
            if (map is PaguroHashMap) return map
            if (map.isEmpty()) return empty()
            if (map is PersistentHashMap) return PaguroHashMap(map)

            return fromEntries(map.entries)
        }
    }

    override val entries: ImmutableSet<Map.Entry<K, V>> = backedMap.entries.asImmutableSet()
    override val keys: ImmutableSet<K> = backedMap.keys.asImmutableSet()

    @Suppress("DEPRECATION")
    override val values: ImmutableCollection<V> = backedMap.values.asImmutableCollection()

    override val size: Int get() = backedMap.size

    override fun isEmpty(): Boolean = backedMap.isEmpty()

    override fun containsKey(key: K): Boolean = backedMap.containsKey(key)
    override fun containsValue(value: @UnsafeVariance V): Boolean = backedMap.containsValue(value)

    override fun get(key: K): V? = backedMap[key]

    override fun plus(key: K, value: @UnsafeVariance V): PersistentMap<K, V> =
            wrap(backedMap.assoc(key, value))

    override fun plus(map: Map<K, @UnsafeVariance V>): PersistentMap<K, V> =
            wrap(map.entries.fold(backedMap) { acc, entry -> acc.assoc(entry) })

    override fun minus(key: K): PersistentMap<K, V> =
            wrap(backedMap.without(key))

    override fun minus(keys: Iterable<K>): PersistentMap<K, V> =
            wrap(keys.fold(backedMap) { acc, key -> acc.without(key) })

    private fun wrap(newMap: ImMap<K, V>): PersistentMap<K, V> = when {
        newMap === backedMap -> this
        newMap.isEmpty() -> empty()
        else -> PaguroHashMap(newMap)
    }
}
