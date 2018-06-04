package com.github.jcornaz.collekt

public fun <E> PersistentList<E>.first(): E = iterator().next()

public fun <E> PersistentCollection<E>.joinToString(
        separator: String = "",
        prefix: String = "",
        postfix: String = "",
        transform: (E) -> String = { it as? String ?: it.toString() }
): String = buildString {
    var hasPrevious = false
    append(prefix)
    for (element in this@joinToString) {
        if (hasPrevious) append(separator)
        append(transform(element))
        hasPrevious = true
    }
    append(postfix)
}
