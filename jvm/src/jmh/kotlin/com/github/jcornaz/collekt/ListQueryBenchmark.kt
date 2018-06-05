package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.infra.Blackhole

open class ListQueryBenchmark {

    @Benchmark
    fun measureSize(input: PersistentInput) = input.list.size

    @Benchmark
    fun measureIsEmpty(input: PersistentInput) = input.list.isEmpty

    @Benchmark
    fun measureRandomAccess(input: PersistentInput) = input.list[input.randomIndex]

    @Benchmark
    fun measureContainsTrue(input: PersistentInput) = input.randomIndex in input.list

    @Benchmark
    fun measureContainsFalse(input: PersistentInput) = input.size.toInt() in input.list

    @Benchmark
    fun measureIteration(input: PersistentInput, blackhole: Blackhole) {
        input.list.forEach { blackhole.consume(it) }
    }
}
