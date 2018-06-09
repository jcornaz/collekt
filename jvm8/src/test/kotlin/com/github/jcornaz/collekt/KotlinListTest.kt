package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.KotlinList

class KotlinListTest : PersistentListTest() {
    override val factory get() = KotlinList
}
