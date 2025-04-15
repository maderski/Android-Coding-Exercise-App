package com.maderskitech.androidcodingexerciseapp.application

import android.app.Application
import com.maderskitech.androidcodingexerciseapp.application.di.appModule
import com.maderskitech.kmpcodingexercisenetwork.di.commonModule
import com.maderskitech.kmpcodingexercisenetwork.di.initKoin

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(listOf(appModule))
    }
}