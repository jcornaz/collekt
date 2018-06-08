package com.github.jcornaz.collekt

import kotlin.test.*

class TraversableOperatorTest {

    @Test
    fun forEachActionShouldNotBeCalledForEmptyCollection() {
        emptyPersistentList<Int>().forEach { fail("forEach action should not be called on empty collection") }
    }

    @Test
    fun forEachActionShouldBeCalledForEachElementInTheSameOrder() {
        var expect = 0
        persistentListOf(0, 1, 2, 3, 4).forEach {
            assertEquals(expect, it)
            ++expect
        }
        assertEquals(5, expect)
    }

    @Test
    fun forEachIndexedShouldPassIndexInOrder() {
        var expect = 0
        persistentListOf(0, 1, 2, 3, 4).forEachIndexed { index, element ->
            assertEquals(expect, index)
            assertEquals(expect, element)
            ++expect
        }
        assertEquals(5, expect)
    }

    @Test
    fun foldShouldCallOperationForEachElement() {
        var expectAcc = 0
        var expectElt = 0

        persistentListOf(0, 1, 2, 3).fold(0) { acc, elt ->
            assertEquals(expectAcc, acc)
            assertEquals(expectElt, elt)
            ++expectElt
            expectAcc += elt

            return@fold acc + elt // sum
        }

        assertEquals(4, expectElt)
        assertEquals(6, expectAcc)
    }

    @Test
    fun firstShouldThrowNoSuchElementExceptionForEmptyCollection() {
        assertFailsWith<NoSuchElementException> { emptyPersistentList<Int>().first() }
    }

    @Test
    fun firstShouldReturnTheFirstElement() {
        assertEquals(1, persistentListOf(1, 2, 3, 4).first())
    }

    @Test
    fun firstShouldReturnTheFirstElementEvenIfItIsNull() {
        assertNull(persistentListOf(null, 2, 3).first())
    }

    @Test
    fun firstOrNullShouldReturnNullForEmptyCollection() {
        assertNull(emptyPersistentList<Int>().firstOrNull())
    }

    @Test
    fun firstOrNulLShouldReturnTheFirstElementIfPossible() {
        assertEquals(1, persistentListOf(1, 2, 3, 4).firstOrNull())
    }

    @Test
    fun joinToStringShouldReturnPrefixAndSuffixOnlyForEmptyCollection() {
        assertEquals("prefixpostfix", emptyPersistentList<Int>().joinToString(prefix = "prefix", postfix = "postfix") { fail("should not be called") })
    }

    @Test
    fun joinToStringShouldUseDelimiterBetweenelements() {
        val actual = persistentListOf(0, 1, 2)
                .joinToString(prefix = "[", postfix = "]", separator = ", ") {
                    ('a' + it).toString()
                }

        assertEquals("[a, b, c]", actual)
    }
}
