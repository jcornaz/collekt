package com.github.jcornaz.collekt.benchmark

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Warmup
import java.util.concurrent.TimeUnit

@Fork(2)
@Warmup(time = 2, timeUnit = TimeUnit.SECONDS, iterations = 3)
@Measurement(time = 2, timeUnit = TimeUnit.SECONDS, iterations = 10)
open class BuildBenchmark {

    @Benchmark
    fun buildArrayList(input: ArrayListInput) =
            ArrayList<Int>(input.size.toInt()).apply { addAll(input.size) }

    @Benchmark
    fun buildImmutableList(input: PersistentInput) =
            input.factory.from(input.size)
}
