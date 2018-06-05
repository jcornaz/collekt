package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.infra.Blackhole


open class IterationBenchmark {

    @Benchmark
    fun measureIteration(input: PersistentInput, blackhole: Blackhole) {
        input.list.forEach { blackhole.consume(it) }
    }
}
