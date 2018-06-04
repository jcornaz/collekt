package com.github.jcornaz.collekt

public inline fun <E> PersistentCollection<E>.forEach(action: (E) -> Unit) {
    for (element in this) action(element)
}

public fun <E> PersistentCollection<E>.first(): E =
        if (this is PersistentList<E> && !isEmpty) this[0] else iterator().next()

public fun <E> PersistentCollection<E>.firstOrNull(): E? {
    return if (this is PersistentList<E>) {
        if (isEmpty) null else this[0]
    } else {
        iterator().let { if (it.hasNext()) null else it.next() }
    }
}

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
