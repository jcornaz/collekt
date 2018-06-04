package com.github.jcornaz.collekt

/**
 * An immutable [Collection].
 *
 * All implementers promise that the collection won't ever change after instance creation
 */
public interface PersistentCollection<out E> {
    val size: Int
    val isEmpty: Boolean

    operator fun contains(element: @UnsafeVariance E): Boolean

    operator fun iterator(): Iterator<E>

    operator fun plus(element: @UnsafeVariance E): PersistentCollection<E>
    operator fun plus(collection: PersistentCollection<@UnsafeVariance E>): PersistentCollection<E>
    operator fun minus(element: @UnsafeVariance E): PersistentCollection<E>
}

/**
 * An immutable [List]
 *
 * All implementers promise that the list won't ever change after instance creation
 */
public interface PersistentList<out E> : PersistentCollection<E> {
    operator fun get(index: Int): E

    fun indexOf(element: @UnsafeVariance E): Int
    fun lastIndexOf(element: @UnsafeVariance E): Int

    fun subList(fromIndex: Int, toIndex: Int): PersistentList<E>

    override fun plus(element: @UnsafeVariance E): PersistentList<E> = plus(element, size)
    fun plus(element: @UnsafeVariance E, index: Int): PersistentList<E>

    override fun plus(collection: PersistentCollection<@UnsafeVariance E>): PersistentList<E> = plus(collection, size)
    fun plus(collection: PersistentCollection<@UnsafeVariance E>, index: Int): PersistentList<E>

    override fun minus(element: @UnsafeVariance E): PersistentList<E>
    fun minusIndex(index: Int): PersistentList<E>
}

public fun <E> PersistentCollection<E>.asCollection(): Collection<E> = object : AbstractCollection<E>() {
    override val size get() = this@asCollection.size
    override fun iterator() = this@asCollection.iterator()
}

public fun <E> PersistentList<E>.asList(): List<E> = object : AbstractList<E>() {
    override val size get() = this@asList.size
    override fun get(index: Int) = this@asList[index]
}

internal interface PersistentCollectionFactory {
    val empty: PersistentCollection<Nothing>
    fun <E> from(iterable: Iterable<E>): PersistentCollection<E>
}

internal interface PersistentListFactory : PersistentCollectionFactory {
    override val empty: PersistentList<Nothing>
    override fun <E> from(iterable: Iterable<E>): PersistentList<E>
}

internal fun <E> PersistentCollectionFactory.empty(): PersistentCollection<E> = empty
internal fun <E> PersistentListFactory.empty(): PersistentList<E> = empty

internal fun <E> PersistentCollectionFactory.of(vararg elements: E): PersistentCollection<E> =
        if (elements.isEmpty()) empty else from(elements.asIterable())

internal fun <E> PersistentListFactory.of(vararg elements: E): PersistentList<E> =
        if (elements.isEmpty()) empty else from(elements.asIterable())

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
