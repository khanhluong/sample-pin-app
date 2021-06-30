package com.hk.samplepinapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hk.samplepinapp.repository.PinCalculatorRepository
import com.hk.samplepinapp.utils.PreferenceManager
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class PinSettingsViewModelTest {

    private lateinit var pinSettingsViewModel: PinSettingsViewModel

    @Mock
    private lateinit var referenceManager: PreferenceManager


    @get:Rule
    public val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        pinSettingsViewModel = PinSettingsViewModel(referenceManager)
    }

    @Test
    fun `pin with length not from value 4 to 12`(){
        assertEquals(pinSettingsViewModel.pinLengthValidate("1").value, false)
        assertEquals(pinSettingsViewModel.pinLengthValidate("13").value, false)
        assertEquals(pinSettingsViewModel.pinLengthValidate("-1").value, false)
    }

    @Test
    fun `test pin length equal 4`(){
        assertEquals(pinSettingsViewModel.pinLengthValidate("4").value, true)
    }

    @Test
    fun `test pin length equal 12`(){
        assertEquals(pinSettingsViewModel.pinLengthValidate("12").value, true)
    }
}