package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.DexxVector

class DexxVectorTest : PersistentListTest() {
    override val factory get() = DexxVector
}
