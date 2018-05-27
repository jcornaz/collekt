package com.github.jcornaz.collekt.impl

internal fun checkRange(index: Int, size: Int) {
    if (index < 0 || index >= size) throw IndexOutOfBoundsException("Index: $index, Size: $size")
}

internal fun checkRangeInclusive(index: Int, size: Int) {
    if (index < 0 || index > size) throw IndexOutOfBoundsException("Index: $index, Size: $size")
}

internal fun checkSublistRange(fromIndex: Int, toIndex: Int, size: Int) {
    if (fromIndex < 0) throw IndexOutOfBoundsException("fromIndex = $fromIndex")
    if (toIndex > size) throw IndexOutOfBoundsException("toIndex = $toIndex")
    if (fromIndex > toIndex) throw IllegalArgumentException("fromIndex($fromIndex) > toIndex($toIndex)")
}
