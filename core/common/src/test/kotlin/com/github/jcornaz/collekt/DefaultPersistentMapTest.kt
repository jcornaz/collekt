package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.PersistentMapFactory
import com.github.jcornaz.collekt.test.PersistentMapTest

class DefaultPersistentMapTest : PersistentMapTest() {
    override val mapFactory: PersistentMapFactory get() = DefaultMapFactory
}