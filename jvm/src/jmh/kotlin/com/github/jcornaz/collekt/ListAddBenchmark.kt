package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.Benchmark

open class ListAddBenchmark {

    @Benchmark
    fun measureAppendElement(input: PersistentInput) = input.list + input.size.toInt()

    @Benchmark
    fun measurePrependElement(input: PersistentInput) = input.list.plus(input.size.toInt(), index = 0)

    @Benchmark
    fun measureInsertElement(input: PersistentInput) = input.list.plus(input.size.toInt(), index = input.randomIndex)

    @Benchmark
    fun measureAppendCollection(input: PersistentInput) = input.list + input.list

    @Benchmark
    fun measurePrependCollection(input: PersistentInput) = input.list.plus(input.list, index = 0)

    @Benchmark
    fun measureInsertCollection(input: PersistentInput) = input.list.plus(input.list, index = input.randomIndex)
}
