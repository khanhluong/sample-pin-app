package com.hk.samplepinapp.utils

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.Exception

@RunWith(JUnit4::class)
class ISOFormatTest{

    lateinit var isoFormat: ISOFormat

    @Before
    fun setUp(){
        isoFormat = ISOFormat()
    }

    @After
    fun tearDown(){

    }

    @Test
    fun `test pin with length is 4`(){
        val result = isoFormat.pinCalculateResult("1234")
        assertEquals(result.length, 16)
    }

    @Test
    fun `test pin with length is 8`(){
        val result = isoFormat.pinCalculateResult("12345678")
        assertEquals(result.length, 16)
    }

    @Test
    fun `test pin with length is 12`(){
        val result = isoFormat.pinCalculateResult("123456789123")
        assertEquals(result.length, 16)
    }

    @Test(expected = Exception::class)
    fun `test pin with length is 15`(){
        isoFormat.pinCalculateResult("123456789123123")
    }

    @Test
    fun `return 16 when input length is empty`(){
        val result = isoFormat.pinCalculateResult("")
        assertEquals(result.length, 16)
    }





}