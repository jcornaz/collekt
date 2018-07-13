package com.github.jcornaz.collekt.stdlib

import com.github.jcornaz.collekt.api.PersistentSetFactory
import com.github.jcornaz.collekt.test.PersistentSetTest

class StdlibSetTest : PersistentSetTest() {
    override val factory: PersistentSetFactory get() = StdlibSet
}
