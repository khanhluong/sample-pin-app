package com.hk.samplepinapp.utils

import kotlin.experimental.or
import kotlin.experimental.xor

class ISOFormat {

    companion object {
        const val LENGTH = 8
        const val DEFAULT_PAN = "1111222233334444"
    }


    private val hexArray = "0123456789ABCDEF".toCharArray()

    fun pinCalculateResult(pin: String, pan: String = DEFAULT_PAN): String {
        return bytesToHex(formatISO3(pin, pan))
    }

    private fun formatISO3(pin: String, pan: String): ByteArray {
        val pinField = hexStringToByteArray(preparePin(pin))
        val panField = preparePan(pan)
        val result = ByteArray(LENGTH)
        for (i in 0..7) {
            result[i] = pinField[i] xor panField[i]
        }
        return result
    }


    /**
     * Prepare a PIN – L is length of the PIN, P is PIN digit, R is random value from X’0′ to X’F’
     */
    private fun preparePin(pin: String): String {
        var pinField: String = "3" + Integer.toHexString(pin.length) + pin
        for (i in 0 until 14 - pin.length) {
            val randomValue = getRandomValueInt()
            pinField += Integer.toHexString(randomValue).uppercase()
        }
        return pinField
    }

    /**
     * Prepare PAN – take 12 rightmost digits of the primary account number (excluding the check digit)
     */
    private fun preparePan(pan: String): ByteArray {
        return getPan12RightMostDigits(pan)
    }

    private fun getPan12RightMostDigits(value: String): ByteArray {
//        excluding the check digit
        val newValueLength = value.length - 1
        val subString = value.substring(newValueLength - 12, newValueLength)
        return hexStringToByteArray("0000$subString")
    }



    fun bytesToHex(bytes: ByteArray): String {
        val hexChars = CharArray(bytes.size * 2)
        for (j in bytes.indices) {
            val v = bytes[j].toInt() and 0xFF
            hexChars[j * 2] = hexArray[v ushr 4]
            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
        }
        return String(hexChars)
    }


    fun hexStringToByteArray(input: String): ByteArray {

        if (input.length in 13 downTo 3){
            throw Exception("length")
        }

        val result = ByteArray(input.length / 2)

        for (i in input.indices step 2) {
            val firstIndex = hexArray.indexOf(input[i])
            val secondIndex = hexArray.indexOf(input[i + 1])

            val octet =
                setHiNibbleValue(firstIndex.toByte()).or(setLowNibbleValue(secondIndex.toByte()))
            result[i.shr(1)] = octet.toByte()
        }

        return result
    }

    private fun setHiNibbleValue(value: Byte): Byte = (0xF0 and (value.toInt() shl 4)).toByte()

    private fun setLowNibbleValue(value: Byte): Byte = (0x0F and value.toInt()).toByte()

    /**
     * random value from X’0′ to X’F’
     */
    private fun getRandomValueInt(): Int {
        return (10..15).random()
    }
}