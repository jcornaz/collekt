package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.Benchmark

open class BuildBenchmark {

    @Benchmark
    fun buildList(input: PersistentInput) =
            input.factory.from(input.size)
}
