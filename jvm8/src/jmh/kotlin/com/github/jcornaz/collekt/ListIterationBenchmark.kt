package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.infra.Blackhole

open class ListIterationBenchmark {

    @Benchmark
    fun indexOfContained(input: PersistentInput) = input.list.indexOf(input.randomIndex)

    @Benchmark
    fun indexOfNotContained(input: PersistentInput) = input.list.indexOf(input.size.toInt())

    @Benchmark
    fun iteration(input: PersistentInput, blackhole: Blackhole) {
        input.list.forEach { blackhole.consume(it) }
    }
}
