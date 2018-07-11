package com.github.jcornaz.collekt.benchmark

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Warmup
import java.util.concurrent.TimeUnit

@Fork(2)
@Warmup(time = 500, timeUnit = TimeUnit.MILLISECONDS, iterations = 3)
@Measurement(time = 500, timeUnit = TimeUnit.MILLISECONDS, iterations = 10)
open class AddElementBenchmark {

    @Benchmark
    fun appendElementToArrayList(input: ArrayListInput) {
        input.list.add(input.size.toInt())
    }

    @Benchmark
    fun appendElement(input: PersistentInput) = input.list + input.size.toInt()

    @Benchmark
    fun insertElementToArrayList(input: ArrayListInput) {
        input.list.add(input.randomIndex, input.size.toInt())
    }

    @Benchmark
    fun insertElement(input: PersistentInput) = input.list.plus(index = input.randomIndex, element = input.size.toInt())

}
