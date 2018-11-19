package com.github.jcornaz.collekt.immutable

import com.github.jcornaz.collekt.api.ImmutableCollection
import com.github.jcornaz.collekt.api.ImmutableMap
import com.github.jcornaz.collekt.api.ImmutableSet

internal object EmptyImmutableMap : ImmutableMap<Any?, Nothing> {
    override val entries: ImmutableSet<Map.Entry<Any?, Nothing>> get() = EmptyImmutableSet
    override val keys: ImmutableSet<Any?> get() = EmptyImmutableSet
    override val values: ImmutableCollection<Nothing> get() = EmptyImmutableSet
    override val size: Int get() = 0

    override fun containsKey(key: Any?): Boolean = false
    override fun containsValue(value: Nothing): Boolean = false

    override fun get(key: Any?): Nothing? = null

    override fun isEmpty(): Boolean = true
}
