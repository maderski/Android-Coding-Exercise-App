package com.maderskitech.androidcodingexerciseapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maderskitech.kmpcodingexercisenetwork.domain.usecase.SortedItemsUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val sortedItemsUseCase: SortedItemsUseCase) : ViewModel() {
    fun printSortedItemsToTerminal() {
        viewModelScope.launch {
            sortedItemsUseCase.getSortedItemsFlow().collect { result ->
                result
                    .onSuccess { listIdToItemsMap ->
                        listIdToItemsMap.forEach { entry ->
                            Log.d(TAG, "${entry.key}")
                            entry.value.forEach { item ->
                                Log.d(TAG, item.name)
                            }
                        }
                    }
                    .onFailure { throwable ->
                        Log.e(TAG, "Error Occurred getting sorted items.\nMessage: ${throwable.message}")
                    }
            }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}