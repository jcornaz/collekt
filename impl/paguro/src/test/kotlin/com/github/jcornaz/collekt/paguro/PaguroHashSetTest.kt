package com.github.jcornaz.collekt.paguro

import com.github.jcornaz.collekt.api.PersistentSetFactory
import com.github.jcornaz.collekt.test.PersistentSetTest

class PaguroHashSetTest : PersistentSetTest() {
    override val factory: PersistentSetFactory get() = PaguroHashSet
}