@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE")
@file:[JsModule("immutable") JsQualifier("Iterable")]
package immutable.iterable

import kotlin.js.*

external fun isIterable(maybeIterable: Any): Boolean = definedExternally
external fun isKeyed(maybeKeyed: Any): Boolean = definedExternally
external fun isIndexed(maybeIndexed: Any): Boolean = definedExternally
external fun isAssociative(maybeAssociative: Any): Boolean = definedExternally
external fun isOrdered(maybeOrdered: Any): Boolean = definedExternally
external fun <K, V> Keyed(iter: Keyed<K, V>): Keyed<K, V> = definedExternally
external fun <K, V> Keyed(iter: immutable.Iterable<Any, Any>): Keyed<K, V> = definedExternally
external fun <K, V> Keyed(array: Array<Any>): Keyed<K, V> = definedExternally
external interface `T$2`<V> {
    @nativeGetter
    operator fun get(key: String): V?
    @nativeSetter
    operator fun set(key: String, value: V)
}
external fun <V> Keyed(obj: `T$2`<V>): Keyed<String, V> = definedExternally
external fun <K, V> Keyed(iterator: immutable.Iterator<Any>): Keyed<K, V> = definedExternally
external fun <K, V> Keyed(iterable: Any): Keyed<K, V> = definedExternally
external interface Keyed<K, V> : immutable.Iterable<K, V> {
    override fun toSeq(): immutable.seq.Keyed<K, V>
    fun flip(): Keyed<V, K>
    fun <M> mapKeys(mapper: (key: K? /*= null*/, value: V? /*= null*/, iter: Keyed<K, V>? /*= null*/) -> M, context: Any? = definedExternally /* null */): Keyed<M, V>
    fun <KM, VM> mapEntries(mapper: (entry: Array<Any>? /*= null*/, index: Number? /*= null*/, iter: Keyed<K, V>? /*= null*/) -> Array<Any>, context: Any? = definedExternally /* null */): Keyed<KM, VM>
}
external fun <T> Indexed(iter: Indexed<T>): Indexed<T> = definedExternally
external fun <T> Indexed(iter: Set<T>): Indexed<T> = definedExternally
external fun <K, V> Indexed(iter: Keyed<K, V>): Indexed<Any> = definedExternally
external fun <T> Indexed(array: Array<T>): Indexed<T> = definedExternally
external fun <T> Indexed(iterator: immutable.Iterator<T>): Indexed<T> = definedExternally
external fun <T> Indexed(iterable: Any): Indexed<T> = definedExternally
external interface Indexed<T> : immutable.Iterable<Number, T> {
    override fun get(index: Number, notSetValue: T? /* null */): T
    override fun toSeq(): immutable.seq.Indexed<T>
    fun fromEntrySeq(): immutable.seq.Keyed<Any, Any>
    fun interpose(separator: T): Indexed<T>
    fun interleave(vararg iterables: immutable.Iterable<Any, T>): Indexed<T>
    fun splice(index: Number, removeNum: Number, vararg values: Any): Indexed<T>
    fun zip(vararg iterables: immutable.Iterable<Any, Any>): Indexed<Any>
    fun <U, Z> zipWith(zipper: (value: T, otherValue: U) -> Z, otherIterable: immutable.Iterable<Any, U>): Indexed<Z>
    fun <U, V, Z> zipWith(zipper: (value: T, otherValue: U, thirdValue: V) -> Z, otherIterable: immutable.Iterable<Any, U>, thirdIterable: immutable.Iterable<Any, V>): Indexed<Z>
    fun <Z> zipWith(zipper: (any: Any) -> Z, vararg iterables: immutable.Iterable<Any, Any>): Indexed<Z>
    fun indexOf(searchValue: T): Number
    fun lastIndexOf(searchValue: T): Number
    fun findIndex(predicate: (value: T? /*= null*/, index: Number? /*= null*/, iter: Indexed<T>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */): Number
    fun findLastIndex(predicate: (value: T? /*= null*/, index: Number? /*= null*/, iter: Indexed<T>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */): Number
}
external fun <T> Set(iter: Set<T>): Set<T> = definedExternally
external fun <T> Set(iter: Indexed<T>): Set<T> = definedExternally
external fun <K, V> Set(iter: Keyed<K, V>): Set<Any> = definedExternally
external fun <T> Set(array: Array<T>): Set<T> = definedExternally
external fun <T> Set(iterator: immutable.Iterator<T>): Set<T> = definedExternally
external fun <T> Set(iterable: Any): Set<T> = definedExternally
external interface Set<T> : immutable.Iterable<T, T> {
    override fun toSeq(): immutable.seq.Set<T>
}
