package com.github.jcornaz.collekt

class SequenceToImmutableList : ListTest() {
    override fun create(v1: Int, v2: Int, v3: Int, v4: Int) = sequenceOf(v1, v2, v3, v4).toImmutableList()
    override fun createEmpty() = emptySequence<Int>().toImmutableList()
}
