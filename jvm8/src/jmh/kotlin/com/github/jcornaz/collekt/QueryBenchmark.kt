package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Warmup
import java.util.concurrent.TimeUnit


@Fork(3)
@Warmup(time = 200, timeUnit = TimeUnit.MILLISECONDS, iterations = 3)
@Measurement(time = 200, timeUnit = TimeUnit.MILLISECONDS, iterations = 15)
open class QueryBenchmark {

    @Benchmark
    fun size(input: PersistentInput) = input.list.size

    @Benchmark
    fun isEmpty(input: PersistentInput) = input.list.isEmpty

    @Benchmark
    fun get(input: PersistentInput) = input.list[input.randomIndex]
}
