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
