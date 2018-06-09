package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.PaguroRRBTree

class PaguroRRBTreeTest : PersistentListTest() {
    override val factory get() = PaguroRRBTree
}
