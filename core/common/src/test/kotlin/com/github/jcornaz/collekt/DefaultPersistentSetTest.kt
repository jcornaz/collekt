package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.PersistentSetFactory
import com.github.jcornaz.collekt.test.PersistentSetTest

class DefaultPersistentSetTest : PersistentSetTest() {
    override val factory: PersistentSetFactory get() = DefaultSetFactory
}