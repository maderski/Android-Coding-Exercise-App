package com.maderskitech.androidcodingexerciseapp.presentation

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.maderskitech.androidcodingexerciseapp.presentation.displayitems.DisplayItemsScreen
import com.maderskitech.androidcodingexerciseapp.presentation.ui.theme.AndroidCodingExerciseAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidCodingExerciseAppTheme {
                var isDark = isSystemInDarkTheme()
                var backgroundColor = if (isDark) Color.Black else Color.White
                UpdateStatusBarForTheme(this@MainActivity, isDark)
                DisplayItemsScreen(backgroundColor)
            }
        }
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