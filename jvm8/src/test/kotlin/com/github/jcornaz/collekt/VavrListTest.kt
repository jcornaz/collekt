package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.VavrList

class VavrListTest : PersistentListTest() {
    override val factory get() = VavrList
}
