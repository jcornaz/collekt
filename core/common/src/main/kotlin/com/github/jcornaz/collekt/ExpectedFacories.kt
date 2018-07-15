package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.api.PersistentListFactory
import com.github.jcornaz.collekt.api.PersistentMapFactory
import com.github.jcornaz.collekt.api.PersistentSetFactory

/**
 * Default [PersistentListFactory]
 */
public expect var DefaultListFactory: PersistentListFactory

/**
 * Default [PersistentListFactory]
 */
public expect var DefaultSetFactory: PersistentSetFactory

/**
 * Default [PersistentMapFactory]
 */
public expect var DefaultMapFactory: PersistentMapFactory
