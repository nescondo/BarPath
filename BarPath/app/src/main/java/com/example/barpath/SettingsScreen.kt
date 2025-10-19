package com.example.barpath

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(vm: MainScreenViewModel, onNavigateBack: () -> Unit ={}){

    Button(
        onClick = {onNavigateBack()}
    ) {
        Text("Back")
    }

}