package com.github.jcornaz.collekt.impl

internal fun checkRange(index: Int, size: Int) {
    if (index < 0 || index >= size) throw IndexOutOfBoundsException("Index: $index, Size: $size")
}

internal fun checkRangeInclusive(index: Int, size: Int) {
    if (index < 0 || index > size) throw IndexOutOfBoundsException("Index: $index, Size: $size")
}
