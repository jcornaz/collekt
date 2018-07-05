package com.github.jcornaz.collekt.api

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Warmup
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

@Fork(2)
@Warmup(time = 2, timeUnit = TimeUnit.SECONDS, iterations = 3)
@Measurement(time = 2, timeUnit = TimeUnit.SECONDS, iterations = 10)
open class IterationBenchmark {

    @Benchmark
    fun iteration(input: PersistentInput, blackhole: Blackhole) {
        input.list.forEach { blackhole.consume(it) }
    }
}
