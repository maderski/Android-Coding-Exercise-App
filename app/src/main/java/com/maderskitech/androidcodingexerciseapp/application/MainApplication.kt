package com.maderskitech.androidcodingexerciseapp.application

import android.app.Application
import com.maderskitech.androidcodingexerciseapp.application.di.appModule
import com.maderskitech.kmpcodingexercisenetwork.di.initKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin() {
            modules(appModule)
        }
    }
}