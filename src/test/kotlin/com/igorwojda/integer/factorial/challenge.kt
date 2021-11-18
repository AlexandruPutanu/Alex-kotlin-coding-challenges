package com.igorwojda.integer.factorial

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeLessThan
import org.junit.jupiter.api.Test
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

private fun factorial(n: Int): Int {
    return if (n == 0) 1 else (1..n).toList().reduce { acc: Int, current: Int -> acc * current }
}

// Recursive tail rec implementation
private tailrec fun factorialTailRec(n: Int, currentFactorial: Int = 1): Int {
    return if (n == 1 || n == 0)
        return currentFactorial
    else factorialTailRec(n - 1, n * currentFactorial)
}

// Recursive implementation
private fun factorialRec(n: Int): Int {
    return if (n == 1 || n == 0)
        return 1
    else n * factorialRec(n - 1)
}

class RecursiveFactorial {
    @Test
    fun `timeSpent`(){
        val initial = measureNanoTime {
            println(factorial(12))
        }
        val recursive= measureNanoTime {
            println(factorialRec(12))
        }
        val tailRecursive = measureNanoTime {
            println(factorialTailRec(12))
        }
        println("Time spent in ns:\n" +
                "initial:       $initial\n" +
                "recursive:     $recursive\n" +
                "tailRecursive: $tailRecursive")
        tailRecursive shouldBeLessThan recursive shouldBeLessThan initial
    }

    @Test
    fun `factorial 0 should equal 1`() {
        factorial(0) shouldBeEqualTo 1
    }

    @Test
    fun `factorial 3 should equal 6`() {
        factorial(3) shouldBeEqualTo 6
    }

    @Test
    fun `factorial 5 should equal 120`() {
        factorial(5) shouldBeEqualTo 120
    }

    @Test
    fun `factorial 10 should equal 3628800`() {
        factorial(10) shouldBeEqualTo 3628800
    }
}
