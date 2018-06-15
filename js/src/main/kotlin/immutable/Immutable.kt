@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE")
@file:JsModule("immutable")
package immutable

import kotlin.js.*
import kotlin.js.Json

external fun fromJS(json: Any, reviver: ((k: Any, v: Iterable<Any, Any>) -> Any)? = definedExternally /* null */): Any = definedExternally
external fun `is`(first: Any, second: Any): Boolean = definedExternally
external fun <T> List(): List<T> = definedExternally
external fun <T> List(iter: immutable.iterable.Indexed<T>): List<T> = definedExternally
external fun <T> List(iter: immutable.iterable.Set<T>): List<T> = definedExternally
external fun <K, V> List(iter: immutable.iterable.Keyed<K, V>): List<Any> = definedExternally
external fun <T> List(array: Array<T>): List<T> = definedExternally
external fun <T> List(iterator: Iterator<T>): List<T> = definedExternally
external fun <T> List(iterable: Any): List<T> = definedExternally
external interface List<T> : immutable.collection.Indexed<T> {
    fun set(index: Number, value: T): List<T>
    fun delete(index: Number): List<T>
    fun remove(index: Number): List<T>
    fun insert(index: Number, value: T): List<T>
    fun clear(): List<T>
    fun push(vararg values: T): List<T>
    fun pop(): List<T>
    fun unshift(vararg values: T): List<T>
    fun shift(): List<T>
    fun update(updater: (value: List<T>) -> List<T>): List<T>
    fun update(index: Number, updater: (value: T) -> T): List<T>
    fun update(index: Number, notSetValue: T, updater: (value: T) -> T): List<T>
    fun merge(vararg iterables: immutable.iterable.Indexed<T>): List<T>
    fun merge(vararg iterables: Array<T>): List<T>
    fun mergeWith(merger: (previous: T? /*= null*/, next: T? /*= null*/, key: Number? /*= null*/) -> T, vararg iterables: immutable.iterable.Indexed<T>): List<T>
    fun mergeWith(merger: (previous: T? /*= null*/, next: T? /*= null*/, key: Number? /*= null*/) -> T, vararg iterables: Array<T>): List<T>
    fun mergeDeep(vararg iterables: immutable.iterable.Indexed<T>): List<T>
    fun mergeDeep(vararg iterables: Array<T>): List<T>
    fun mergeDeepWith(merger: (previous: T? /*= null*/, next: T? /*= null*/, key: Number? /*= null*/) -> T, vararg iterables: immutable.iterable.Indexed<T>): List<T>
    fun mergeDeepWith(merger: (previous: T? /*= null*/, next: T? /*= null*/, key: Number? /*= null*/) -> T, vararg iterables: Array<T>): List<T>
    fun setSize(size: Number): List<T>
    fun setIn(keyPath: Array<Any>, value: Any): List<T>
    fun setIn(keyPath: Iterable<Any, Any>, value: Any): List<T>
    fun deleteIn(keyPath: Array<Any>): List<T>
    fun deleteIn(keyPath: Iterable<Any, Any>): List<T>
    fun removeIn(keyPath: Array<Any>): List<T>
    fun removeIn(keyPath: Iterable<Any, Any>): List<T>
    fun updateIn(keyPath: Array<Any>, updater: (value: Any) -> Any): List<T>
    fun updateIn(keyPath: Array<Any>, notSetValue: Any, updater: (value: Any) -> Any): List<T>
    fun updateIn(keyPath: Iterable<Any, Any>, updater: (value: Any) -> Any): List<T>
    fun updateIn(keyPath: Iterable<Any, Any>, notSetValue: Any, updater: (value: Any) -> Any): List<T>
    fun mergeIn(keyPath: Iterable<Any, Any>, vararg iterables: immutable.iterable.Indexed<T>): List<T>
    fun mergeIn(keyPath: Array<Any>, vararg iterables: immutable.iterable.Indexed<T>): List<T>
    fun mergeIn(keyPath: Array<Any>, vararg iterables: Array<T>): List<T>
    fun mergeDeepIn(keyPath: Iterable<Any, Any>, vararg iterables: immutable.iterable.Indexed<T>): List<T>
    fun mergeDeepIn(keyPath: Array<Any>, vararg iterables: immutable.iterable.Indexed<T>): List<T>
    fun mergeDeepIn(keyPath: Array<Any>, vararg iterables: Array<T>): List<T>
    fun withMutations(mutator: (mutable: List<T>) -> Any): List<T>
    fun asMutable(): List<T>
    fun asImmutable(): List<T>
    companion object {
        fun isList(maybeList: Any): Boolean = definedExternally
        fun <T> of(vararg values: T): immutable.List<T> = definedExternally
    }
}
external fun <K, V> Map(): Map<K, V> = definedExternally
external fun <K, V> Map(iter: immutable.iterable.Keyed<K, V>): Map<K, V> = definedExternally
external fun <K, V> Map(iter: Iterable<Any, Array<Any>>): Map<K, V> = definedExternally
external fun <K, V> Map(array: Array<Array<Any>>): Map<K, V> = definedExternally
external interface `T$0`<V> {
    @nativeGetter
    operator fun get(key: String): V?
    @nativeSetter
    operator fun set(key: String, value: V)
}
external fun <V> Map(obj: `T$0`<V>): Map<String, V> = definedExternally
external fun <K, V> Map(iterator: Iterator<Array<Any>>): Map<K, V> = definedExternally
external fun <K, V> Map(iterable: Any): Map<K, V> = definedExternally
external interface Map<K, V> : immutable.collection.Keyed<K, V> {
    fun set(key: K, value: V): Map<K, V>
    fun delete(key: K): Map<K, V>
    fun remove(key: K): Map<K, V>
    fun clear(): Map<K, V>
    fun update(updater: (value: Map<K, V>) -> Map<K, V>): Map<K, V>
    fun update(key: K, updater: (value: V) -> V): Map<K, V>
    fun update(key: K, notSetValue: V, updater: (value: V) -> V): Map<K, V>
    fun merge(vararg iterables: Iterable<K, V>): Map<K, V>
    fun merge(vararg iterables: `T$0`<V>): Map<String, V>
    fun mergeWith(merger: (previous: V? /*= null*/, next: V? /*= null*/, key: K? /*= null*/) -> V, vararg iterables: Iterable<K, V>): Map<K, V>
    fun mergeWith(merger: (previous: V? /*= null*/, next: V? /*= null*/, key: K? /*= null*/) -> V, vararg iterables: `T$0`<V>): Map<String, V>
    fun mergeDeep(vararg iterables: Iterable<K, V>): Map<K, V>
    fun mergeDeep(vararg iterables: `T$0`<V>): Map<String, V>
    fun mergeDeepWith(merger: (previous: V? /*= null*/, next: V? /*= null*/, key: K? /*= null*/) -> V, vararg iterables: Iterable<K, V>): Map<K, V>
    fun mergeDeepWith(merger: (previous: V? /*= null*/, next: V? /*= null*/, key: K? /*= null*/) -> V, vararg iterables: `T$0`<V>): Map<String, V>
    fun setIn(keyPath: Array<Any>, value: Any): Map<K, V>
    fun setIn(KeyPath: Iterable<Any, Any>, value: Any): Map<K, V>
    fun deleteIn(keyPath: Array<Any>): Map<K, V>
    fun deleteIn(keyPath: Iterable<Any, Any>): Map<K, V>
    fun removeIn(keyPath: Array<Any>): Map<K, V>
    fun removeIn(keyPath: Iterable<Any, Any>): Map<K, V>
    fun updateIn(keyPath: Array<Any>, updater: (value: Any) -> Any): Map<K, V>
    fun updateIn(keyPath: Array<Any>, notSetValue: Any, updater: (value: Any) -> Any): Map<K, V>
    fun updateIn(keyPath: Iterable<Any, Any>, updater: (value: Any) -> Any): Map<K, V>
    fun updateIn(keyPath: Iterable<Any, Any>, notSetValue: Any, updater: (value: Any) -> Any): Map<K, V>
    fun mergeIn(keyPath: Iterable<Any, Any>, vararg iterables: Iterable<K, V>): Map<K, V>
    fun mergeIn(keyPath: Array<Any>, vararg iterables: Iterable<K, V>): Map<K, V>
    fun mergeIn(keyPath: Array<Any>, vararg iterables: `T$0`<V>): Map<String, V>
    fun mergeDeepIn(keyPath: Iterable<Any, Any>, vararg iterables: Iterable<K, V>): Map<K, V>
    fun mergeDeepIn(keyPath: Array<Any>, vararg iterables: Iterable<K, V>): Map<K, V>
    fun mergeDeepIn(keyPath: Array<Any>, vararg iterables: `T$0`<V>): Map<String, V>
    fun withMutations(mutator: (mutable: Map<K, V>) -> Any): Map<K, V>
    fun asMutable(): Map<K, V>
    fun asImmutable(): Map<K, V>
    companion object {
        fun isMap(maybeMap: Any): Boolean = definedExternally
        fun of(vararg keyValues: Any): immutable.Map<Any, Any> = definedExternally
    }
}
external fun <K, V> OrderedMap(): OrderedMap<K, V> = definedExternally
external fun <K, V> OrderedMap(iter: immutable.iterable.Keyed<K, V>): OrderedMap<K, V> = definedExternally
external fun <K, V> OrderedMap(iter: Iterable<Any, Array<Any>>): OrderedMap<K, V> = definedExternally
external fun <K, V> OrderedMap(array: Array<Array<Any>>): OrderedMap<K, V> = definedExternally
external fun <V> OrderedMap(obj: `T$0`<V>): OrderedMap<String, V> = definedExternally
external fun <K, V> OrderedMap(iterator: Iterator<Array<Any>>): OrderedMap<K, V> = definedExternally
external fun <K, V> OrderedMap(iterable: Any): OrderedMap<K, V> = definedExternally
external interface OrderedMap<K, V> : Map<K, V> {
    companion object {
        fun isOrderedMap(maybeOrderedMap: Any): Boolean = definedExternally
    }
}
external fun <T> Set(): Set<T> = definedExternally
external fun <T> Set(iter: immutable.iterable.Set<T>): Set<T> = definedExternally
external fun <T> Set(iter: immutable.iterable.Indexed<T>): Set<T> = definedExternally
external fun <K, V> Set(iter: immutable.iterable.Keyed<K, V>): Set<Any> = definedExternally
external fun <T> Set(array: Array<T>): Set<T> = definedExternally
external fun <T> Set(iterator: Iterator<T>): Set<T> = definedExternally
external fun <T> Set(iterable: Any): Set<T> = definedExternally
external interface Set<T> : immutable.collection.Set<T> {
    fun add(value: T): Set<T>
    fun delete(value: T): Set<T>
    fun remove(value: T): Set<T>
    fun clear(): Set<T>
    fun union(vararg iterables: Iterable<Any, T>): Set<T>
    fun union(vararg iterables: Array<T>): Set<T>
    fun merge(vararg iterables: Iterable<Any, T>): Set<T>
    fun merge(vararg iterables: Array<T>): Set<T>
    fun intersect(vararg iterables: Iterable<Any, T>): Set<T>
    fun intersect(vararg iterables: Array<T>): Set<T>
    fun subtract(vararg iterables: Iterable<Any, T>): Set<T>
    fun subtract(vararg iterables: Array<T>): Set<T>
    fun withMutations(mutator: (mutable: Set<T>) -> Any): Set<T>
    fun asMutable(): Set<T>
    fun asImmutable(): Set<T>
    companion object {
        fun isSet(maybeSet: Any): Boolean = definedExternally
        fun <T> of(vararg values: T): immutable.Set<T> = definedExternally
        fun <T> fromKeys(iter: immutable.Iterable<T, Any>): immutable.Set<T> = definedExternally
        fun fromKeys(obj: Json): immutable.Set<String> = definedExternally
    }
}
external fun <T> OrderedSet(): OrderedSet<T> = definedExternally
external fun <T> OrderedSet(iter: immutable.iterable.Set<T>): OrderedSet<T> = definedExternally
external fun <T> OrderedSet(iter: immutable.iterable.Indexed<T>): OrderedSet<T> = definedExternally
external fun <K, V> OrderedSet(iter: immutable.iterable.Keyed<K, V>): OrderedSet<Any> = definedExternally
external fun <T> OrderedSet(array: Array<T>): OrderedSet<T> = definedExternally
external fun <T> OrderedSet(iterator: Iterator<T>): OrderedSet<T> = definedExternally
external fun <T> OrderedSet(iterable: Any): OrderedSet<T> = definedExternally
external interface OrderedSet<T> : Set<T> {
    companion object {
        fun isOrderedSet(maybeOrderedSet: Any): Boolean = definedExternally
        fun <T> of(vararg values: T): immutable.OrderedSet<T> = definedExternally
        fun <T> fromKeys(iter: immutable.Iterable<T, Any>): immutable.OrderedSet<T> = definedExternally
        fun fromKeys(obj: Json): immutable.OrderedSet<String> = definedExternally
    }
}
external fun <T> Stack(): Stack<T> = definedExternally
external fun <T> Stack(iter: immutable.iterable.Indexed<T>): Stack<T> = definedExternally
external fun <T> Stack(iter: immutable.iterable.Set<T>): Stack<T> = definedExternally
external fun <K, V> Stack(iter: immutable.iterable.Keyed<K, V>): Stack<Any> = definedExternally
external fun <T> Stack(array: Array<T>): Stack<T> = definedExternally
external fun <T> Stack(iterator: Iterator<T>): Stack<T> = definedExternally
external fun <T> Stack(iterable: Any): Stack<T> = definedExternally
external interface Stack<T> : immutable.collection.Indexed<T> {
    fun peek(): T
    fun clear(): Stack<T>
    fun unshift(vararg values: T): Stack<T>
    fun unshiftAll(iter: Iterable<Any, T>): Stack<T>
    fun unshiftAll(iter: Array<T>): Stack<T>
    fun shift(): Stack<T>
    fun push(vararg values: T): Stack<T>
    fun pushAll(iter: Iterable<Any, T>): Stack<T>
    fun pushAll(iter: Array<T>): Stack<T>
    fun pop(): Stack<T>
    fun withMutations(mutator: (mutable: Stack<T>) -> Any): Stack<T>
    fun asMutable(): Stack<T>
    fun asImmutable(): Stack<T>
    companion object {
        fun isStack(maybeStack: Any): Boolean = definedExternally
        fun <T> of(vararg values: T): immutable.Stack<T> = definedExternally
    }
}
external fun Range(start: Number? = definedExternally /* null */, end: Number? = definedExternally /* null */, step: Number? = definedExternally /* null */): immutable.seq.Indexed<Number> = definedExternally
external fun <T> Repeat(value: T, times: Number? = definedExternally /* null */): immutable.seq.Indexed<T> = definedExternally
external fun Record(defaultValues: Json, name: String? = definedExternally /* null */): immutable.record.Class = definedExternally
external fun <K, V> Seq(): Seq<K, V> = definedExternally
external fun <K, V> Seq(seq: Seq<K, V>): Seq<K, V> = definedExternally
external fun <K, V> Seq(iterable: Iterable<K, V>): Seq<K, V> = definedExternally
external fun <T> Seq(array: Array<T>): immutable.seq.Indexed<T> = definedExternally
external fun <V> Seq(obj: `T$0`<V>): immutable.seq.Keyed<String, V> = definedExternally
external fun <T> Seq(iterator: Iterator<T>): immutable.seq.Indexed<T> = definedExternally
external fun <T> Seq(iterable: Any): immutable.seq.Indexed<T> = definedExternally
external interface Seq<K, V> : Iterable<K, V> {
    override var size: Number
    fun cacheResult(): Seq<K, V>
}
external fun <K, V> Iterable(iterable: Iterable<K, V>): Iterable<K, V> = definedExternally
external fun <T> Iterable(array: Array<T>): immutable.iterable.Indexed<T> = definedExternally
external fun <V> Iterable(obj: `T$0`<V>): immutable.iterable.Keyed<String, V> = definedExternally
external fun <T> Iterable(iterator: Iterator<T>): immutable.iterable.Indexed<T> = definedExternally
external fun <T> Iterable(iterable: Any): immutable.iterable.Indexed<T> = definedExternally
external fun <V> Iterable(value: V): immutable.iterable.Indexed<V> = definedExternally
external interface Iterable<K, V> {
    fun equals(other: Iterable<K, V>): Boolean
    override fun hashCode(): Number
    fun get(key: K, notSetValue: V? = definedExternally /* null */): V
    fun has(key: K): Boolean
    fun includes(value: V): Boolean
    fun contains(value: V): Boolean
    fun first(): V
    fun last(): V
    fun getIn(searchKeyPath: Array<Any>, notSetValue: Any? = definedExternally /* null */): Any
    fun getIn(searchKeyPath: Iterable<Any, Any>, notSetValue: Any? = definedExternally /* null */): Any
    fun hasIn(searchKeyPath: Array<Any>): Boolean
    fun hasIn(searchKeyPath: Iterable<Any, Any>): Boolean
    fun toJS(): Any
    fun toArray(): Array<V>
    fun toObject(): `T$0`<V>
    fun toMap(): Map<K, V>
    fun toOrderedMap(): OrderedMap<K, V>
    fun toSet(): Set<V>
    fun toOrderedSet(): OrderedSet<V>
    fun toList(): List<V>
    fun toStack(): Stack<V>
    fun toSeq(): Seq<K, V>
    fun toKeyedSeq(): immutable.seq.Keyed<K, V>
    fun toIndexedSeq(): immutable.seq.Indexed<V>
    fun toSetSeq(): immutable.seq.Set<V>
    fun keys(): Iterator<K>
    fun values(): Iterator<V>
    fun entries(): Iterator<Array<Any>>
    fun keySeq(): immutable.seq.Indexed<K>
    fun valueSeq(): immutable.seq.Indexed<V>
    fun entrySeq(): immutable.seq.Indexed<Array<Any>>
    fun <M> map(mapper: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> M, context: Any? = definedExternally /* null */): Iterable<K, M>
    fun filter(predicate: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */): Iterable<K, V>
    fun filterNot(predicate: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */): Iterable<K, V>
    fun reverse(): Iterable<K, V>
    fun sort(comparator: ((valueA: V, valueB: V) -> Number)? = definedExternally /* null */): Iterable<K, V>
    fun <C> sortBy(comparatorValueMapper: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> C, comparator: ((valueA: C, valueB: C) -> Number)? = definedExternally /* null */): Iterable<K, V>
    fun <G> groupBy(grouper: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> G, context: Any? = definedExternally /* null */): immutable.seq.Keyed<G, Iterable<K, V>>
    fun forEach(sideEffect: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Any, context: Any? = definedExternally /* null */): Number
    fun slice(begin: Number? = definedExternally /* null */, end: Number? = definedExternally /* null */): Iterable<K, V>
    fun rest(): Iterable<K, V>
    fun butLast(): Iterable<K, V>
    fun skip(amount: Number): Iterable<K, V>
    fun skipLast(amount: Number): Iterable<K, V>
    fun skipWhile(predicate: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */): Iterable<K, V>
    fun skipUntil(predicate: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */): Iterable<K, V>
    fun take(amount: Number): Iterable<K, V>
    fun takeLast(amount: Number): Iterable<K, V>
    fun takeWhile(predicate: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */): Iterable<K, V>
    fun takeUntil(predicate: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */): Iterable<K, V>
    fun concat(vararg valuesOrIterables: Any): Iterable<K, V>
    fun flatten(depth: Number? = definedExternally /* null */): Iterable<Any, Any>
    fun flatten(shallow: Boolean? = definedExternally /* null */): Iterable<Any, Any>
    fun <MK, MV> flatMap(mapper: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Iterable<MK, MV>, context: Any? = definedExternally /* null */): Iterable<MK, MV>
    fun <MK, MV> flatMap(mapper: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Any, context: Any? = definedExternally /* null */): Iterable<MK, MV>
    fun <R> reduce(reducer: (reduction: R? /*= null*/, value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> R, initialReduction: R? = definedExternally /* null */, context: Any? = definedExternally /* null */): R
    fun <R> reduceRight(reducer: (reduction: R? /*= null*/, value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> R, initialReduction: R? = definedExternally /* null */, context: Any? = definedExternally /* null */): R
    fun every(predicate: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */): Boolean
    fun some(predicate: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */): Boolean
    fun join(separator: String? = definedExternally /* null */): String
    fun isEmpty(): Boolean
    fun count(): Number
    fun count(predicate: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */): Number
    fun <G> countBy(grouper: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> G, context: Any? = definedExternally /* null */): Map<G, Number>
    fun find(predicate: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */, notSetValue: V? = definedExternally /* null */): V
    fun findLast(predicate: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */, notSetValue: V? = definedExternally /* null */): V
    fun findEntry(predicate: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */, notSetValue: V? = definedExternally /* null */): Array<Any>
    fun findLastEntry(predicate: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */, notSetValue: V? = definedExternally /* null */): Array<Any>
    fun findKey(predicate: (value: V? /*= null*/, key: K? /*= null*/, iter: immutable.iterable.Keyed<K, V>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */): K
    fun findLastKey(predicate: (value: V? /*= null*/, key: K? /*= null*/, iter: immutable.iterable.Keyed<K, V>? /*= null*/) -> Boolean, context: Any? = definedExternally /* null */): K
    fun keyOf(searchValue: V): K
    fun lastKeyOf(searchValue: V): K
    fun max(comparator: ((valueA: V, valueB: V) -> Number)? = definedExternally /* null */): V
    fun <C> maxBy(comparatorValueMapper: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> C, comparator: ((valueA: C, valueB: C) -> Number)? = definedExternally /* null */): V
    fun min(comparator: ((valueA: V, valueB: V) -> Number)? = definedExternally /* null */): V
    fun <C> minBy(comparatorValueMapper: (value: V? /*= null*/, key: K? /*= null*/, iter: Iterable<K, V>? /*= null*/) -> C, comparator: ((valueA: C, valueB: C) -> Number)? = definedExternally /* null */): V
    fun isSubset(iter: Iterable<Any, V>): Boolean
    fun isSubset(iter: Array<V>): Boolean
    fun isSuperset(iter: Iterable<Any, V>): Boolean
    fun isSuperset(iter: Array<V>): Boolean
    var size: Number
    fun flatten(): Iterable<Any, Any>
}
external interface Collection<K, V> : Iterable<K, V> {
    override var size: Number
}
external interface `T$3`<T> {
    var value: T
    var done: Boolean
}
external interface Iterator<T> {
    fun next(): `T$3`<T>
}
