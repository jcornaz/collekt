package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.Benchmark

open class ListQueryBenchmark {

    @Benchmark
    fun size(input: PersistentInput) = input.list.size

    @Benchmark
    fun isEmpty(input: PersistentInput) = input.list.isEmpty

    @Benchmark
    fun get(input: PersistentInput) = input.list[input.randomIndex]
}
