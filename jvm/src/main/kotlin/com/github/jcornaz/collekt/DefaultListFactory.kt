package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.KotlinList

internal actual val DefaultListFactory: PersistentListFactory = KotlinList
internal actual val DefaultSetFactory: PersistentSetFactory = TODO()

