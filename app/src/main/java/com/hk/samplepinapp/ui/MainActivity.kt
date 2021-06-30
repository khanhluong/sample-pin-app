package com.hk.samplepinapp.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.hk.samplepinapp.R
import com.hk.samplepinapp.dialog.PurchaseConfirmationDialogFragment
import com.hk.samplepinapp.viewmodel.PinCalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar

    private val pinCalculatorViewModel: PinCalculatorViewModel by viewModels()

    lateinit var btnCalculate: Button
    lateinit var txtPinResult: TextView
    lateinit var edtInputPinValue: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "PIN Calculator"
        setSupportActionBar(toolbar)

        btnCalculate = findViewById(R.id.btnCalculate)
        txtPinResult = findViewById(R.id.tvResult)
        edtInputPinValue = findViewById(R.id.edtInput)
        btnCalculate.setOnClickListener {
            pinCalculatorViewModel.pinInputValidate(edtInputPinValue.text.toString()).observe(this, this::showPinInputValidate)
        }

    }

    private fun showPinInputValidate(validate: Boolean) {
        if (validate) {
            pinCalculatorViewModel.calculate(edtInputPinValue.text.toString())
                .observe(this) { res -> showPinCalculateInputValue(res) }
        } else {
            edtInputPinValue.error = "Pin input error"
        }
    }

    private fun showPinCalculateInputValue(result: String) {
        txtPinResult.text = result
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_settings -> {
                SettingsFragment().show(
                    supportFragmentManager,
                    PurchaseConfirmationDialogFragment.TAG
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
