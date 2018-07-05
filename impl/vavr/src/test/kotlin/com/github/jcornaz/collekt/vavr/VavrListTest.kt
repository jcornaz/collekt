package com.github.jcornaz.collekt.vavr

import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.test.PersistentListTest

class VavrListTest : PersistentListTest() {
    override val factory: PersistentListFactory get() = VavrList
}