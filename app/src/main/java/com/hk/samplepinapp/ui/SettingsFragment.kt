package com.hk.samplepinapp.ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.hk.samplepinapp.R
import com.hk.samplepinapp.viewmodel.PinCalculatorViewModel
import com.hk.samplepinapp.viewmodel.PinSettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : DialogFragment(R.layout.fragment_settings) {

    private val pinSettingsViewModel: PinSettingsViewModel by viewModels()

    lateinit var btnClose: Button
    lateinit var btnSubmitPin: Button
    lateinit var edtPinLength: EditText


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnClose = view.findViewById(R.id.btn_close)
        btnSubmitPin = view.findViewById(R.id.btn_submit_pin)
        edtPinLength = view.findViewById(R.id.edt_pin_length)

        edtPinLength.requestFocus()
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        btnSubmitPin.setOnClickListener {
            pinSettingsViewModel.pinLengthValidate(edtPinLength.text.toString()).observe(this, this::setPinValidatePin)
        }

        btnClose.setOnClickListener {
            dismiss()
        }
    }

    private fun setPinValidatePin(validate: Boolean) {

        if (validate) {
            pinSettingsViewModel.savePinLength(Integer.parseInt(edtPinLength.text.toString()))
            dismiss()
        } else {
            edtPinLength.error = "wrong input"
        }
    }
}