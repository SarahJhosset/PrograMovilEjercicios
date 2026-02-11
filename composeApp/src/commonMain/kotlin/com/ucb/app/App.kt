package com.ucb.app

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ucb.app.counter.presentation.screen.CounterScreen
import com.ucb.app.detail.presentation.screen.DetailScreen
import com.ucb.app.product_detail.presentation.screen.ProductDetailScreen


@Composable
@Preview
fun App() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing
        ) { paddingVaues ->
//            CollectionScreen( modifier = Modifier.padding(paddingVaues))
            //DetailScreen(modifier = Modifier.padding(paddingVaues))
//            ProductDetailScreen(modifier = Modifier.padding(paddingVaues))
            CounterScreen()
        }

    }
}