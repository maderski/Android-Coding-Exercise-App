package com.maderskitech.androidcodingexerciseapp.presentation.displayitems.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maderskitech.androidcodingexerciseapp.presentation.displayitems.model.ItemGroup
import com.maderskitech.kmpcodingexercisenetwork.domain.model.Item

@Composable
fun DisplayItemsScreenContent(
    isLoading: Boolean,
    itemGroups: List<ItemGroup>,
    innerPadding: PaddingValues
) {
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        GroupedLazyColumn(itemGroups, innerPadding)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplayItemsScreenContent() {
    DisplayItemsScreenContent(
        false,
        listOf(
            ItemGroup(1, listOf(Item(100, 1, "Item 100"), Item(101, 1, "Item 101"), Item(102, 1, "Item 202"))),
            ItemGroup(2, listOf(Item(200, 2, "Item 200"), Item(201, 2, "Item 201"), Item(202, 2, "Item 202")))
        ),
        PaddingValues(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplayItemsScreenContentLoading() {
    DisplayItemsScreenContent(
        true,
        emptyList(),
        PaddingValues(8.dp)
    )
}