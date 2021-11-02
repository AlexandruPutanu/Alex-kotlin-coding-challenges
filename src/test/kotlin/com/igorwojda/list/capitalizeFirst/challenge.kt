package com.igorwojda.list.capitalizeFirst

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun capitalizeFirst(list: List<String>): List<String> {
    return if (list.isEmpty()) emptyList() else listOf(list[0].capitalize()) + capitalizeFirst(
        list.subList(
            1,
            list.size
        )
    )
}

private class Test {
    @Test
    fun `capitalize list with one string`() {
        capitalizeFirst(listOf("igor")) shouldBeEqualTo listOf("Igor")
    }

    @Test
    fun `capitalize list with two strings`() {
        capitalizeFirst(listOf("igor", "wojda")) shouldBeEqualTo listOf("Igor", "Wojda")
    }

    @Test
    fun `capitalize empty list`() {
        capitalizeFirst(listOf("")) shouldBeEqualTo listOf("")
    }

    @Test
    fun `capitalize list with sentence`() {
        capitalizeFirst(listOf("what a", "beautiful", "morning")) shouldBeEqualTo listOf(
            "What a",
            "Beautiful",
            "Morning"
        )
    }
}
