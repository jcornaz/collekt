package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.Benchmark

open class ListRemoveBenchmark {

    @Benchmark
    fun measureRemoveElementContained(input: PersistentInput) = input.list - input.randomIndex

    @Benchmark
    fun measureRemoveElementNotContained(input: PersistentInput) = input.list - input.size.toInt()

    @Benchmark
    fun measureRemoveIndex(input: PersistentInput) = input.list.minusIndex(input.randomIndex)

    @Benchmark
    fun measureSubList(input: PersistentInput) = input.list.subList(input.middleRange.first, input.middleRange.endInclusive)
}

