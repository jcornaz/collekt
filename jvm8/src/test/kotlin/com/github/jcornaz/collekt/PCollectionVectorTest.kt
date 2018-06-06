package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.PCollectionVector

class PCollectionVectorTest : ListTest() {
    override val factory get() = PCollectionVector
}
