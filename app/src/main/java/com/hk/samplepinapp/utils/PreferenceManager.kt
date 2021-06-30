package com.hk.samplepinapp.utils

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceManager @Inject constructor(private val preferences: SharedPreferences) {

    fun setPinLength(tag: String, pin: Int) {
        preferences.edit().putInt(tag, pin).apply()
    }

    fun getPinLength(tag: String): Int {
        //return Default length of Pin is 4
        return preferences.getInt(tag, 4)
    }
}