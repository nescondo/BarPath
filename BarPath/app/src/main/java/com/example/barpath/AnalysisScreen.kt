package com.example.barpath

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AnalysisScreen(vm: MainScreenViewModel, onNavigateBack: () -> Unit ={}) {

    Column(modifier = Modifier.fillMaxWidth().
    fillMaxHeight().
    background(vm._color1.value)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(vm._color1.value),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { onNavigateBack() }
            ) {
                Text("Back")
            }
        }
    }
}

