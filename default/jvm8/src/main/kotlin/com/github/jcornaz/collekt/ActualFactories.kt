package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.paguro.PaguroRRBTree

public actual var DefaultListFactory: PersistentListFactory = PaguroRRBTree
