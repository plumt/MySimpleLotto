package com.yun.mysimplelotto

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MySimpleLottoApplication : Application(){

    override fun onCreate() {
        super.onCreate()
    }
}