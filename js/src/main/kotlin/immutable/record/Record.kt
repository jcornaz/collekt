@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE")
@file:[JsModule("immutable") JsQualifier("Record")]
package immutable.record

import kotlin.js.*
import kotlin.js.Json

external interface Class {
    @nativeInvoke
    operator fun invoke(): immutable.Map<String, Any>
    @nativeInvoke
    operator fun invoke(values: Json): immutable.Map<String, Any>
    @nativeInvoke
    operator fun invoke(values: immutable.Iterable<String, Any>): immutable.Map<String, Any>
}
