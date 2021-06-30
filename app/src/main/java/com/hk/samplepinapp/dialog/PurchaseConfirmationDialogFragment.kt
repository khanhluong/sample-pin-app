package com.hk.samplepinapp.dialog

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.hk.samplepinapp.R
import com.hk.samplepinapp.viewmodel.PinCalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchaseConfirmationDialogFragment: DialogFragment() {

    private val viewModel: PinCalculatorViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.order_confirmation))
            .setPositiveButton(getString(R.string.ok)) { _,_ -> setConfigValue()}
            .create()

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }

    private fun setConfigValue(){
        Log.d("Save", "Save config")
//        viewModel.saveTag("1234")
    }
}