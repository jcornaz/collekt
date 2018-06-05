package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.*
import java.util.*

open class ArrayListBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    fun measureBaseline(input: ArrayListInput) = Unit

    @Benchmark
    fun measureAppendElement(input: ArrayListInput) {
        input.list.add(0)
    }

    @Benchmark
    fun measureInsertElement(input: ArrayListInput) {
        input.list.add(index = input.randomIndex, element = 0)
    }

    @Benchmark
    fun measureAppendCollection(input: ArrayListInput) {
        input.list.addAll(input.source)
    }

    @Benchmark
    fun measureInsertCollection(input: ArrayListInput) {
        input.list.addAll(input.randomIndex, input.source)
    }

    @State(Scope.Benchmark)
    open class ArrayListInput {

        @Param
        lateinit var size: CollectionSize
            private set

        lateinit var source: ArrayList<Int>
            private set

        lateinit var list: ArrayList<Int>
            private set

        var randomIndex: Int = 0
            private set

        private val rng = Random()

        @Setup(Level.Trial)
        fun createSource() {
            source = ArrayList<Int>(size.toInt()).apply { addAll(this@ArrayListInput.size) }
        }

        @Setup(Level.Iteration)
        fun chooseRandomIndex() {
            randomIndex = rng.nextInt(size.toInt() - 1)
        }

        @Setup(Level.Invocation)
        fun copySourceToList() {
            list = ArrayList(source)
        }
    }

}
