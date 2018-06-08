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

public inline fun <E> Traversable(crossinline iterator: () -> Iterator<E>): Traversable<E> = object : Traversable<E> {
    override fun iterator(): Iterator<E> = iterator()
}

public interface SizedTraversable<out E> : Traversable<E> {
    public val size: Int
    public val isEmpty: Boolean
}

public val SizedTraversable<*>.isNotEmpty: Boolean get() = !isEmpty

public fun <E> Traversable<E>.asIterable(): Iterable<E> = Iterable { iterator() }
public fun <E> Traversable<E>.asSequence(): Sequence<E> = Sequence { iterator() }
public fun <E> Iterable<E>.asTraversable(): Traversable<E> = Traversable { iterator() }
public fun <E> Sequence<E>.asTraversable(): Traversable<E> = Traversable { iterator() }

/**
 * Execute [action] for each element in the collection
 */
public inline fun <E> Traversable<E>.forEach(action: (E) -> Unit) {
    for (element in this) action(element)
}

public inline fun <T, R> Traversable<T>.fold(initial: R, operation: (acc: R, elt: T) -> R): R {
    var result = initial

    forEach { result = operation(result, it) }

    return result
}

public inline fun <T> Traversable<T>.reduce(operation: (T, T) -> T): T {
    val iterator = iterator()
    var result = iterator.next()

    while (iterator.hasNext()) {
        result = operation(result, iterator.next())
    }

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
    if (this is SizedTraversable && isEmpty) return false

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
