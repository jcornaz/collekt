package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.Benchmark

open class ListRemoveBenchmark {

    @Benchmark
    fun removeIndex(input: PersistentInput) = input.list.minusIndex(input.randomIndex)

    @Benchmark
    fun subList(input: PersistentInput) = input.list.slice(input.middleRange.first, input.middleRange.endInclusive)
}

