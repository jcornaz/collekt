package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.api.PersistentSetFactory
import com.github.jcornaz.collekt.paguro.PaguroRRBTree
import com.github.jcornaz.collekt.stdlib.StdlibSet

public actual var DefaultListFactory: PersistentListFactory = PaguroRRBTree
public actual var DefaultSetFactory: PersistentSetFactory = StdlibSet
