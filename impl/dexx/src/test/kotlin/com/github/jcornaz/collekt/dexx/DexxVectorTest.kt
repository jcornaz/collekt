package com.github.jcornaz.collekt.dexx

import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.test.PersistentListTest

class DexxVectorTest : PersistentListTest() {
    override val factory: PersistentListFactory get() = DexxVector
}