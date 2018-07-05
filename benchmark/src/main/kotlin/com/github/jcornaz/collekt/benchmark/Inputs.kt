package com.github.jcornaz.collekt.benchmark

import com.github.jcornaz.collekt.api.PersistentList
import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.dexx.DexxVector
import com.github.jcornaz.collekt.paguro.PaguroRRBTree
import com.github.jcornaz.collekt.pcollections.PCollectionVector
import com.github.jcornaz.collekt.stdlib.StdlibList
import com.github.jcornaz.collekt.vavr.VavrList
import org.openjdk.jmh.annotations.*
import java.util.*


enum class CollectionSize(private val size: Int) : Iterable<Int> {

    HUNDRED_THOUSAND(100_000),

    ;

    fun toInt(): Int = size

    override fun iterator() = (0 until size).iterator()
}

enum class ListImplementation(val factory: PersistentListFactory) : PersistentListFactory by factory {

    STDLIB_LIST(StdlibList),
    PAGURO_RRB_TREE(PaguroRRBTree),
    VAVR_LIST(VavrList),
    PCOLLECTION_VECTOR(PCollectionVector),
    DEXX_VECTOR(DexxVector),

    ;
}

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

    val factory get() = implementation.factory

    @Setup(Level.Trial)
    fun createSource() {
        list = implementation.from(size)
    }

    @Setup(Level.Trial)
    fun chooseRange() {
        middleRange = (size.toInt() / 4)..(size.toInt() * 3 / 4)
    }

    @Setup(Level.Iteration)
    fun chooseRandomIndex() {
        randomIndex = rng.nextInt(size.toInt() - 1)
    }
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
