package com.github.jcornaz.collekt

class ImmutableListOfTest : ListTest() {

    override fun create(v1: Int, v2: Int, v3: Int, v4: Int) = immutableListOf(v1, v2, v3, v4)
    override fun createEmpty() = immutableListOf<Int>()
}
