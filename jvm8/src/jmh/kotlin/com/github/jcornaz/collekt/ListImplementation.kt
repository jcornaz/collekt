package com.github.jcornaz.collekt

import com.github.jcornaz.collekt.impl.*

enum class ListImplementation(val factory: PersistentListFactory) : PersistentListFactory by factory {
    KOTLIN_LIST(KotlinList) {
        override fun <E> from(iterable: Iterable<E>): PersistentList<E> =
                factory.from(iterable) // faster creation as KotlinList cannot be unbalanced
    },

    PAGURO_RRB_TREE(PaguroRRBTree),

    VAVR_LIST(VavrList),

    PCOLLECTION_VECTOR(PCollectionVector),

    DEXX_VECTOR(DexxVector),

    ;

    // Use fold instead of delegating to factory.from() in order to create unbalanced data structure when relevant
    override fun <E> from(iterable: Iterable<E>): PersistentList<E> =
            iterable.fold(factory.empty()) { result, element -> result + element }
}
