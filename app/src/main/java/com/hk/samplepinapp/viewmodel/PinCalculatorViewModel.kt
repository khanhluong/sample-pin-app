package com.hk.samplepinapp.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.hk.samplepinapp.repository.PinCalculatorRepository
import com.hk.samplepinapp.utils.PreferenceManager
import com.hk.samplepinapp.utils.SharePreferencesConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinCalculatorViewModel @Inject constructor(
    private val pinCalculatorRepository: PinCalculatorRepository,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    fun calculate(pin: String): LiveData<String> {
        val liveDataResult = MutableLiveData<String>()
        viewModelScope.launch {
            liveDataResult.value = pinCalculatorRepository.provideResult(pin)
        }
        return liveDataResult

    }

    fun pinInputValidate(pinInput: String): LiveData<Boolean> {
        val inputValidate = MutableLiveData<Boolean>()
        inputValidate.value =
            pinInput.length in 4..12 && pinInput.length == preferenceManager.getPinLength(
                SharePreferencesConstants.PIN_LENGTH
            )
        return inputValidate
    }
}