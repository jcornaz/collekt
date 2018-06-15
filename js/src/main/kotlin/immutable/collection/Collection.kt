@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE")
@file:[JsModule("immutable") JsQualifier("Collection")]
package immutable.collection

import kotlin.js.*

external interface Keyed<K, V> : immutable.Collection<K, V>, immutable.iterable.Keyed<K, V> {
    override fun toSeq(): immutable.seq.Keyed<K, V>
}
external interface Indexed<T> : immutable.Collection<Number, T>, immutable.iterable.Indexed<T> {
    override fun toSeq(): immutable.seq.Indexed<T>
}
external interface Set<T> : immutable.Collection<T, T>, immutable.iterable.Set<T> {
    override fun toSeq(): immutable.seq.Set<T>
}
