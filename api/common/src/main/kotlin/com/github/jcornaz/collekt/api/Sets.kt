package com.github.jcornaz.collekt.api

/**
 * An immutable set. Users of this interfaces can safely assumes that the content will never change.
 *
 * It is for instance never needed to create a "defensive copy".
 */
public interface ImmutableSet<out E> : ImmutableCollection<E>, Set<E>

/**
 * An effectively immutable set providing *mutation method* which return new instances instead of modifying the instance.
 *
 * Typically a persistent set is optimized to share as much data as possible between other instances,
 * reducing memory usage, and providing good performance of the *mutation methods*
 */
public interface PersistentSet<out E> : ImmutableSet<E>, PersistentCollection<E> {
  override fun plus(element: @UnsafeVariance E): PersistentSet<E>
  override fun plus(elements: Iterable<@UnsafeVariance E>): PersistentSet<E>
  override fun minus(element: @UnsafeVariance E): PersistentSet<E>
  override fun minus(elements: Iterable<@UnsafeVariance E>): PersistentSet<E>
}
