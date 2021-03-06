package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.api.PersistentMapFactory
import com.github.jcornaz.collekt.api.PersistentSetFactory
import com.github.jcornaz.collekt.stdlib.StdlibList
import com.github.jcornaz.collekt.stdlib.StdlibMap
import com.github.jcornaz.collekt.stdlib.StdlibSet

public actual var DefaultListFactory: PersistentListFactory = StdlibList
public actual var DefaultSetFactory: PersistentSetFactory = StdlibSet
public actual var DefaultMapFactory: PersistentMapFactory = StdlibMap
