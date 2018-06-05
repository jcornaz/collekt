package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

open class ListQueryBenchmark {

    @Benchmark
    fun measureSize(input: PersistentInput) = input.list.size

    @Benchmark
    fun measureIsEmpty(input: PersistentInput) = input.list.isEmpty

    @Benchmark
    fun measureRandomAccess(input: PersistentInput) = input.list[input.randomIndex]

    @Benchmark
    fun measureGetFirst(input: PersistentInput) = input.list[0]

    @Benchmark
    fun measureGetLast(input: PersistentInput) = input.list[input.size.toInt() - 1]

    @Benchmark
//    @Measurement(time = 2, timeUnit = TimeUnit.SECONDS)
//    @OutputTimeUnit(TimeUnit.SECONDS)
    fun measureContainsTrue(input: PersistentInput) = input.randomIndex in input.list

    @Benchmark
//    @Measurement(time = 2, timeUnit = TimeUnit.SECONDS)
//    @OutputTimeUnit(TimeUnit.SECONDS)
    fun measureContainsFalse(input: PersistentInput) = input.size.toInt() in input.list

    @Benchmark
//    @Measurement(time = 2, timeUnit = TimeUnit.SECONDS)
//    @OutputTimeUnit(TimeUnit.SECONDS)
    fun measureIteration(input: PersistentInput, blackhole: Blackhole) {
        input.list.forEach { blackhole.consume(it) }
    }
}
