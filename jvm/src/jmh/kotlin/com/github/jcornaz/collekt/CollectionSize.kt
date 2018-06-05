package com.github.jcornaz.collekt

enum class CollectionSize(private val size: Int) : Iterable<Int> {

    HUNDRED(100),
    TEN_THOUSAND(10_000),
    ONE_MILLION(1_000_000),
    HUNDRED_MILLION(100_000_000),

    ;

    fun toInt(): Int = size

    override fun iterator() = (0 until size).iterator()
}
