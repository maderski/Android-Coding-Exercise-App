package com.maderskitech.androidcodingexerciseapp.application

import android.app.Application
import com.maderskitech.androidcodingexerciseapp.application.di.appModule
import com.maderskitech.kmpcodingexercisenetwork.di.KoinHelper

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinHelper.initKoin(listOf(appModule))
    }
}