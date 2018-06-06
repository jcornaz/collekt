package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.*
import java.util.*

open class ArrayListBenchmark {

    @Benchmark
    fun baseline(input: ArrayListInput) = input.list

    @Benchmark
    fun appendElement(input: ArrayListInput) {
        input.list.add(0)
    }

    @Benchmark
    fun insertElement(input: ArrayListInput) {
        input.list.add(index = input.randomIndex, element = 0)
    }

    @Benchmark
    fun appendCollection(input: ArrayListInput) {
        input.list.addAll(input.source)
    }

    @Benchmark
    fun insertCollection(input: ArrayListInput) {
        input.list.addAll(input.randomIndex, input.source)
    }

    @Benchmark
    fun removeIndex(input: ArrayListInput) {
        input.list.removeAt(input.randomIndex)
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
