package com.maderskitech.androidcodingexerciseapp.presentation

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.maderskitech.androidcodingexerciseapp.presentation.model.ItemGroup
import com.maderskitech.androidcodingexerciseapp.presentation.ui.theme.AndroidCodingExerciseAppTheme
import com.maderskitech.kmpcodingexercisenetwork.domain.model.Item
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidCodingExerciseAppTheme {
                var isDark = isSystemInDarkTheme()
                var backgroundColor = if (isDark) Color.Black else Color.White
                UpdateStatusBarForTheme(this@MainActivity, isDark)
                Scaffold(modifier = Modifier
                    .background(backgroundColor)
                    .safeDrawingPadding()
                ) { innerPadding ->
                    DisplayItemsScreen(
                        isLoading = viewModel.isLoading,
                        itemGroups = viewModel.itemGroups,
                        innerPadding = innerPadding
                    )
                }
            }
        }

        viewModel.fetchItems()
    }
}

@Composable
private fun UpdateStatusBarForTheme(activity: Activity, isDarkTheme: Boolean) {
    val view = LocalView.current
    val window = activity.window
    val lifecycleOwner = LocalLifecycleOwner.current
    val useLightIcons = !isDarkTheme

    DisposableEffect(lifecycleOwner, isDarkTheme) {
        val insetsController = WindowInsetsControllerCompat(window, view)

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                insetsController.isAppearanceLightStatusBars = useLightIcons
            }
        }

        insetsController.isAppearanceLightStatusBars = useLightIcons

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
private fun DisplayItemsScreen(
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun GroupedLazyColumn(
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

@Composable
private fun GroupHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplayItemsScreen() {
    DisplayItemsScreen(
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
fun PreviewDisplayItemsScreenLoading() {
    DisplayItemsScreen(
        true,
        emptyList(),
        PaddingValues(8.dp)
    )
}