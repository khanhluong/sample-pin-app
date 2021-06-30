package com.hk.samplepinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hk.samplepinapp.utils.PreferenceManager
import com.hk.samplepinapp.utils.SharePreferencesConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PinSettingsViewModel @Inject constructor(private val preferenceManager: PreferenceManager) :
    ViewModel() {

    fun savePinLength(pinValue: Int) {
        preferenceManager.setPinLength(SharePreferencesConstants.PIN_LENGTH, pinValue)
    }

    fun getPinLength(): Int {
        return preferenceManager.getPinLength(SharePreferencesConstants.PIN_LENGTH)
    }

    fun pinLengthValidate(pin: String): LiveData<Boolean> {
        val validatePinLength = MutableLiveData<Boolean>()
        if (pin.isNotEmpty()) {
            val pinLengthValue = Integer.parseInt(pin)
            validatePinLength.value = pinLengthValue in 4..12
        } else {
            validatePinLength.value = false
        }
        return validatePinLength
    }


}