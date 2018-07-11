package com.github.jcornaz.collekt.pcollections

import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.test.PersistentListTest

class PCollectionVectorTest : PersistentListTest() {
    override val factory: PersistentListFactory get() = PCollectionVector
}