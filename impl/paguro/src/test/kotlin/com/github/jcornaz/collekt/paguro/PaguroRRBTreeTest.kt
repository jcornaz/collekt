package com.github.jcornaz.collekt.paguro

import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.test.PersistentListTest

class PaguroRRBTreeTest : PersistentListTest() {
    override val factory: PersistentListFactory get() = PaguroRRBTree
}