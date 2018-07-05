package com.github.jcornaz.collekt.benchmark

import org.openjdk.jmh.annotations.Benchmark

open class RemoveElementBenchmark {

    @Benchmark
    fun removeIndexInArrayList(input: ArrayListInput) = input.list.removeAt(input.randomIndex)

    @Benchmark
    fun removeIndex(input: PersistentInput) = input.list.minusIndex(input.randomIndex)
}
