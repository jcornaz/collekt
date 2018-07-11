package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.test.PersistentListTest

class DefaultPersistentListTest : PersistentListTest() {
    override val factory: PersistentListFactory get() = DefaultListFactory
}
