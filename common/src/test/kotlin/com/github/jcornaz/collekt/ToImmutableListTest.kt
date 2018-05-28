package com.github.jcornaz.collekt

class ToImmutableListTest : ListTest() {
    override fun create(v1: Int, v2: Int, v3: Int, v4: Int) = listOf(v1, v2, v3, v4).toImmutableList()
    override fun createEmpty() = emptyList<Int>().toImmutableList()
}
