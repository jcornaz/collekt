package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.VavrList

class VavrListTest : ListTest() {
    override val factory get() = VavrList
}
