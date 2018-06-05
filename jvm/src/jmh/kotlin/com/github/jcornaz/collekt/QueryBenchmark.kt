package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.*

open class QueryBenchmark {

    @State(Scope.Benchmark)
    open class Input {

        @Param
        lateinit var size: CollectionSize
            private set

        @Param
        private lateinit var implementation: ListImplementation

        lateinit var list: PersistentList<Int>
            private set

        var randomIndex: Int = 0
            private set

        private val rng = Random()

        @Setup(Level.Iteration)
        fun chooseRandomIndex() {
            randomIndex = rng.nextInt(size.toInt() - 1)
        }

        @Setup(Level.Trial)
        fun createSource() {
            list = implementation.from(size)
        }
    }

    @Benchmark
    fun measureSize(input: Input) = input.list.size

    @Benchmark
    fun measureIsEmpty(input: Input) = input.list.isEmpty

    @Benchmark
    fun measureRandomAccess(input: Input) = input.list[input.randomIndex]

    @Benchmark
    fun measureGetFirst(input: Input) = input.list[0]

    @Benchmark
    fun measureGetLast(input: Input) = input.list[input.size.toInt() - 1]

    @Benchmark
    fun measureContainsTrue(input: Input) = input.randomIndex in input.list

    @Benchmark
    fun measureContainsFalse(input: Input) = input.size.toInt() in input.list

    @Benchmark
    fun measureIteration(input: Input, blackhole: Blackhole) {
        input.list.forEach { blackhole.consume(it) }
    }
}
