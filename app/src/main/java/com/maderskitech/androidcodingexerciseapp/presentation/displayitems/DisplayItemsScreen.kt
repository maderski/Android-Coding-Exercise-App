package com.maderskitech.androidcodingexerciseapp.presentation.displayitems

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.maderskitech.androidcodingexerciseapp.presentation.displayitems.components.DisplayItemsScreenContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DisplayItemsScreen(
    backgroundColor: Color,
    viewModel: DisplayItemsViewModel = koinViewModel()
) {
    Scaffold(modifier = Modifier
        .background(backgroundColor)
        .safeDrawingPadding()
    ) { innerPadding ->
        DisplayItemsScreenContent(
            isLoading = viewModel.isLoading,
            itemGroups = viewModel.itemGroups,
            innerPadding = innerPadding
        )
    }
}