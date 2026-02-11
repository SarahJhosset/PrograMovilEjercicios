package com.ucb.app.counter.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ucb.app.counter.presentation.viewmodel.CounterViewModel

@Composable
fun CounterScreen( viewModel: CounterViewModel = CounterViewModel()) {

    val state = viewModel.stateString.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(state.value)
        Button( onClick = {
            viewModel.increment()
        }) {
            Text("Add")
        }
    }
}