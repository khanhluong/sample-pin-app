package com.hk.samplepinapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hk.samplepinapp.repository.PinCalculatorRepository
import com.hk.samplepinapp.utils.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PinCalculatorViewModelTest {

    private lateinit var pinCalculatorViewModel: PinCalculatorViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    private lateinit var referenceManager: PreferenceManager

    @Mock
    private lateinit var pinCalculatorRepository: PinCalculatorRepository


    @get:Rule
    public val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        pinCalculatorViewModel = PinCalculatorViewModel(pinCalculatorRepository, referenceManager)

    }

    @After
    fun tearDown() {
        // reset main dispatcher to the original Main dispatcher
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `gen pin test`() {
        runBlocking {
            launch(Dispatchers.Main) {
                val value = pinCalculatorViewModel.calculate("1234")
                org.junit.Assert.assertNotNull(value)
            }
        }


    }


}