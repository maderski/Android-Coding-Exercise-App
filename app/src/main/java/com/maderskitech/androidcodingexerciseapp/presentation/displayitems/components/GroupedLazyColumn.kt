package com.maderskitech.androidcodingexerciseapp.presentation.displayitems.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maderskitech.androidcodingexerciseapp.presentation.displayitems.model.ItemGroup
import kotlin.collections.forEach

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupedLazyColumn(
    itemGroups: List<ItemGroup>,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = innerPadding
    ) {
        itemGroups.forEach { itemGroup ->
            stickyHeader {
                GroupHeader("List ID: ${itemGroup.listId}")
            }
            items(
                items = itemGroup.items,
                key = { it.id }
            ) { item ->
                ListItem(
                    headlineContent = {
                        Text(item.name)
                    },
                    supportingContent = {
                        Text("ID: ${item.id}")
                    },
                    trailingContent = {
                        Text("List ID: ${item.listId}")
                    }
                )
            }
        }
    }
}