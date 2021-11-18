package com.igorwojda.matrix.findrectangle

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun findRectangle(image: List<List<Int>>): List<Int>? {
    val result = mutableListOf<Int>()
    var i = 0
    // Find the top left corner
    while (i < image.size) {
        val j = image[i].indexOfFirst { it == 0 }
        if (j != -1) {
            result.addAll(listOf(i, j))
            break
        }
        i++
    }
    i = image.size - 1
    // Find the bottom right corner
    while (i >= 0) {
        val j = image[i].indexOfLast { it == 0 }
        if (j != -1) {
            result.addAll(listOf(i, j))
            break
        }
        i--
    }
    return if (result.size == 4) result else null
}

private class Test {
    @Test
    fun `find rectangle in image 1`() {
        val image = listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 0, 0, 0, 1),
            listOf(1, 1, 1, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        )

        findRectangle(image) shouldBeEqualTo listOf(2, 3, 3, 5)
    }

    @Test
    fun `find rectangle in image 2`() {
        val image = listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 0)
        )

        findRectangle(image) shouldBeEqualTo listOf(4, 6, 4, 6)
    }

    @Test
    fun `find rectangle in image 3`() {
        val image = listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 0, 0),
            listOf(1, 1, 1, 1, 1, 0, 0)
        )

        findRectangle(image) shouldBeEqualTo listOf(3, 5, 4, 6)
    }

    @Test
    fun `find rectangle in image 4`() {
        val image = listOf(
            listOf(0, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        )

        findRectangle(image) shouldBeEqualTo listOf(0, 0, 0, 0)
    }

    @Test
    fun `find rectangle in image 5`() {
        val image = listOf(
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1),
            listOf(0, 0, 1, 1, 1, 1, 1),
            listOf(0, 0, 1, 1, 1, 1, 1),
            listOf(1, 1, 1, 1, 1, 1, 1)
        )

        findRectangle(image) shouldBeEqualTo listOf(2, 0, 3, 1)
    }

    @Test
    fun `find rectangle in image that has no background`() {
        val image = listOf(
            listOf(0)
        )

        findRectangle(image) shouldBeEqualTo listOf(0, 0, 0, 0)
    }
}
