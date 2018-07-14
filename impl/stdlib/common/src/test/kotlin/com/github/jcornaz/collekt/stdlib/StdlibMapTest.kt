package com.github.jcornaz.collekt.stdlib

import com.github.jcornaz.collekt.api.PersistentMapFactory
import com.github.jcornaz.collekt.test.PersistentMapTest

class StdlibMapTest : PersistentMapTest() {
    override val mapFactory: PersistentMapFactory get() = StdlibMap
}