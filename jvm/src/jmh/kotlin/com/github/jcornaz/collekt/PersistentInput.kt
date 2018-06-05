package com.github.jcornaz.collekt

import org.openjdk.jmh.annotations.*
import java.util.*


@State(Scope.Benchmark)
open class PersistentInput {

    @Param
    lateinit var size: CollectionSize
        private set

    @Param
    private lateinit var implementation: ListImplementation

    lateinit var list: PersistentList<Int>
        private set

    var randomIndex: Int = 0
        private set

    lateinit var middleRange: IntRange
        private set

    private val rng = Random()

    @Setup(Level.Trial)
    fun createSource() {
        list = implementation.from(size)
    }

    @Setup(Level.Trial)
    fun setMiddleRange() {
        middleRange = (size.toInt() / 4)..(size.toInt() * 3 / 4)
    }

    @Setup(Level.Iteration)
    fun chooseRandomIndex() {
        randomIndex = rng.nextInt(size.toInt() - 1)
    }
}
