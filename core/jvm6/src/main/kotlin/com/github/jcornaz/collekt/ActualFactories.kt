package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.api.PersistentSetFactory
import com.github.jcornaz.collekt.dexx.DexxSet
import com.github.jcornaz.collekt.dexx.DexxVector

public actual var DefaultListFactory: PersistentListFactory = DexxVector
public actual var DefaultSetFactory: PersistentSetFactory = DexxSet
