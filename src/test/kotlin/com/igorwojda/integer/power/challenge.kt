package com.igorwojda.integer.power

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.reflect.KFunction

//private fun power(base: Int, exponent: Int): Int {
//    var result = base
//    var pow = exponent
//    if(exponent==0){
//        return 1
//    }
//    while(pow>1){
//        result*=base
//        pow--
//    }
//    return result
//}

private fun power(base: Int, exponent: Int): Int {
    operator fun List<Int>.invoke() = reduce { it, acc -> it * acc }
    val reference: KFunction<Int> = List<Int>::invoke
    return (reference.call(List(exponent) { base }))
}

private class Test {
    @Test
    fun `power 2^1 returns 2`() {
        power(2, 1) shouldBeEqualTo 2
    }

    @Test
    fun `power 2^2 returns 2`() {
        power(2, 2) shouldBeEqualTo 4
    }

    @Test
    fun `power 2^3 returns 8`() {
        power(2, 3) shouldBeEqualTo 8
    }

    @Test
    fun `power 3^4 returns 81`() {
        power(3, 4) shouldBeEqualTo 81
    }
}
