package com.github.jcornaz.collekt.paguro

import com.github.jcornaz.collekt.api.PersistentMapFactory
import com.github.jcornaz.collekt.test.PersistentMapTest

class PaguroHashMapTest : PersistentMapTest() {
    override val mapFactory: PersistentMapFactory get() = PaguroHashMap
}
