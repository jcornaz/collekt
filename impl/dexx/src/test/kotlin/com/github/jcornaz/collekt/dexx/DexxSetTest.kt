package com.github.jcornaz.collekt.dexx

import com.github.jcornaz.collekt.api.PersistentSetFactory
import com.github.jcornaz.collekt.test.PersistentSetTest

class DexxSetTest : PersistentSetTest() {
    override val factory: PersistentSetFactory get() = DexxSet
}
