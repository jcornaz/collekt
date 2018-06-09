package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.Benchmark

open class ListAddBenchmark {

    @Benchmark
    fun appendElement(input: PersistentInput) = input.list + 0

    @Benchmark
    fun insertElement(input: PersistentInput) = input.list.plus(index = input.randomIndex, element = 0)

    @Benchmark
    fun appendCollection(input: PersistentInput) = input.list + input.list

    @Benchmark
    fun insertCollection(input: PersistentInput) = input.list.plus(index = input.randomIndex, elements = input.list)
}
