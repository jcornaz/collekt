package com.github.jcornaz.collekt

/**
 * Execute [action] for each element in the collection
 */
public inline fun <E> PersistentCollection<E>.forEach(action: (E) -> Unit) {
    for (element in this) action(element)
}

public inline fun <E, R> PersistentCollection<E>.fold(seed: R, operation: (R, E) -> R): R {
    var result = seed
    forEach { result = operation(result, it) }
    return result
}

/**
 * Returns the first element of the collection (first element return by the iterator in case of un-ordered collection)
 *
 * @throws kotlin.NoSuchElementException If the collection is empty
 */
public fun <E> PersistentCollection<E>.first(): E =
        if (this is PersistentList<E> && !isEmpty) this[0] else iterator().next()

/**
 * Returns the first element of the collection or null if the collection is empty
 */
public fun <E> PersistentCollection<E>.firstOrNull(): E? {
    return if (this is PersistentList<E>) {
        if (isEmpty) null else this[0]
    } else {
        iterator().let { if (it.hasNext()) null else it.next() }
    }
}

/**
 * Join the element of the collection in a string
 */
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
