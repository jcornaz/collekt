@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE")
@file:[JsModule("immutable") JsQualifier("Seq")]
package immutable.seq

import kotlin.js.*

external fun isSeq(maybeSeq: Any): Boolean = definedExternally
external fun <T> of(vararg values: T): Indexed<T> = definedExternally
external fun <K, V> Keyed(): Keyed<K, V> = definedExternally
external fun <K, V> Keyed(seq: immutable.iterable.Keyed<K, V>): Keyed<K, V> = definedExternally
external fun <K, V> Keyed(seq: immutable.Iterable<Any, Any>): Keyed<K, V> = definedExternally
external fun <K, V> Keyed(array: Array<Any>): Keyed<K, V> = definedExternally
external interface `T$1`<V> {
    @nativeGetter
    operator fun get(key: String): V?
    @nativeSetter
    operator fun set(key: String, value: V)
}
external fun <V> Keyed(obj: `T$1`<V>): Keyed<String, V> = definedExternally
external fun <K, V> Keyed(iterator: immutable.Iterator<Any>): Keyed<K, V> = definedExternally
external fun <K, V> Keyed(iterable: Any): Keyed<K, V> = definedExternally
external interface Keyed<K, V> : immutable.Seq<K, V>, immutable.iterable.Keyed<K, V> {
    override fun toSeq(): Keyed<K, V>
}
external fun <T> Indexed(): Indexed<T> = definedExternally
external fun <T> Indexed(seq: immutable.iterable.Indexed<T>): Indexed<T> = definedExternally
external fun <T> Indexed(seq: immutable.iterable.Set<T>): Indexed<T> = definedExternally
external fun <K, V> Indexed(seq: immutable.iterable.Keyed<K, V>): Indexed<Any> = definedExternally
external fun <T> Indexed(array: Array<T>): Indexed<T> = definedExternally
external fun <T> Indexed(iterator: immutable.Iterator<T>): Indexed<T> = definedExternally
external fun <T> Indexed(iterable: Any): Indexed<T> = definedExternally
external interface Indexed<T> : immutable.Seq<Number, T>, immutable.iterable.Indexed<T> {
    override fun toSeq(): Indexed<T>
    companion object {
        fun <T> of(vararg values: T): immutable.seq.Indexed<T> = definedExternally
    }
}
external fun <T> Set(): Set<T> = definedExternally
external fun <T> Set(seq: immutable.iterable.Set<T>): Set<T> = definedExternally
external fun <T> Set(seq: immutable.iterable.Indexed<T>): Set<T> = definedExternally
external fun <K, V> Set(seq: immutable.iterable.Keyed<K, V>): Set<Any> = definedExternally
external fun <T> Set(array: Array<T>): Set<T> = definedExternally
external fun <T> Set(iterator: immutable.Iterator<T>): Set<T> = definedExternally
external fun <T> Set(iterable: Any): Set<T> = definedExternally
external interface Set<T> : immutable.Seq<T, T>, immutable.iterable.Set<T> {
    override fun toSeq(): Set<T>
    companion object {
        fun <T> of(vararg values: T): immutable.seq.Set<T> = definedExternally
    }
}
