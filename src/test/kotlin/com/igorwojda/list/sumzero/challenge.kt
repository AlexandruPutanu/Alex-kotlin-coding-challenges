package com.igorwojda.list.sumzero

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.math.abs

private fun sumZero(list: List<Int>): Pair<Int, Int>? {
    var leftIndex = 0
    var rightIndex = list.size - 1
    while (leftIndex < rightIndex) {
        if (list[leftIndex] == -list[rightIndex])
            return list[leftIndex] to list[rightIndex]
        if (abs(list[leftIndex]) > abs(list[rightIndex]))
            leftIndex++
        else
            rightIndex--
    }
    return null
}

private class Test {
    @Test
    fun `sumZero empty list return null`() {
        sumZero(listOf()) shouldBeEqualTo null
    }

    @Test
    fun `sumZero 1, 2 return null`() {
        sumZero(listOf(1, 2)) shouldBeEqualTo null
    }

    @Test
    fun `sumZero 1, 2, 4, 7 return null`() {
        sumZero(listOf(1, 2, 4, 7)) shouldBeEqualTo null
    }

    @Test
    fun `sumZero -4, -3, -2, 1, 2, 3, 5 return Pair(-3, 3)`() {
        sumZero(listOf(-4, -3, -2, 1, 2, 3, 5)) shouldBeEqualTo Pair(-3, 3)
    }

    @Test
    fun `sumZero -4, -3, -2, 1, 2, 5 return Pair(-2, 2)`() {
        sumZero(listOf(-4, -3, -2, 1, 2, 5)) shouldBeEqualTo Pair(-2, 2)
    }
}
