package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.api.PersistentMapFactory
import com.github.jcornaz.collekt.api.PersistentSetFactory
import com.github.jcornaz.collekt.paguro.PaguroHashMap
import com.github.jcornaz.collekt.paguro.PaguroHashSet
import com.github.jcornaz.collekt.paguro.PaguroRRBTree

public actual var DefaultListFactory: PersistentListFactory = PaguroRRBTree
public actual var DefaultSetFactory: PersistentSetFactory = PaguroHashSet
public actual var DefaultMapFactory: PersistentMapFactory = PaguroHashMap
