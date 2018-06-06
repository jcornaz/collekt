package com.github.jcornaz.collekt

enum class CollectionSize(private val size: Int) : Iterable<Int> {

    HUNDRED_THOUSAND(100_000),
    TEN_MILLION(10_000_000),

    ;

    fun toInt(): Int = size

    override fun iterator() = (0 until size).iterator()
}
