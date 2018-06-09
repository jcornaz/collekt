package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.PCollectionVector

class PCollectionVectorTest : PersistentListTest() {
    override val factory get() = PCollectionVector
}
