package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Warmup
import java.util.concurrent.TimeUnit

@Fork(2)
@Warmup(time = 500, timeUnit = TimeUnit.MILLISECONDS, iterations = 3)
@Measurement(time = 500, timeUnit = TimeUnit.MILLISECONDS, iterations = 10)
open class SplitAndSliceBenchmark {

    @Benchmark
    fun slice(input: PersistentInput) = input.list.subList(input.middleRange.first, input.middleRange.endInclusive)

    @Benchmark
    fun split(input: PersistentInput) = input.list.split(input.randomIndex)
}

