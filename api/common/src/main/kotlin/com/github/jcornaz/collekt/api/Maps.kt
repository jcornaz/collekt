package com.github.jcornaz.collekt.api

/**
 * An immutable map. Users of this interfaces can safely assumes that the content will never change.
 *
 * It is for instance never needed to create a "defensive copy".
 */
public interface ImmutableMap<K, out V> : Map<K, V> {
    override val entries: ImmutableSet<Map.Entry<K, V>>
    override val keys: ImmutableSet<K>
    override val values: ImmutableCollection<V>
}

/**
 * An effectively immutable map providing *mutation method* which return new instances instead of modifying the instance.
 *
 * Typically a persistent map is optimized to share as much data as possible between other instances,
 * reducing memory usage, and providing good performance of the *mutation methods*
 */
public interface PersistentMap<K, out V> : ImmutableMap<K, V> {
    fun plus(key: K, value: @UnsafeVariance V): PersistentMap<K, V>

    operator fun plus(entry: Pair<K, @UnsafeVariance V>): PersistentMap<K, V> = plus(entry.first, entry.second)
    operator fun plus(map: Map<K, @UnsafeVariance V>): PersistentMap<K, V>

    operator fun minus(key: K): PersistentMap<K, V>
    operator fun minus(keys: Iterable<K>): PersistentMap<K, V>

    /** Returns an empty map */
    public fun empty(): PersistentMap<K, V>
}

/**
 * Provide [equals], [hashCode] and [toString] implementations for map
 */
public abstract class AbstractPersistentMap<K, out V> : PersistentMap<K, V> {

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is Map<*, *>) return false
        if (other.size != size) return false

        return other.entries.all { (key, value) -> value == this[key] }
    }

    override fun hashCode(): Int = entries.hashCode()

    override fun toString(): String = entries.joinToString(", ", "{", "}")
}
