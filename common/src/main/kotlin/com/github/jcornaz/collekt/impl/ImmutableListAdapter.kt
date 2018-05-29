package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.ImmutableList
import com.github.jcornaz.collekt.emptyImmutableList

internal class ImmutableListAdapter<out E>(
        private val list: List<E>
) : ImmutableList<E>, List<E> by list {

    override fun subList(fromIndex: Int, toIndex: Int): ImmutableList<E> {
        checkSublistRange(fromIndex, toIndex, list.size)

        if (toIndex <= fromIndex) return emptyImmutableList()

        return ImmutableListAdapter(list.subList(fromIndex, toIndex))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is List<*>) return false
        if (other.isEmpty()) return list.isEmpty()
        if (other is ImmutableListAdapter<*>) return list == other.list
        
        return list == other
    }

    override fun hashCode() = list.hashCode()

    override fun toString() = list.toString()
}
