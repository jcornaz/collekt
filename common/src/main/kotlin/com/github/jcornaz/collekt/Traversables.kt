package com.github.jcornaz.collekt

/**
 * Immutable data structure which may be iterated.
 *
 * It is analogous to [Iterable] except a [Traversable] is *immutable* by contract,
 * which means that different iteration will always return the same elements in the same order.
 *
 * Usages of [Traversable] may take profit of this contract by caching the element iterated for example.
 */
public interface Traversable<out E> {

    /** Returns an iterator over the elements */
    public operator fun iterator(): Iterator<E>
}

/**
 * A [Traversable] from which it is possible to query the [size] without iterating
 */
public interface SizedTraversable<out E> : Traversable<E> {

    /** The number of elements which would be returned by [iterator] */
    public val size: Int

    /** Returns true if, and only if, [size] == 0 */
    public val isEmpty: Boolean
}

/**
 * Returns true if, and only if, [SizedTraversable.isEmpty] == false
 */
public val SizedTraversable<*>.isNotEmpty: Boolean get() = !isEmpty

/**
 * Returns an [Iterable] adapter fo [Traversable]
 */
public fun <E> Traversable<E>.asIterable(): Iterable<E> = Iterable { iterator() }

/**
 * Returns an [Sequence] adapter fo [Traversable]
 */
public fun <E> Traversable<E>.asSequence(): Sequence<E> = Sequence { iterator() }

/**
 * Execute [action] for each element in the collection
 */
public inline fun <E> Traversable<E>.forEach(action: (E) -> Unit) {
    for (element in this) action(element)
}

/**
 * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
 */
public inline fun <T, R> Traversable<T>.fold(initial: R, operation: (acc: R, elt: T) -> R): R {
    var result = initial

    forEach { result = operation(result, it) }

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

/**
 * Returns true if, and only if, [predicate] returns true for at least one element in this [Traversable]
 *
 * Returns false if the [Traversable] is empty
 */
public inline fun <E> Traversable<E>.any(predicate: (E) -> Boolean = { true }): Boolean {
    if (this is SizedTraversable && isEmpty) return false

    for (element in this) {
        if (predicate(element)) return true
    }

    return false
}

/**
 * Returns true if, and only if, [predicate] returns false for all elements in this [Traversable]
 *
 * Returns true if the [Traversable] is empty
 */
public inline fun <E> Traversable<E>.none(predicate: (E) -> Boolean = { true }): Boolean = !any(predicate)

/**
 * Returns true if, and only if, [predicate] returns true for all elemens in this [Traversable]
 *
 * Returns true if the [Traversable] is empty
 */
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
