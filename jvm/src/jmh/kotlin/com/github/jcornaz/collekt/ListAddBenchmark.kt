package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.Benchmark

open class ListAddBenchmark {

    @Benchmark
    fun measureAppendElement(input: PersistentInput) = input.list + 0

    @Benchmark
    fun measureInsertElement(input: PersistentInput) = input.list.plus(0, index = input.randomIndex)

    @Benchmark
    fun measureAppendCollection(input: PersistentInput) = input.list + input.list

    @Benchmark
    fun measureInsertCollection(input: PersistentInput) = input.list.plus(input.list, index = input.randomIndex)
}
