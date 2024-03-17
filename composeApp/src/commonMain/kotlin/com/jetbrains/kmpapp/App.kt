package com.jetbrains.kmpapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.jetbrains.kmpapp.screens.HomeScreen

@Composable
fun App() {
    MaterialTheme {
        Navigator(HomeScreen()) {
            SlideTransition(it)
        }
    }
}
