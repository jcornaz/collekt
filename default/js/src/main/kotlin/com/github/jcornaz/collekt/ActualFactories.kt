package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.stdlib.StdlibList

public actual var DefaultListFactory: PersistentListFactory = StdlibList
