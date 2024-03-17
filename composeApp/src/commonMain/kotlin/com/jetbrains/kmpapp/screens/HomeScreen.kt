package com.jetbrains.kmpapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jetbrains.kmpapp.viewmodels.TestViewModel

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<TestViewModel>()
        val navigator = LocalNavigator.currentOrThrow
        val items = viewModel.museums.collectAsState()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = items.value.size.toString())
//            val viewModel = getScreenModel<TestViewModel>()
            Button(onClick = {
//                navigator.push(DetailScreen(1))
                viewModel.getData()
            }) {
                Text(text = "Fetch")
            }
        }
    }
}
