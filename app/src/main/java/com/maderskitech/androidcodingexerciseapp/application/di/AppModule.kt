package com.maderskitech.androidcodingexerciseapp.application.di

import com.maderskitech.androidcodingexerciseapp.presentation.displayitems.DisplayItemsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::DisplayItemsViewModel)
}