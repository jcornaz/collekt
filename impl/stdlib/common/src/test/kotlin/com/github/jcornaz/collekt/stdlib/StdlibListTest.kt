package com.github.jcornaz.collekt.stdlib

import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.test.PersistentListTest

class StdlibListTest : PersistentListTest() {
    override val factory: PersistentListFactory get() = StdlibList
}