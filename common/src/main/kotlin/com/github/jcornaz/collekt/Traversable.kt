package com.github.jcornaz.collekt

/**
 * Immutable data structure which may be traversed.
 *
 * It is analogous to [Iterable] except a [Traversable] is *immutable* by contract,
 * which means that different iteration will always return the same result.
 *
 * Usages of [Traversable] may take profit of this contract by caching the element iterated for example.
 */
public interface Traversable<out E> {

    /** Returns an iterator over the elements */
    public operator fun iterator(): Iterator<E>
}

/**
 * Execute [action] for each element in the collection
 */
public inline fun <E> Traversable<E>.forEach(action: (E) -> Unit) {
    for (element in this) action(element)
}

public inline fun <E> Traversable<E>.forEachIndexed(action: (index: Int, element: E) -> Unit) {
    var index = 0
    forEach {
        action(index, it)
        ++index
    }
}

public inline fun <E, R> Traversable<E>.fold(seed: R, action: (acc: R, elt: E) -> R): R {
    var result = seed

    forEach { result = action(result, it) }

    return result
}

/**
 * Returns the first element of the collection (first element return by the iterator in case of un-ordered collection)
 *
 * @throws kotlin.NoSuchElementException If the collection is empty
 */
public fun <E> Traversable<E>.first(): E =
        if (this is PersistentList<E> && isNotEmpty) this[0] else iterator().next()

/**
 * Returns the first element of the collection or null if the collection is empty
 */
public fun <E> Traversable<E>.firstOrNull(): E? {
    if (this is PersistentCollection<E> && isEmpty) return null
    if (this is PersistentList<E>) return this[0]

    return iterator().let { if (it.hasNext()) null else it.next() }
}

public inline fun <E> Traversable<E>.any(predicate: (E) -> Boolean = { true }): Boolean {
    if (this is PersistentCollection<E> && isEmpty) return false

    for (element in this) {
        if (predicate(element)) return true
    }

    return false
}

public inline fun <E> Traversable<E>.none(predicate: (E) -> Boolean = { true }): Boolean = !any(predicate)
public inline fun <E> Traversable<E>.all(predicate: (E) -> Boolean): Boolean = none { !predicate(it) }

/**
 * Join the element of the collection in a string
 */
public fun <E> Traversable<E>.joinToString(
        separator: CharSequence = "",
        prefix: CharSequence = "",
        postfix: CharSequence = "",
        transform: (E) -> CharSequence = { it as? String ?: it.toString() }
): String = buildString {
    var hasPrevious = false
    append(prefix)
    this@joinToString.forEach { element ->
        if (hasPrevious) append(separator)
        append(transform(element))
        hasPrevious = true
    }
    append(postfix)
}
