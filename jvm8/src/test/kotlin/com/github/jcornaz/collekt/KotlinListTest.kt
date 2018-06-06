package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.KotlinList

class KotlinListTest : ListTest() {
    override val factory get() = KotlinList
}
