package com.github.jcornaz.collekt.impl

import com.github.jcornaz.collekt.PersistentCollection
import com.github.jcornaz.collekt.joinToString

public abstract class AbstractPersistentCollection<out E> : PersistentCollection<E> {
    final override fun toString() = joinToString(prefix = "[", separator = ", ", postfix = "]")
}
