package com.github.jcornaz.collekt

class DefaultListTest : ListTest() {
  override val factory = object : PersistentListFactory {
    override val empty get() = emptyPersistentList<Nothing>()

    override fun <E> from(iterable: Iterable<E>) = iterable.toPersistentList()
  }
}