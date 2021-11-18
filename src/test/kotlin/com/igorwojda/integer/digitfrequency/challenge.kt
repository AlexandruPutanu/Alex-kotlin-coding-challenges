package com.igorwojda.integer.digitfrequency

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun equalDigitFrequency(i1: Int, i2: Int): Boolean {
//    val digitMap = mutableMapOf<Int, Int>()
//    var i1Digits = i1
//    var i2Digits = i2
//    while (i1Digits > 0) {
//        digitMap[i1Digits % 10] = digitMap.getOrDefault(i1Digits % 10, 0) + 1
//        i1Digits /= 10
//    }
//    while (i2Digits > 0) {
//        digitMap[i2Digits % 10] = digitMap.getOrDefault(i2Digits % 10, 0) - 1
//        i2Digits /= 10
//    }
//    for (i in digitMap.keys) {
//        if (digitMap[i] != 0)
//            return false
//    }
//    return true
    val i1String = i1.toString()
    val i2String = i2.toString()
    return (i1String.length == i2String.length) &&
            i1String.toList().groupBy { it } == i2String.toList().groupBy { it }
}

private class Test {
    @Test
    fun `"789" and "897" have the same digit frequency`() {
        equalDigitFrequency(789, 897) shouldBeEqualTo true
    }

    @Test
    fun `"123445" and "451243" have the same digit frequency`() {
        equalDigitFrequency(123445, 451243) shouldBeEqualTo true
    }

    @Test
    fun `"447" and "477" have different digit frequency"`() {
        equalDigitFrequency(447, 477) shouldBeEqualTo false
    }

    @Test
    fun `"578" and "0" have different digit frequency"`() {
        equalDigitFrequency(578, 0) shouldBeEqualTo false
    }

    @Test
    fun `"0" and "0" have the same digit frequency"`() {
        equalDigitFrequency(0, 0) shouldBeEqualTo true
    }
}
