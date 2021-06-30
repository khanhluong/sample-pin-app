package com.hk.samplepinapp

import android.app.Application
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SamplePinApp : MultiDexApplication(){
}