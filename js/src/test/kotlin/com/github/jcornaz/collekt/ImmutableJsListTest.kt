package com.github.jcornaz.collekt

class ImmutableJsListTest : PersistentListTest() {
    override val factory: PersistentListFactory get() = ImmutableJsList
}
