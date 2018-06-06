package com.github.jcornaz.collekt

enum class CollectionSize(private val size: Int) : Iterable<Int> {

    ONE_MILLION(1_000_000),

    ;

    fun toInt(): Int = size

    override fun iterator() = (0 until size).iterator()
}
