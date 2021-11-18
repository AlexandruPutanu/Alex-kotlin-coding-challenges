package com.igorwojda.matrix.spiralmatrixgenerator

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun generateSpiralMatrix(n: Int): List<MutableList<Int?>> {
    // Initialize the spiraling number, the matrix, the current depth of the spiral
    var currentNumber = 1
    val matrix = List(n) { MutableList<Int?>(n) { null } }
    var circle = 0
    var rowIndex = 0
    var columnIndex = -1
    do {
        columnIndex++
        // Move along the top horizontal line
        while (columnIndex < n - circle) {
            matrix[rowIndex][columnIndex] = currentNumber
            currentNumber++
            columnIndex++
        }
        // If you reached the center of the spiral, then exit
        if (currentNumber > n * n)
            break
        columnIndex--
        rowIndex++
        // Move along the right vertical line downwards
        while (rowIndex < n - circle) {
            matrix[rowIndex][columnIndex] = currentNumber
            currentNumber++
            rowIndex++
        }
        columnIndex--
        rowIndex--
        // Put the numbers on the bottom line, from right to left
        while (columnIndex >= circle) {
            matrix[rowIndex][columnIndex] = currentNumber
            currentNumber++
            columnIndex--
        }
        rowIndex--
        columnIndex++
        // Put the numbers on the left column, from bottom to top
        while (rowIndex > circle) {
            matrix[rowIndex][columnIndex] = currentNumber
            currentNumber++
            rowIndex--
        }
        circle++
        rowIndex++
    } while (currentNumber <= n * n)
    return matrix
}

private class Test {
    @Test
    fun `generateSpiralMatrix generates a 2x2 matrix`() {
        val matrix = generateSpiralMatrix(2)
        matrix.size shouldBeEqualTo 2
        matrix[0] shouldBeEqualTo listOf(1, 2)
        matrix[1] shouldBeEqualTo listOf(4, 3)
    }

    @Test
    fun `generateSpiralMatrix generates a 3x3 matrix`() {
        val matrix = generateSpiralMatrix(3)
        matrix.size shouldBeEqualTo 3
        matrix[0] shouldBeEqualTo listOf(1, 2, 3)
        matrix[1] shouldBeEqualTo listOf(8, 9, 4)
        matrix[2] shouldBeEqualTo listOf(7, 6, 5)
    }

    @Test
    fun `generateSpiralMatrix generates a 4x4 matrix`() {
        val matrix = generateSpiralMatrix(4)
        matrix.size shouldBeEqualTo 4
        matrix[0] shouldBeEqualTo listOf(1, 2, 3, 4)
        matrix[1] shouldBeEqualTo listOf(12, 13, 14, 5)
        matrix[2] shouldBeEqualTo listOf(11, 16, 15, 6)
        matrix[3] shouldBeEqualTo listOf(10, 9, 8, 7)
    }
}
