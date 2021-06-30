package com.hk.samplepinapp.repository

import com.hk.samplepinapp.utils.ISOFormat
import javax.inject.Inject


class PinCalculatorRepository @Inject constructor(){

    suspend fun provideResult(pin: String): String {
        val isoFormat = ISOFormat()
        return isoFormat.pinCalculateResult(pin)
    }
}