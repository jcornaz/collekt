package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.PaguroRRBTree

class PaguroRRBTreeTest : ListTest() {
    override val factory get() = PaguroRRBTree
}
