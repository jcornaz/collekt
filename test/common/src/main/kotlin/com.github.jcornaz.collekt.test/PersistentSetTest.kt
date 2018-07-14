package com.github.jcornaz.collekt.test

import com.github.jcornaz.collekt.api.PersistentSetFactory
import com.github.jcornaz.collekt.api.of
import kotlin.test.Test
import kotlin.test.assertEquals

abstract class PersistentSetTest : PersistentCollectionTest() {
    abstract override val factory: PersistentSetFactory

    @Test
    fun shouldNotContainsDuplicates() {
        val result = factory.of(1, 2, 3, 1, 3)

        assertEquals(3, result.size)
        assertEquals(factory.of(1, 2, 3), result)
    }

    @Test
    fun shouldNotAddExistingValue() {
        assertEquals(factory.of(1, 2, 3), factory.of(1, 2, 3) + 2)

        assertEquals(3, (factory.of(1, 2, 3) + 2).size)
    }
}