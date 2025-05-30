package com.maderskitech.androidcodingexerciseapp.presentation.displayitems

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maderskitech.androidcodingexerciseapp.presentation.displayitems.model.ItemGroup
import com.maderskitech.kmpcodingexercisenetwork.domain.respository.ItemRepository
import kotlinx.coroutines.launch

class DisplayItemsViewModel(private val itemsRepository: ItemRepository) : ViewModel() {
    var isLoading by mutableStateOf(false)
        private set

    var itemGroups by mutableStateOf<List<ItemGroup>>(emptyList())
        private set

    init {
        fetchItems()
    }

    fun fetchItems() {
        isLoading = true
        viewModelScope.launch {
            itemsRepository.getAllItems()
                .onSuccess { listIdToItemsMap ->
                    itemGroups = listIdToItemsMap.map { entry ->
                        ItemGroup(entry.key, entry.value)
                    }
                    isLoading = false
                }
                .onFailure { throwable ->
                    Log.e(
                        TAG,
                        "Error Occurred getting sorted items.\nMessage: ${throwable.message}"
                    )

                }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}