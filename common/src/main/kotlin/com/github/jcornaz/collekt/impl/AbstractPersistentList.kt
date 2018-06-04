package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.PersistentList
import com.github.jcornaz.collekt.joinToString

internal abstract class AbstractPersistentList<out E> : PersistentList<E> {

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other === this) return true
        if (other !is PersistentList<*>) return false
        if (size != other.size) return false

        val e1 = iterator()
        val e2 = other.iterator()

        while (e1.hasNext() && e2.hasNext()) {
            val o1 = e1.next()
            val o2 = e2.next()
            if (!(if (o1 == null) o2 == null else o1 == o2))
                return false
        }

        return !(e1.hasNext() || e2.hasNext())
    }

    override fun hashCode(): Int {
        if (isEmpty) return 1

        var hash = 1

        for (element in this) {
            hash = 31 * hash + (element?.hashCode() ?: 0)
        }

        return hash
    }

    override fun toString() = joinToString(prefix = "[", separator = ", ", postfix = "]")
}
