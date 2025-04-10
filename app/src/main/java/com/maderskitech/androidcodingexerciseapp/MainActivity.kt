package com.maderskitech.androidcodingexerciseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maderskitech.androidcodingexerciseapp.ui.theme.AndroidCodingExerciseAppTheme
import com.maderskitech.kmpcodingexercisenetwork.domain.model.Item
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidCodingExerciseAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DisplayItemsScreen(
                        isLoading = viewModel.isLoading,
                        itemList = viewModel.items,
                        innerPadding = innerPadding
                    )
                }
            }
        }

        viewModel.fetchItems()
    }
}

@Composable
fun DisplayItemsScreen(
    isLoading: Boolean,
    itemList: List<Item>,
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
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding
        ) {
            items(
                items = itemList,
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

@Preview(showBackground = true)
@Composable
fun PreviewDisplayItemsScreen() {
    DisplayItemsScreen(
        false,
        listOf(Item(100, 1, "Item 100"), Item(101, 2, "Item 101"), Item(202, 3, "Item 202")),
        PaddingValues(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplayItemsScreenLoading() {
    DisplayItemsScreen(
        true,
        emptyList(),
        PaddingValues(8.dp)
    )
}