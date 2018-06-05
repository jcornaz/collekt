package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.Benchmark

open class QueryBenchmark {

    @Benchmark
    fun measureSize(input: PersistentInput) = input.list.size

    @Benchmark
    fun measureIsEmpty(input: PersistentInput) = input.list.isEmpty

    @Benchmark
    fun measureRandomAccess(input: PersistentInput) = input.list[input.randomIndex]

    @Benchmark
    fun measureGetFirst(input: PersistentInput) = input.list[0]

    @Benchmark
    fun measureGetLast(input: PersistentInput) = input.list[input.size.toInt() - 1]

    @Benchmark
    fun measureContainsTrue(input: PersistentInput) = input.randomIndex in input.list

    @Benchmark
    fun measureContainsFalse(input: PersistentInput) = input.size.toInt() in input.list
}
